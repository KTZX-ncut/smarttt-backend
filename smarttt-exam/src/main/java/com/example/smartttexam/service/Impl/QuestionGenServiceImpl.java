package com.example.smartttexam.service.Impl;

import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.Utils.PythonRunner;
import com.example.smartttexam.dto.KwaContextDTO;
import com.example.smartttexam.dto.PageResult;
import com.example.smartttexam.dto.QuestionGenRequest;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.mapper.CmKwadictMapper;
import com.example.smartttexam.mapper.TmTestquelibExtMapper;
import com.example.smartttexam.mapper.TmTestquelibKwaMapper;
import com.example.smartttexam.pojo.CmKwadict;
import com.example.smartttexam.pojo.TmTestquelib;
import com.example.smartttexam.pojo.TmTestquelibKwa;
import com.example.smartttexam.service.QuestionGenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class QuestionGenServiceImpl implements QuestionGenService {

    @Autowired
    private CmKwadictMapper cmKwadictMapper;

    @Autowired
    private TmTestquelibExtMapper tmTestquelibExtMapper;

    @Autowired
    private TmTestquelibKwaMapper tmTestquelibKwaMapper;

    @Value("${llm.api.url}")
    private String llmApiUrl;

    @Value("${llm.api.key}")
    private String llmApiKey;

    @Value("${llm.api.model:qwen3-235b}")
    private String llmModel;

    //获取kwa列表
    @Override
    public Result getCourseKwa(String courseId) {
        List<CmKwadict> kwaList = cmKwadictMapper.getKwaByCourseId(courseId);
        // 转换为DTO返回
        List<KwaContextDTO> result = new ArrayList<>();
        for (CmKwadict k : kwaList) {
            result.add(new KwaContextDTO(
                    k.getKeywordid(), k.getKeywordName(),
                    k.getAbilityid(), k.getAbilityName(),
                    k.getId(), k.getName()
            ));
        }
        return Result.success(result);
    }

    //AI出题核心流程
    @Override
    public Result generateQuestions(QuestionGenRequest request) {
        // 1. 查询KWA数据，构建上下文
        List<CmKwadict> kwaList = cmKwadictMapper.getKwaByIds(request.getSelectedKwaIds());
        if (kwaList.isEmpty()) {
            return Result.error("未找到选中的KWA数据");
        }

        // 2. 构建传给Python的JSON参数
        List<Map<String, String>> kwaMapList = new ArrayList<>();
        for (CmKwadict k : kwaList) {
            Map<String, String> item = new HashMap<>();
            item.put("keyword", k.getKeywordName() != null ? k.getKeywordName() : "");
            item.put("ability", k.getAbilityName() != null ? k.getAbilityName() : "");
            item.put("kwaName", k.getName() != null ? k.getName() : "");
            kwaMapList.add(item);
        }
        int questionCount = request.getQuestionCount() != null ? request.getQuestionCount() : 20;

        Map<String, Object> kwaJson = new HashMap<>();
        kwaJson.put("kwaList", kwaMapList);
        kwaJson.put("questionCount", questionCount);
        kwaJson.put("apiKey", llmApiKey);
        kwaJson.put("apiUrl", llmApiUrl);
        kwaJson.put("model", llmModel);

        String kwaJsonStr;
        try {
            kwaJsonStr = new ObjectMapper().writeValueAsString(kwaJson);
        } catch (Exception e) {
            return Result.error("构建KWA JSON失败: " + e.getMessage());
        }

        // 3. 调用Python脚本（传入唯一输出ID，防止并发冲突）
        String outputId = UUID.randomUUID().toString().substring(0, 8);
        PythonRunner.PythonResult pyResult = PythonRunner.run("generateQuestion.py", kwaJsonStr, outputId);
        if (!pyResult.isSuccess()) {
            return Result.error("AI出题失败: " + pyResult.getErrOutput());
        }

        // 4. 读取Python输出的JSON
        String jsonPath = "src/main/resources/python/outputFiles/" + outputId + ".json";
        File jsonFile = new File(jsonPath);
        if (!jsonFile.exists()) {
            return Result.error("AI出题输出文件未生成: " + jsonPath);
        }

        List<Map<String, Object>> questionData;
        try {
            questionData = new ObjectMapper().readValue(jsonFile,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            return Result.error("解析AI出题JSON失败: " + e.getMessage());
        }

        if (questionData.isEmpty()) {
            return Result.error("AI未生成任何题目");
        }

        // 5. 转换为tm_testquelib实体，保存到数据库
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<TmTestquelib> questions = new ArrayList<>();
        List<TmTestquelibKwa> kwaLinks = new ArrayList<>();

        for (Map<String, Object> dto : questionData) {
            String libId = CommonFunctions.generateEnhancedID("tm_testquelib");

            String questionText = (String) dto.getOrDefault("question", "");
            int level = dto.get("level") instanceof Number ? ((Number) dto.get("level")).intValue() : 1;
            int type = dto.get("type") instanceof Number ? ((Number) dto.get("type")).intValue() : 1;
            Object kwaObj = dto.get("KWA");

            TmTestquelib q = new TmTestquelib();
            q.setId(libId);
            q.setContent(questionText);
            String title = questionText != null && questionText.length() > 50
                    ? questionText.substring(0, 50) : questionText;
            q.setTitle(title);
            // type映射: 1=单选→0201, 2=多选→0202, 3=判断→0203, 4=填空→0204, 5=简答→0205
            String[] typeMap = {"", "0201", "0202", "0203", "0204", "0205"};
            q.setQuestionTypeId(type >= 1 && type <= 5 ? typeMap[type] : "0204");
            q.setDifficultyLevel((double) level);
            q.setDifferenceLevel(0.0);
            q.setGuesssLevel(0.0);
            q.setCourseId(request.getCourseId());
            q.setClassroomId(request.getClassroomId());
            q.setUnitId(request.getUnitId());
            q.setCreatorId(request.getCreatorId());
            q.setCreator(request.getCreator());
            q.setCreateTime(now);
            q.setStatus(0);
            q.setCodelang("zh");
            questions.add(q);

            // 处理KWA关联：模糊匹配
            if (kwaObj instanceof List) {
                @SuppressWarnings("unchecked")
                List<String> kwaNames = (List<String>) kwaObj;
                for (String kwaName : kwaNames) {
                    for (CmKwadict kwa : kwaList) {
                        String dbKwaName = kwa.getName();
                        if (dbKwaName != null && !dbKwaName.isEmpty()
                                && (kwaName.contains(dbKwaName) || dbKwaName.contains(kwaName))) {
                            TmTestquelibKwa link = new TmTestquelibKwa();
                            link.setId(CommonFunctions.generateEnhancedID("tm_testquelib_kwa"));
                            link.setLibId(libId);
                            link.setKwaId(kwa.getId());
                            link.setKwaName(dbKwaName);
                            link.setDataValue(1.0);
                            kwaLinks.add(link);
                            break;
                        }
                    }
                }
            }
        }

        // 6. 批量插入
        tmTestquelibExtMapper.batchInsert(questions);
        if (!kwaLinks.isEmpty()) {
            tmTestquelibKwaMapper.batchInsert(kwaLinks);
        }

        // 7. 清理临时文件
        jsonFile.delete();
        File excelFile2 = new File("src/main/resources/python/outputFiles/" + outputId + ".xlsx");
        excelFile2.delete();

        return Result.success("成功生成 " + questions.size() + " 道题目");
    }


    @Override
    public Result getQuestionsByCourse(String courseId, int pageIndex, int pageSize) {
        int offset = (pageIndex - 1) * pageSize;
        List<TmTestquelib> list = tmTestquelibExtMapper.getQuestionsByCourseIdPaged(courseId, offset, pageSize);
        long total = tmTestquelibExtMapper.countByCourseId(courseId);
        PageResult<TmTestquelib> result = new PageResult<>(list, total, pageIndex, pageSize);
        return Result.success(result);
    }

    @Override
    public Result deleteQuestions(List<String> libIds) {
        if (libIds != null && !libIds.isEmpty()) {
            tmTestquelibExtMapper.softDeleteByIds(libIds);
        }
        return Result.success();
    }
}
