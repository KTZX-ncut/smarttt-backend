package com.example.smartttexam.service.Impl;

import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.Utils.PythonRunner;
import com.example.smartttexam.dto.*;
import com.example.smartttexam.mapper.CmKwadictMapper;
import com.example.smartttexam.mapper.TmTestquelibExtMapper;
import com.example.smartttexam.mapper.TmTestquelibKwaMapper;
import com.example.smartttexam.pojo.CmKwadict;
import com.example.smartttexam.pojo.TmTestquelib;
import com.example.smartttexam.pojo.TmTestquelibKwa;
import com.example.smartttexam.service.ProgressManager;
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

    @Autowired private CmKwadictMapper cmKwadictMapper;
    @Autowired private TmTestquelibExtMapper tmTestquelibExtMapper;
    @Autowired private TmTestquelibKwaMapper tmTestquelibKwaMapper;
    @Autowired private ProgressManager progressManager;

    @Value("${llm.api.url}") private String llmApiUrl;
    @Value("${llm.api.key}") private String llmApiKey;
    @Value("${llm.api.model:qwen3-235b}") private String llmModel;

    @Override
    public Result getCourseKwa(String courseId) {
        List<CmKwadict> kwaList = cmKwadictMapper.getKwaByCourseId(courseId);
        List<KwaContextDTO> result = new ArrayList<>();
        for (CmKwadict k : kwaList) {
            result.add(new KwaContextDTO(
                    k.getKeywordid(), k.getKeywordName(),
                    k.getAbilityid(), k.getAbilityName(),
                    k.getId(), k.getName()));
        }
        return Result.success(result);
    }

    @Override
    public Result generateQuestions(QuestionGenRequest request) {
        // 同步校验
        final List<CmKwadict> kwaList = cmKwadictMapper.getKwaByIds(request.getSelectedKwaIds());
        if (kwaList.isEmpty()) return Result.error("未找到选中的KWA数据");

        final String taskId = UUID.randomUUID().toString().substring(0, 8);
        final int questionCount = request.getQuestionCount() != null ? request.getQuestionCount() : 20;
        final String courseId = request.getCourseId();
        final String classroomId = request.getClassroomId();
        final String unitId = request.getUnitId();
        final String creatorId = request.getCreatorId();
        final String creator = request.getCreator();

        // 构建JSON
        List<Map<String, String>> kwaMapList = new ArrayList<>();
        for (CmKwadict k : kwaList) {
            Map<String, String> item = new HashMap<>();
            item.put("keyword", k.getKeywordName() != null ? k.getKeywordName() : "");
            item.put("ability", k.getAbilityName() != null ? k.getAbilityName() : "");
            item.put("kwaName", k.getName() != null ? k.getName() : "");
            kwaMapList.add(item);
        }
        Map<String, Object> kwaJson = new HashMap<>();
        kwaJson.put("kwaList", kwaMapList);
        kwaJson.put("questionCount", questionCount);
        kwaJson.put("apiKey", llmApiKey);
        kwaJson.put("apiUrl", llmApiUrl);
        kwaJson.put("model", llmModel);
        final String kwaJsonStr;
        try { kwaJsonStr = new ObjectMapper().writeValueAsString(kwaJson); }
        catch (Exception e) { return Result.error("构建KWA JSON失败: " + e.getMessage()); }

        // 初始化进度
        progressManager.create(taskId);

        // 异步执行
        new Thread(() -> {
            try {
                progressManager.update(taskId, 10, "正在调用AI模型...");
                PythonRunner.PythonResult pyResult = PythonRunner.run("generateQuestion.py", kwaJsonStr, taskId);
                if (!pyResult.isSuccess()) { progressManager.fail(taskId, pyResult.getErrOutput()); return; }

                progressManager.update(taskId, 70, "正在解析生成结果...");
                File jsonFile = new File("src/main/resources/python/outputFiles/" + taskId + ".json");
                if (!jsonFile.exists()) { progressManager.fail(taskId, "输出文件未生成"); return; }

                List<Map<String, Object>> questionData;
                try { questionData = new ObjectMapper().readValue(jsonFile, new com.fasterxml.jackson.core.type.TypeReference<List<Map<String, Object>>>() {}); }
                catch (Exception e) { progressManager.fail(taskId, "解析失败: " + e.getMessage()); return; }
                if (questionData.isEmpty()) { progressManager.fail(taskId, "AI未生成题目"); return; }

                progressManager.update(taskId, 85, "正在存入题库...");
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                List<TmTestquelib> questions = new ArrayList<>();
                List<TmTestquelibKwa> kwaLinks = new ArrayList<>();
                Map<String, CmKwadict> nameMap = new HashMap<>();
                for (CmKwadict k : kwaList) if (k.getName() != null) nameMap.put(k.getName(), k);

                for (Map<String, Object> dto : questionData) {
                    String libId = CommonFunctions.generateEnhancedID("tm_testquelib");
                    String questionText = (String) dto.getOrDefault("question", "");
                    int level = dto.get("level") instanceof Number ? ((Number) dto.get("level")).intValue() : 1;
                    int type = dto.get("type") instanceof Number ? ((Number) dto.get("type")).intValue() : 1;
                    Object kwaObj = dto.get("KWA");

                    TmTestquelib q = new TmTestquelib();
                    q.setId(libId); q.setContent(questionText);
                    q.setTitle(questionText != null && questionText.length() > 50 ? questionText.substring(0, 50) : questionText);
                    q.setQuestionTypeId(type >= 1 && type <= 5 ? new String[]{"","0201","0202","0203","0204","0205"}[type] : "0204");
                    q.setDifficultyLevel((double) level); q.setDifferenceLevel(0.0); q.setGuesssLevel(0.0);
                    q.setCourseId(courseId); q.setClassroomId(classroomId); q.setUnitId(unitId);
                    q.setCreatorId(creatorId); q.setCreator(creator); q.setCreateTime(now);
                    q.setStatus(0); q.setCodelang("zh");
                    questions.add(q);

                    if (kwaObj instanceof List) {
                        @SuppressWarnings("unchecked") List<String> kwaNames = (List<String>) kwaObj;
                        for (String kwaName : kwaNames) {
                            CmKwadict matched = nameMap.get(kwaName);
                            if (matched == null) {
                                for (CmKwadict k : kwaList) {
                                    String dbName = k.getName();
                                    if (dbName != null && !dbName.isEmpty() && (kwaName.contains(dbName) || dbName.contains(kwaName)))
                                    { matched = k; break; }
                                }
                            }
                            if (matched != null) {
                                TmTestquelibKwa link = new TmTestquelibKwa();
                                link.setId(CommonFunctions.generateEnhancedID("tm_testquelib_kwa"));
                                link.setLibId(libId); link.setKwaId(matched.getId());
                                link.setKwaName(matched.getName()); link.setDataValue(1.0);
                                kwaLinks.add(link);
                            }
                        }
                    }
                }
                tmTestquelibExtMapper.batchInsert(questions);
                if (!kwaLinks.isEmpty()) tmTestquelibKwaMapper.batchInsert(kwaLinks);

                jsonFile.delete();
                new File("src/main/resources/python/outputFiles/" + taskId + ".xlsx").delete();
                progressManager.done(taskId, questions.size());
            } catch (Exception e) {
                progressManager.fail(taskId, e.getMessage());
            }
        }).start();

        return Result.success(taskId);
    }

    @Override
    public Result getQuestionsByCourse(String courseId, int pageIndex, int pageSize) {
        int offset = (pageIndex - 1) * pageSize;
        List<TmTestquelib> list = tmTestquelibExtMapper.getQuestionsByCourseIdPaged(courseId, offset, pageSize);
        long total = tmTestquelibExtMapper.countByCourseId(courseId);

        if (!list.isEmpty()) {
            List<String> libIds = new ArrayList<>();
            for (TmTestquelib q : list) libIds.add(q.getId());
            List<TmTestquelibKwa> allKwas = tmTestquelibKwaMapper.getByLibIds(libIds);
            Map<String, List<String>> kwaMap = new HashMap<>();
            for (TmTestquelibKwa kwa : allKwas) kwaMap.computeIfAbsent(kwa.getLibId(), k -> new ArrayList<>()).add(kwa.getKwaName());

            List<Map<String, Object>> enriched = new ArrayList<>();
            for (TmTestquelib q : list) {
                Map<String, Object> item = new ObjectMapper().convertValue(q, Map.class);
                item.put("kwas", kwaMap.getOrDefault(q.getId(), Collections.emptyList()));
                enriched.add(item);
            }
            Map<String, Object> pageResult = new HashMap<>();
            pageResult.put("data", enriched); pageResult.put("recordSize", total);
            pageResult.put("pageIndex", pageIndex); pageResult.put("pageSize", pageSize);
            return Result.success(pageResult);
        }
        return Result.success(new PageResult<>(list, total, pageIndex, pageSize));
    }

    @Override
    public Result deleteQuestions(List<String> libIds) {
        if (libIds != null && !libIds.isEmpty()) tmTestquelibExtMapper.softDeleteByIds(libIds);
        return Result.success();
    }

    /** 查询任务进度 */
    public Result getProgress(String taskId) {
        TaskProgress p = progressManager.get(taskId);
        if (p == null) return Result.error("任务不存在");
        return Result.success(p);
    }
}
