package com.example.smartttexam.service.Impl;

import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.Utils.NormalDistributionUtil;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.ScoreSimulationRequest;
import com.example.smartttexam.mapper.*;
import com.example.smartttexam.pojo.*;
import com.example.smartttexam.service.ScoreSimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreSimulationServiceImpl implements ScoreSimulationService {

    @Autowired
    private PmTestpaperMapper paperMapper;

    @Autowired
    private PmTestMapper testMapper;

    @Autowired
    private PmTestpaperQuestionsMapper paperQuestionsMapper;

    @Autowired
    private TmTestquelibExtMapper questionMapper;

    @Autowired
    private CmClassroomStudentMapper classroomStudentMapper;

    @Autowired
    private AiInStuAnsInfoMapper aiInStuAnsInfoMapper;

    @Autowired
    private StUsersMapper stUsersMapper;

    @Autowired
    private CmClassroomMypracticelistMapper mypracticelistMapper;

    @Autowired
    private TmTestquelibKwaMapper tmTestquelibKwaMapper;

    @Autowired
    private NormalDistributionUtil normalDistributionUtil;

    @Override
    public Result simulateScores(ScoreSimulationRequest request) {
        // 1. 确定 testId：如果传了paperId，找它的pm_test
        String testId = request.getTestId();
        String paperId = request.getPaperId();
        PmTestpaper paper;

        if (testId != null) {
            PmTest test = testMapper.getById(testId);
            if (test == null) return Result.error("考试记录不存在");
            paperId = test.getPaperId();
            paper = paperMapper.getById(paperId);
        } else if (paperId != null) {
            paper = paperMapper.getById(paperId);
            // 找已发布的test
            PmTest test = testMapper.getByPaperId(paperId);
            if (test != null) testId = test.getId();
        } else {
            return Result.error("请指定 paperId 或 testId");
        }

        if (paper == null) return Result.error("试卷不存在");
        if (testId == null) return Result.error("试卷尚未发布，请先发布后再模拟作答");

        // 检查是否已有作答记录
        if (!aiInStuAnsInfoMapper.getByTestId(testId).isEmpty()) {
            return Result.error("该试卷已有学生作答记录，不允许重复模拟");
        }

        String classroomId = request.getClassroomId();
        if (classroomId == null) {
            classroomId = paper.getClassroomId();
        }

        // 2. 获取试卷中的题目
        List<PmTestpaperQuestions> links = paperQuestionsMapper.getByPaperId(paperId);
        if (links.isEmpty()) return Result.error("试卷中没有题目");

        List<String> libIds = new ArrayList<>();
        for (PmTestpaperQuestions l : links) libIds.add(l.getLibId());
        List<TmTestquelib> questions = questionMapper.getQuestionsByIds(libIds);

        // 建立 libId → score 映射
        Map<String, Long> libScoreMap = new HashMap<>();
        for (PmTestpaperQuestions l : links) {
            libScoreMap.put(l.getLibId(), l.getScore());
        }

        // 建立 libId → kwaId 映射
        List<TmTestquelibKwa> kwaList = tmTestquelibKwaMapper.getByLibIds(libIds);
        Map<String, String> libKwaMap = new HashMap<>();
        for (TmTestquelibKwa kwa : kwaList) {
            if (!libKwaMap.containsKey(kwa.getLibId())) {
                libKwaMap.put(kwa.getLibId(), kwa.getKwaId());
            }
        }

        // 3. 获取课堂学生
        List<CmClassroomStudent> students = classroomStudentMapper.getStudentsByClassroomId(classroomId);
        if (students.isEmpty()) return Result.error("课堂中没有学生");

        // 4. 为每道题生成每个学生的分数
        List<AiInStuAnsInfo> answerList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (TmTestquelib question : questions) {
            int difficulty = (int) question.getDifficultyLevel();
            if (difficulty < 1) difficulty = 1;
            if (difficulty > 3) difficulty = 3;

            Long libScoreLong = libScoreMap.get(question.getId());
            int maxScore = libScoreLong != null ? libScoreLong.intValue() : 5;

            // 正态分布生成全班学生在这道题上的分数
            int[] scores = normalDistributionUtil.generateBatchScores(difficulty, maxScore, students.size());

            for (int i = 0; i < students.size(); i++) {
                CmClassroomStudent stu = students.get(i);

                AiInStuAnsInfo ans = new AiInStuAnsInfo();
                ans.setId(CommonFunctions.generateEnhancedID("ai_in_stu_ans_info"));
                ans.setLogId(CommonFunctions.generateEnhancedID("ai_log"));
                ans.setCourseId(paper.getCourseId());
                ans.setClassroomId(classroomId);
                ans.setTestId(testId);
                ans.setPaperId(paperId);
                ans.setLibId(question.getId());
                ans.setStuId(stu.getUserId());
                ans.setQuestionTypeId(question.getQuestionTypeId());
                ans.setQuestionContent(question.getContent());
                ans.setDifficultLevel(question.getDifficultyLevel());
                ans.setLibScore(maxScore);
                ans.setLibStuScore(scores[i]);
                ans.setKwaId(libKwaMap.get(question.getId()));
                ans.setCreateTime(now);
                answerList.add(ans);
            }
        }

        // 5. 批量插入作答详情（INSERT IGNORE 防重复）
        int inserted = aiInStuAnsInfoMapper.batchInsert(answerList);

        // 6. 计算每个学生的总分，写入汇总表
        Map<String, Long> stuTotalScore = new LinkedHashMap<>();
        for (AiInStuAnsInfo ans : answerList) {
            stuTotalScore.merge(ans.getStuId(), ans.getLibStuScore(), Long::sum);
        }

        long paperTotalScore = paper.getTotalScore();
        List<CmClassroomMypracticelist> practiceList = new ArrayList<>();
        for (CmClassroomStudent stu : students) {
            long stuScore = stuTotalScore.getOrDefault(stu.getUserId(), 0L);
            int correctPercent = paperTotalScore > 0
                    ? (int) Math.round(stuScore * 100.0 / paperTotalScore) : 0;

            CmClassroomMypracticelist rec = new CmClassroomMypracticelist();
            rec.setId(CommonFunctions.generateEnhancedID("cm_classroom_mypracticelist"));
            rec.setClassroomId(classroomId);
            rec.setTestId(testId);
            rec.setPaperId(paperId);
            rec.setStuId(stu.getUserId());
            rec.setResultScore(stuScore);
            rec.setMyFinishStatus(1L);  // 已完成
            rec.setCorrectCount(0L);
            rec.setErrorCount(0L);
            rec.setCorrectPercent((long) correctPercent);
            rec.setCatelog(paper.getCatelog());
            rec.setBeginTime(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            rec.setPublishTime(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            rec.setState(1L);  // 正常
            practiceList.add(rec);
        }
        mypracticelistMapper.batchInsert(practiceList);

        Map<String, Object> result = new HashMap<>();
        result.put("insertedRecords", inserted);
        result.put("questionCount", questions.size());
        result.put("studentCount", students.size());

        // 简要的分数统计
        List<Map<String, Object>> stuScores = new ArrayList<>();
        for (CmClassroomStudent stu : students) {
            Map<String, Object> info = new HashMap<>();
            info.put("stuId", stu.getUserId());
            info.put("stuName", stu.getUserName());
            info.put("totalScore", stuTotalScore.getOrDefault(stu.getUserId(), 0L));
            stuScores.add(info);
        }
        result.put("studentScores", stuScores);

        return Result.success(result);
    }

    @Override
    public Result getSimulatedScores(String testId, String paperId, String stuId) {
        if (testId == null && paperId != null) {
            PmTest test = testMapper.getByPaperId(paperId);
            if (test != null) testId = test.getId();
        }
        if (testId == null) return Result.error("未找到考试记录");

        List<AiInStuAnsInfo> details;
        if (stuId != null) {
            details = aiInStuAnsInfoMapper.getByTestIdAndStuId(testId, stuId);
        } else {
            details = aiInStuAnsInfoMapper.getByTestId(testId);
        }

        // 收集所有stuId，批量查姓名
        Map<String, String> nameMap = new HashMap<>();
        if (!details.isEmpty()) {
            List<String> ids = details.stream().map(AiInStuAnsInfo::getStuId).distinct().collect(Collectors.toList());
            List<Map<String, String>> users = stUsersMapper.getNamesByIds(ids);
            for (Map<String, String> u : users) {
                nameMap.put(u.get("id"), u.getOrDefault("username", u.get("id")));
            }
        }

        // 按学生分组：stuId → { name, questions[], totalScore }
        Map<String, Map<String, Object>> grouped = new LinkedHashMap<>();
        for (AiInStuAnsInfo d : details) {
            String sid = d.getStuId();
            grouped.putIfAbsent(sid, new HashMap<>());
            Map<String, Object> stu = grouped.get(sid);
            stu.putIfAbsent("stuId", sid);
            stu.putIfAbsent("stuName", nameMap.getOrDefault(sid, sid));
            stu.putIfAbsent("totalScore", 0L);
            stu.put("totalScore", (Long) stu.get("totalScore") + d.getLibStuScore());
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> qs = (List<Map<String, Object>>) stu.computeIfAbsent("questions", k -> new ArrayList<>());
            Map<String, Object> q = new HashMap<>();
            q.put("libId", d.getLibId());
            q.put("content", d.getQuestionContent());
            q.put("libScore", d.getLibScore());
            q.put("libStuScore", d.getLibStuScore());
            qs.add(q);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("students", new ArrayList<>(grouped.values()));
        result.put("totalCount", details.size());
        return Result.success(result);
    }
}
