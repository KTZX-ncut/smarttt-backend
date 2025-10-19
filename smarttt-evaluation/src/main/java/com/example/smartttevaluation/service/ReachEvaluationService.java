package com.example.smartttevaluation.service;


import com.example.smartttevaluation.pojo.CmClassroomStudent;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import com.example.smartttevaluation.vo.ReachCategoryEvaluationVO;
import com.example.smartttevaluation.vo.ReachObjectiveEvaluationVO;

import java.util.List;

public interface ReachEvaluationService {
    String getCourseIdByClassroomId(String classroomId);

    void calculate(List<CmClassroomStudent> classroomStudentList,
                   List<FeCourseObjectives> courseObjectivesList,
                   List<FeAssessmentCategories> categoriesList,
                   String classroomId,
                   String courseId);

    List<ReachCategoryEvaluationVO> getReachCategoryEvaluation(String classroomId);

    String getStuNoByUserId(String userId);

    String getStudentNameByUserId(String userId);

    List<ReachObjectiveEvaluationVO> getReachObjectiveEvaluation(String classroomId);

    List<String> getUserIdList(String classroomId);
}
