package com.example.smartttevaluation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.smartttevaluation.pojo.CmClassroom;
import com.example.smartttevaluation.dto.*;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.AttainmentEvaluationMapper;
import com.example.smartttevaluation.mapper.CmCourseAssessmentMapper;
import com.example.smartttevaluation.mapper.CmCoursetargetMapper;
import com.example.smartttevaluation.mapper.CmKwadictMapper;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.pojo.CmCourse;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.service.AttainmentEvaluationService;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class AttainmentEvaluationImpl implements AttainmentEvaluationService {
    @Autowired
    private AttainmentEvaluationMapper attainmentEvaluationMapper;
    @Autowired
    private CmCourseAssessmentService cmCourseAssessmentService;
    @Autowired
    private CmCourseAssessmentMapper cmCourseAssessmentMapper;
    @Autowired
    private CmCoursetargetMapper cmCoursetargetMapper;
    @Autowired
    private CmKwadictMapper cmKwadictMapper;

    @Override
    public Result checkRole(String obsId) {
        JSONObject res = new JSONObject();
        boolean isCourseManager = attainmentEvaluationMapper.checkRole(obsId) > 0;
        res.put("isCourseManager", isCourseManager);
        if (!isCourseManager) {
            res.put("classroomName", attainmentEvaluationMapper.getClassroomByClassroomId(obsId).getClassroomName());
        }
        return Result.success(res);
    }

    @Override
    public Result getClassroomStuList(String classroomId) {
        return Result.success(attainmentEvaluationMapper.getClassroomStuList(classroomId));
    }

    public void pakageClassroomInfo(ClassroomInfoReq classroomInfoReq, CmClassroom cmClassroom, String obsId) {
        classroomInfoReq.setClassroomId(cmClassroom.getId());
        classroomInfoReq.setClassroomName(cmClassroom.getClassroomName());
        classroomInfoReq.setTermName(attainmentEvaluationMapper.getTermName(cmClassroom.getTermId()));

        CmCourse course = attainmentEvaluationMapper.getCourseByCourseId(obsId);
        if (course == null)  // 如果是任课教师，要使用根据课堂id的查询函数
            course = attainmentEvaluationMapper.getCourseByClassroomId(obsId);

        classroomInfoReq.setCourseName(course.getCoursechinesename());
        classroomInfoReq.setProfessionName(attainmentEvaluationMapper.getProfessionName(obsId));
        classroomInfoReq.setTime(cmClassroom.getTeachTime() + cmClassroom.getLabTime() + cmClassroom.getPracticeTime());
        classroomInfoReq.setTeacherName(cmClassroom.getTeacherName());
    }

    @Override
    public Result getClassroomByCourseId(String obsId) {
        List<CmClassroom> classroomList = attainmentEvaluationMapper.getClassroomByCourseId(obsId);
        List<ClassroomInfoReq> classroomInfoReqs = new ArrayList<>();
        for (CmClassroom cmClassroom : classroomList) {
            ClassroomInfoReq classroomInfoReq = new ClassroomInfoReq();
            pakageClassroomInfo(classroomInfoReq, cmClassroom, obsId);
            classroomInfoReqs.add(classroomInfoReq);
        }
        return Result.success(classroomInfoReqs);
    }

    @Override
    public Result getClassroomByClassroomId(String obsId) {
        CmClassroom cmClassroom = attainmentEvaluationMapper.getClassroomByClassroomId(obsId);
        ClassroomInfoReq classroomInfoReq = new ClassroomInfoReq();
        pakageClassroomInfo(classroomInfoReq, cmClassroom, obsId);
        return Result.success(classroomInfoReq);
    }

    @Override
    public Result getCourseByClassroomId(String obsId) {
        return Result.success(attainmentEvaluationMapper.getCourseByClassroomId(obsId));
    }

    @Override
    public Result calcTotalScore(String classroomId) {
        attainmentEvaluationMapper.cleanTotalScore(classroomId);
        List<CmAssessmentStudent> studentList = attainmentEvaluationMapper.getClassroomStuList(classroomId);
        if (studentList.isEmpty()) return Result.error("课堂未指定学生");

        String courseId = attainmentEvaluationMapper.getCourseByClassroomId(classroomId).getId();
        Result result = cmCourseAssessmentService.getAssessmentTable(courseId);

        CmCourseAssessmentTable assessmentTable = (CmCourseAssessmentTable) result.getData();
        if (assessmentTable.getHead().isEmpty()) return Result.error("请先设置考核项");

        // 获取每个叶考核项的根节点id
        Map<String, String> leafAndRootMap = new HashMap<>();
        // 存放所有叶考核项id
        List<String> leafIds = new ArrayList<>();
        getLeafAndRoot(leafAndRootMap, assessmentTable.getHead(), "", leafIds, 0);

        List<JSONObject> items = assessmentTable.getItems();
        JSONObject percent = assessmentTable.getPercent();
        // 计算每个学生各个叶考核项的得分
        for (String checkitemId : leafIds) {
            int checkitemStandardScore = 0;
            for (JSONObject json : items) {
                if (json.get(checkitemId) != null) {
                    // 计算叶考核项的总分
                    checkitemStandardScore += (int) json.get(checkitemId);
                }
            }

            // 获取当前考核项关联的文件及其来源(type字段)
            List<CmAssessmentFile> associateFiles = cmCourseAssessmentMapper.getAssociateFiles(checkitemId, classroomId);
            // 对每一位学生，计算其当前考核项所有关联文件的平均分
            for (CmAssessmentStudent stu : studentList) {
                float aver = 0;
                for (CmAssessmentFile file : associateFiles) {
                    if (file.getType() == 1) {
                        Integer score = attainmentEvaluationMapper.calcStuTestpaperScore(stu.getId(), file.getId());
                        if (score != null) aver += score;
                    } else if (file.getType() == 4) {
                        float score = cmCourseAssessmentMapper.getUploadFileScore(file.getId(), stu.getId());
                        aver += score;
                    }
                }
                if (!associateFiles.isEmpty()) {
                    aver /= associateFiles.size();
                }

                // 获取当前考核项的根节点的占比
                Integer rootPercent = (Integer) percent.get(leafAndRootMap.get(checkitemId));
                if (rootPercent == null) rootPercent = 0;
                float ratio = rootPercent / 100f;
                // 设置为当前考核项分数对总评分数的占比
                ratio = (checkitemStandardScore / 100f) * ratio;

                String id = generateEnhancedID("cm_classroom_total_score");
                ClassroomTotalScore classroomTotalScore = new ClassroomTotalScore(id, stu.getId(), classroomId, checkitemId, ratio, aver);
                attainmentEvaluationMapper.setTotalScoreData(classroomTotalScore);
            }
        }

//        return calcTargetAchievement(classroomId, studentList, leafIds, assessmentTable);
        return calcTargetAchievementWithoutKwa(classroomId, studentList, leafIds, assessmentTable);
    }

    public void getLeafAndRoot(Map<String, String> leafAndRootMap, List<CmCheckitem> checkitems, String rootId, List<String> leafIds, int floor) {
        for (CmCheckitem checkitem : checkitems) {
            if (floor == 0) {
                rootId = checkitem.getId();
            }
            if (checkitem.getChildren() != null && !checkitem.getChildren().isEmpty()) {
                getLeafAndRoot(leafAndRootMap, checkitem.getChildren(), rootId, leafIds, floor + 1);
            } else {
                leafAndRootMap.put(checkitem.getId(), rootId);
                leafIds.add(checkitem.getId());
            }
        }
    }

    public Result calcTargetAchievement(String classroomId, List<CmAssessmentStudent> studentList, List<String> leafIds, CmCourseAssessmentTable assessmentTable) {
        attainmentEvaluationMapper.cleanTargetAchievement(classroomId);

        String courseId = attainmentEvaluationMapper.getCourseByClassroomId(classroomId).getId();
        List<CmCoursetarget> targets = cmCoursetargetMapper.getCoursetarget(courseId);
        // 考核项和关联文件的映射
        Map<String, List<CmAssessmentFile>> checkitemAssociateFiles = new HashMap<>();
        for (String checkitemId : leafIds) {
            List<CmAssessmentFile> associateFiles = cmCourseAssessmentMapper.getAssociateFiles(checkitemId, classroomId);
            checkitemAssociateFiles.put(checkitemId, associateFiles);
        }

        // 每个课程目标关联的kwa的映射
        Map<String, List<CmKwadict>> targetKwa = new HashMap<>();
        for (CmCoursetarget target : targets) {
            List<CmKwadict> kwas = cmCoursetargetMapper.getKwas(target.getId(), courseId);
            targetKwa.put(target.getId(), kwas);
        }

        // 每个课程目标的考核项的分数映射
        Map<String, Map<String, Integer>> targetCheckitemScore = new HashMap<>();
        // 与每个课程目标的每个kwa有关的考核项的总分映射
        Map<String, Map<String, Integer>> targetKwaCheckitemTotalScore = new HashMap<>();
        for (JSONObject json : assessmentTable.getItems()) {
            List<CmKwadict> kwas = cmCoursetargetMapper.getKwas((String) json.get("id"), courseId);
            for (CmKwadict kwa : kwas) {
                int totalScore = 0;
                for (String checkitemId : leafIds) {
                    List<CmAssessmentFile> assessmentFiles = checkitemAssociateFiles.get(checkitemId);

                    // 记录当前考核项关联的文件中与当前kwa有关的题目数量
                    int libCount = 0;
                    for (CmAssessmentFile file : assessmentFiles) {
                        libCount += attainmentEvaluationMapper.getLibCountByKwaId(file.getId(), kwa.getId());
                    }
                    // 不为0时再把这个考核项的分数加到课程目标-kwa-考核项总分的映射中
                    Integer score = (Integer) json.get(checkitemId);
                    if (score == null) score = 0;
                    if (libCount != 0) totalScore += score;

                    if (json.get(checkitemId) != null) {
                        targetCheckitemScore.computeIfAbsent((String) json.get("id"), k -> new HashMap<>())
                                .putIfAbsent(checkitemId, (int) json.get(checkitemId));
                    }
                }
                targetKwaCheckitemTotalScore.computeIfAbsent((String) json.get("id"), k -> new HashMap<>())
                        .merge(kwa.getId(), totalScore, Integer::sum);
            }
        }

        // 计算课程目标达成度
        for (CmAssessmentStudent stu : studentList) {
            if (stu.getEvaluationState() == 0) continue;
            for (CmCoursetarget target : targets) {
                // 存储当前课程目标的达成度
                float targetAchievement = 0;

                // 获取当前课程目标关联的kwa
                List<CmKwadict> kwas = targetKwa.get(target.getId());
                // 存放kwa的重要程度总和，用于计算重要程度的权重
                float totalImportantLevel = 0;
                for (CmKwadict kwa : kwas) {
                    totalImportantLevel += Float.parseFloat(kwa.getImportantlevelid());
                }

                for (CmKwadict kwa : kwas) {
                    // 存放当前课程目标对应的考核项的当前kwa的达成度
                    float kwaAchievement = 0;
                    for (String checkitemId : leafIds) {
                        // 存储当前考核项分数在与当前课程目标中的当前kwa有关的考核项的总分中的占比
                        float checkitemRatio = 0;
                        int totalScore = targetKwaCheckitemTotalScore.get(target.getId()).get(kwa.getId());
                        if (totalScore != 0) {
                            Integer score = targetCheckitemScore.get(target.getId()).get(checkitemId);
                            // 当前课程目标的考核项没有设置分值，直接设为0
                            if (score == null) score = 0;
                            checkitemRatio = (float) score / totalScore;
                        }
                        // 没设分值就不用做后续计算，结果都会是0
                        if (checkitemRatio == 0) continue;

                        // 获取当前考核项关联的文件列表
                        List<CmAssessmentFile> associateFiles = checkitemAssociateFiles.get(checkitemId);
                        // 当前文件的得分率
                        float scoreRatio = 0;
                        int file1Count = 0;
                        for (CmAssessmentFile file : associateFiles) {
                            if (file.getType() == 1) {
                                file1Count++;
                                // 当前学生获得的分数
                                Integer stuScore = attainmentEvaluationMapper.calcStuTestpaperScoreByKwa(stu.getId(),
                                        file.getId(), kwa.getId());
                                if (stuScore == null) stuScore = 0;
                                // 满分是多少
                                Integer fullScore = attainmentEvaluationMapper.calcTestPaperScoreByKwa(stu.getId(),
                                        file.getId(), kwa.getId());
                                if (fullScore == null) fullScore = 0;
                                // 计算这个文件的得分率，加到总和里，最后算均值
                                if (fullScore != 0) scoreRatio += (float) stuScore / fullScore;
                            }
                        }
                        // 算当前考核项所有关联文件得分率的平均值
//                        if(!associateFiles.isEmpty()) scoreRatio /= associateFiles.size();
                        if (file1Count != 0) scoreRatio /= file1Count;
                        // 得分率乘以考核项的权重作为当前kwa达成度的一部分加进去
                        kwaAchievement += checkitemRatio * scoreRatio;
                    }
                    // 把当前课程目标的每个kwa的达成度乘以其重要程度的权重，相加即是课程目标的达成度
                    if (totalImportantLevel != 0) {
                        targetAchievement += kwaAchievement * Float.parseFloat(kwa.getImportantlevelid()) / totalImportantLevel;
                    }
                }
                ClassroomTargetAchievement classroomTargetAchievement = new ClassroomTargetAchievement(
                        generateEnhancedID("cm_classroom_target_achievement"), stu.getId(), classroomId,
                        target.getId(), targetAchievement);
                attainmentEvaluationMapper.setTargetAchievement(classroomTargetAchievement);
            }
        }
        return Result.success();
    }

    public Result calcTargetAchievementWithoutKwa(String classroomId, List<CmAssessmentStudent> studentList, List<String> leafIds, CmCourseAssessmentTable assessmentTable) {
        attainmentEvaluationMapper.cleanTargetAchievement(classroomId);

        // 考核项和关联文件的映射
        Map<String, List<CmAssessmentFile>> checkitemAssociateFiles = new HashMap<>();
        for (String checkitemId : leafIds) {
            List<CmAssessmentFile> associateFiles = cmCourseAssessmentMapper.getAssociateFiles(checkitemId, classroomId);
            checkitemAssociateFiles.put(checkitemId, associateFiles);
        }

        // 存储每个课程目标的考核项总分
        Map<String, Integer> targetCheckitemTotalScore = new HashMap<>();
        for (JSONObject json : assessmentTable.getItems()) {
            for (String checkitemId : leafIds) {
                Integer score = (Integer) json.get(checkitemId);
                if (score != null) {
                    json.putIfAbsent("totalScore", 0);
                    Integer checkitemTotalScore = (Integer) json.get("totalScore");
                    checkitemTotalScore += score;
                    json.put("totalScore", checkitemTotalScore);
                }
            }
        }
        for (CmAssessmentStudent stu : studentList) {
            if (stu.getEvaluationState() == 0) continue;
            for (JSONObject json : assessmentTable.getItems()) {
                // 存储当前课程目标的达成度
                float targetAchievement = 0;
                for (String checkitemId : leafIds) {
                    Integer checkitemScore = (Integer) json.get(checkitemId);
                    if (checkitemScore != null) {
                        float scoreRatio = 0;
                        int fileCount = 0;
                        for (CmAssessmentFile file : checkitemAssociateFiles.get(checkitemId)) {
                            if (file.getType() == 1) {
                                fileCount++;
                                // 当前学生获得的分数
                                Integer stuScore = attainmentEvaluationMapper.calcStuTestpaperScore(stu.getId(),
                                        file.getId());
                                if (stuScore == null) stuScore = 0;
                                // 满分是多少
                                Integer fullScore = attainmentEvaluationMapper.calcTestPaperScore(stu.getId(),
                                        file.getId());
                                if (fullScore == null) fullScore = 0;
                                // 计算这个文件的得分率，加到总和里，最后算均值
                                if (fullScore != 0) scoreRatio += (float) stuScore / fullScore;
                                fileCount++;
                            } else if (file.getType() == 4) {
                                scoreRatio += (float) attainmentEvaluationMapper.getCustomFileStudentScore(stu.getId(),
                                        file.getId()) / 100;
                                fileCount++;
                            }
                        }
                        if (fileCount != 0) scoreRatio /= fileCount;

                        Integer checkitemTotalScore = (Integer) json.get("totalScore");
                        if (checkitemTotalScore != null && checkitemTotalScore != 0) {
                            targetAchievement += scoreRatio * ((float) checkitemScore / (float) checkitemTotalScore);
                        }
                    }
                }
                ClassroomTargetAchievement classroomTargetAchievement = new ClassroomTargetAchievement(
                        generateEnhancedID("cm_classroom_target_achievement"), stu.getId(), classroomId,
                        (String) json.get("id"), targetAchievement);
                attainmentEvaluationMapper.setTargetAchievement(classroomTargetAchievement);
            }
        }
        return Result.success();
    }

    @Override
    public Result getTotalScore(String classroomId) {
        List<ClassroomTotalScore> score = attainmentEvaluationMapper.getTotalScore(classroomId);
        return Result.success(score);
    }

    @Override
    public Result getTargetAchievement(String classroomId) {
        List<ClassroomTargetAchievement> res = attainmentEvaluationMapper.getTargetAchievement(classroomId);
        return Result.success(res);
    }
}
