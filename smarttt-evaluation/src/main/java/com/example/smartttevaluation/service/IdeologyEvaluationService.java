package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.StudentIdeologyEvalDto;
import com.example.smartttevaluation.dto.StudentIdeologyStateReq;
import com.example.smartttevaluation.pojo.*;

import java.util.List;

public interface IdeologyEvaluationService {
    boolean modifyStudentIdeologyState(List<StudentIdeologyStateReq> studentIdeologyStateReqList);

    List<IdeologyCalculatePaper> getPaperList(String classroomId);

    void calculate(List<CmClassroomStudent> classroomStudentList, List<IdeologyCalculatePaper> paperList, String classroomId, String courseId,List<IdeologyValue> ideologyValueList);

    String getCourseIdByClassroomId(String classroomId);

    List<StudentIdeologyEvaluation> getIdeologyEvaluationByUserId(String userId, String classroomId);

    List<ClassroomIdeologyEvaluation> getIdeologyEvaluationByClassroomId(String classroomId);

    List<StudentIdeologyEvaluation> getAllStuIdeologyEvaluation(String classroomId);

    List<StudentIdeologyEvalDto> getAllEvalStu(String classroomId);
}
