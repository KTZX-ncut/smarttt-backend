package com.example.smartttexam.service.Impl;

import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.dto.PaperAutoGenerateRequest;
import com.example.smartttexam.dto.PaperManualGenerateRequest;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.mapper.*;
import com.example.smartttexam.pojo.*;
import com.example.smartttexam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PmTestpaperMapper paperMapper;

    @Autowired
    private PmTestpaperQuestionsMapper paperQuestionsMapper;

    @Autowired
    private PmTestMapper testMapper;

    @Autowired
    private PmTestStudentsMapper testStudentsMapper;

    @Autowired
    private TmTestquelibExtMapper questionMapper;

    @Autowired
    private CmClassroomStudentMapper classroomStudentMapper;

    @Autowired
    private CmClassroomMapper classroomMapper;


    @Override
    public Result autoGenerate(PaperAutoGenerateRequest request) {
        // 1. 获取题库中该课程所有题目
        List<TmTestquelib> allQuestions = questionMapper.getQuestionsByCourseId(request.getCourseId());
        if (allQuestions.isEmpty()) {
            return Result.error("题库中没有题目，请先生成题目");
        }

        // 2. 按 题型ID × 难度等级 分组
        //    结构: Map<questionTypeId, Map<difficultyLevel, List<TmTestquelib>>>
        Map<String, Map<Integer, List<TmTestquelib>>> grouped = new HashMap<>();
        for (TmTestquelib q : allQuestions) {
            String typeId = q.getQuestionTypeId();
            // difficultyLevel是double，转为int用于分组 (1.x→1, 2.x→2, 3.x→3)
            int level = (int) q.getDifficultyLevel();
            if (level < 1) level = 1;
            if (level > 3) level = 3;

            grouped.computeIfAbsent(typeId, k -> new HashMap<>())
                    .computeIfAbsent(level, k -> new ArrayList<>())
                    .add(q);
        }

        // 3. 抽客观题（0204=填空）
        List<TmTestquelib> selectedObj = drawQuestions(
                grouped, "0204", request.getTargetDifficulty(), request.getObjectiveCount());

        // 4. 抽主观题（0205=简答）
        List<TmTestquelib> selectedSubj = drawQuestions(
                grouped, "0205", request.getTargetDifficulty(), request.getSubjectiveCount());

        List<TmTestquelib> allSelected = new ArrayList<>();
        allSelected.addAll(selectedObj);
        allSelected.addAll(selectedSubj);

        if (allSelected.isEmpty()) {
            return Result.error("未能抽取到题目，请检查题库中对应难度和题型的题目数量");
        }

        // 5. 创建试卷
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String paperId = CommonFunctions.generateEnhancedID("pm_testpaper");

        PmTestpaper paper = new PmTestpaper();
        paper.setId(paperId);
        paper.setName(request.getName());
        paper.setMakeModeId("2");  // 2=抽题组卷
        paper.setMakeModeName("抽题组卷");
        paper.setCatelog(request.getCatelog());
        paper.setCourseId(request.getCourseId());
        paper.setClassroomId(request.getClassroomId());
        paper.setCourseName(classroomMapper.getCourseNameById(request.getCourseId()));
        paper.setClassroomName(classroomMapper.getClassroomNameById(request.getClassroomId()));
        paper.setCreatorId(request.getCreatorId());
        paper.setCreator(request.getCreator());
        paper.setCreateTime(now);
        paper.setStatus(0);
        paper.setLocked(0);
        paperMapper.insert(paper);

        // 6. 动态分配分值，总分严格=100（客观题权重1，主观题权重2）
        List<PmTestpaperQuestions> links = new ArrayList<>();
        int totalWeight = 0;
        for (TmTestquelib q : allSelected) {
            totalWeight += "0205".equals(q.getQuestionTypeId()) ? 2 : 1;
        }
        long totalScore = 0;
        for (int i = 0; i < allSelected.size(); i++) {
            TmTestquelib q = allSelected.get(i);
            // 客观题(0201单选/0202多选/0203判断/0204填空)权重1，主观题(0205简答)权重2
            boolean isObj = !"0205".equals(q.getQuestionTypeId());
            int weight = isObj ? 1 : 2;
            // 按权重比例分配，最后一道题修正为凑满100
            int score = (i == allSelected.size() - 1)
                    ? 100 - (int) totalScore
                    : (int) Math.round(100.0 * weight / totalWeight);
            if (score < 1) score = 1;

            PmTestpaperQuestions link = new PmTestpaperQuestions();
            link.setId(CommonFunctions.generateEnhancedID("pm_testpaper_questions"));
            link.setPaperId(paperId);
            link.setLibId(q.getId());
            link.setScore(score);
            link.setSort(i + 1);
            links.add(link);
            totalScore += score;
        }
        paperQuestionsMapper.batchInsert(links);

        // 7. 更新试卷统计信息
        paper.setQuestionsCount(allSelected.size());
        paper.setTotalScore(totalScore);
        paperMapper.updateInfo(paper);

        Map<String, Object> result = new HashMap<>();
        result.put("paper", paper);
        result.put("questions", allSelected);
        return Result.success(result);
    }

    /**
     * 从分组题库中抽题：从目标难度向下逐级抽取
     */
    private List<TmTestquelib> drawQuestions(
            Map<String, Map<Integer, List<TmTestquelib>>> grouped,
            String questionTypeId, int targetLevel, int needAmount) {

        List<TmTestquelib> result = new ArrayList<>();
        if (needAmount <= 0) return result;

        Map<Integer, List<TmTestquelib>> typeGroup = grouped.get(questionTypeId);
        if (typeGroup == null || typeGroup.isEmpty()) return result;

        for (int level = targetLevel; level >= 1; level--) {
            List<TmTestquelib> levelQuestions = typeGroup.get(level);
            if (levelQuestions == null || levelQuestions.isEmpty()) continue;

            // 随机打乱
            List<TmTestquelib> shuffled = new ArrayList<>(levelQuestions);
            Collections.shuffle(shuffled);

            int remain = needAmount - result.size();
            int take = Math.min(remain, shuffled.size());
            result.addAll(shuffled.subList(0, take));

            if (result.size() >= needAmount) break;
        }
        return result;
    }


    @Override
    public Result manualGenerate(PaperManualGenerateRequest request) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String paperId = CommonFunctions.generateEnhancedID("pm_testpaper");

        // 创建试卷
        PmTestpaper paper = new PmTestpaper();
        paper.setId(paperId);
        paper.setName(request.getName());
        paper.setMakeModeId("1");  // 1=选题组卷
        paper.setMakeModeName("选题组卷");
        paper.setCatelog(request.getCatelog());
        paper.setCourseId(request.getCourseId());
        paper.setClassroomId(request.getClassroomId());
        paper.setCourseName(classroomMapper.getCourseNameById(request.getCourseId()));
        paper.setClassroomName(classroomMapper.getClassroomNameById(request.getClassroomId()));
        paper.setCreatorId(request.getCreatorId());
        paper.setCreator(request.getCreator());
        paper.setCreateTime(now);
        paper.setStatus(0);
        paper.setLocked(0);
        paperMapper.insert(paper);

        // 创建题目关联
        List<PmTestpaperQuestions> links = new ArrayList<>();
        long totalScore = 0;
        for (int i = 0; i < request.getLibIds().size(); i++) {
            int score = request.getScores() != null && i < request.getScores().size()
                    ? request.getScores().get(i) : 5;

            PmTestpaperQuestions link = new PmTestpaperQuestions();
            link.setId(CommonFunctions.generateEnhancedID("pm_testpaper_questions"));
            link.setPaperId(paperId);
            link.setLibId(request.getLibIds().get(i));
            link.setScore(score);
            link.setSort(i + 1);
            links.add(link);
            totalScore += score;
        }
        paperQuestionsMapper.batchInsert(links);

        paper.setQuestionsCount(request.getLibIds().size());
        paper.setTotalScore(totalScore);
        paperMapper.updateInfo(paper);

        return Result.success(paper);
    }

    @Override
    public Result getPaperList(String courseId, String classroomId) {
        return Result.success(paperMapper.getListByCourseId(courseId, classroomId));
    }

    @Override
    public Result getPaperQuestions(String paperId) {
        List<PmTestpaperQuestions> links = paperQuestionsMapper.getByPaperId(paperId);
        List<String> libIds = new ArrayList<>();
        for (PmTestpaperQuestions l : links) {
            libIds.add(l.getLibId());
        }
        List<TmTestquelib> questions = libIds.isEmpty()
                ? new ArrayList<>()
                : questionMapper.getQuestionsByIds(libIds);
        return Result.success(questions);
    }

    @Override
    public Result publishPaper(String paperId, String classroomId) {
        // 1. 获取试卷
        PmTestpaper paper = paperMapper.getById(paperId);
        if (paper == null) return Result.error("试卷不存在");

        // 2. 创建pm_test发布记录
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String testId = CommonFunctions.generateEnhancedID("pm_test");

        PmTest test = new PmTest();
        test.setId(testId);
        test.setPaperId(paperId);
        test.setPaperName(paper.getName());
        test.setName(paper.getName());
        test.setTotalScore(String.valueOf(paper.getTotalScore()));
        test.setStatus(1);  // 已发布
        test.setCourseId(paper.getCourseId());
        test.setClassroomId(classroomId);
        test.setCatelog(paper.getCatelog());
        test.setCreatorId(paper.getCreatorId());
        test.setCreator(paper.getCreator());
        test.setCreatTime(now);
        test.setPublishTime(now);
        testMapper.insert(test);

        // 3. 关联课堂学生
        List<CmClassroomStudent> students = classroomStudentMapper.getStudentsByClassroomId(classroomId);
        List<PmTestStudents> testStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            CmClassroomStudent s = students.get(i);
            PmTestStudents ts = new PmTestStudents();
            ts.setId(CommonFunctions.generateEnhancedID("pm_test_students"));
            ts.setTestId(testId);
            ts.setUserId(s.getUserId());
            ts.setUserName(s.getUserName());
            ts.setObsId(s.getObsId());
            ts.setObsName(s.getObsName());
            ts.setLoginname(s.getLoginname());
            ts.setRowNo(i + 1);
            testStudents.add(ts);
        }
        if (!testStudents.isEmpty()) {
            testStudentsMapper.batchInsert(testStudents);
        }

        // 4. 更新试卷状态
        paperMapper.updateStatus(paperId, 1);

        Map<String, Object> result = new HashMap<>();
        result.put("test", test);
        result.put("studentCount", testStudents.size());
        return Result.success(result);
    }

    @Override
    public Result deletePaper(String paperId) {
        paperMapper.deleteById(paperId);  // 软删除 status=3
        return Result.success();
    }
}
