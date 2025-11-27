package com.example.smartttevaluation.mapper;


import com.example.smartttevaluation.dto.IdeologyEvalDto;
import com.example.smartttevaluation.dto.PaperIdeologyEvaluationDto;
import com.example.smartttevaluation.dto.PaperQuestionDto;
import com.example.smartttevaluation.dto.StudentIdeologyEvalDto;
import com.example.smartttevaluation.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdeologyEvaluationMapper {

    Boolean modifyStudentIdeologyState(@Param("classroomStudentId") String classroomStudentId, @Param("ideologyState") Integer ideologyState);

    List<IdeologyCalculatePaper> getPaperList(@Param("classroomId") String classroomId);

    void deleteStudentIdeologyEvaluation(@Param("userId") String userId, @Param("classroomId") String classroomId);

    List<IdeologyEvalDto> getQuestionInfo(@Param("userId") String userId, @Param("paperIdList") List<String> paperIdList,@Param("classroomId") String classroomId,@Param("vId") String vId);

    void insertStudentIdeologyEvaluation(@Param("id") String id,@Param("userId") String userId,
                                         @Param("courseId") String courseId,
                                         @Param("vId") String vId,
                                         @Param("classroomId") String classroomId,
                                         @Param("vCount") Integer vCount);

    List<IdeologyEvalDto> getQuestionInfoClassroom(@Param("paperIdList") List<String> paperIdList,
                                                   @Param("classroomId") String classroomId,
                                                   @Param("vId") String vId);

    void insertClassroomIdeologyEvaluation(@Param("id") String id,
                                           @Param("courseId") String courseId,
                                           @Param("vId") String vId,
                                           @Param("classroomId") String classroomId,
                                           @Param("vCount") Integer vCount);

    void deleteClassroomIdeologyEvaluation(@Param("classroomId") String classroomId);

    String getCourseIdByClassroomId(@Param("classroomId") String classroomId);

    List<StudentIdeologyEvaluation> getIdeologyEvaluationByUserId(@Param("userId") String userId, @Param("classroomId") String classroomId);

    List<ClassroomIdeologyEvaluation> getIdeologyEvaluationByClassroomId(@Param("classroomId") String classroomId);

    List<StudentIdeologyEvaluation> getAllStuIdeologyEvaluation(@Param("classroomId") String classroomId);

    List<StudentIdeologyEvalDto> getAllEvalStu(@Param("classroomId") String classroomId);

    void deletePaperIdeologyEvaluation(@Param("classroomId") String classroomId);

    String getPaperType(@Param("paperId") String paperId);

    List<String> getQuestionListInPractice(@Param("paperId") String paperId);

    List<String> getQuestionListInPaper(@Param("paperId") String paperId);

    List<IdeologyEvalDto> getQuestionInfoList(@Param("questionId") String questionId,
                                              @Param("paperId") String paperId,
                                              @Param("studentList") List<CmClassroomStudent> studentList,
                                              @Param("classroomId") String classroomId);

    void insertPaperIdeologyEvaluation(@Param("id") String id,
                                       @Param("courseId") String courseId,
                                       @Param("classroomId") String classroomId,
                                       @Param("paperId") String paperId,
                                       @Param("questionId") String questionId,
                                       @Param("vId") String vid,
                                       @Param("paperType") String paperType,
                                       @Param("vCount") Integer vCount);

    PaperIdeologyEvaluationDto getPaperIdeologyEvaluation(@Param("classroomId") String classroomId, @Param("paperId") String paperId);

    List<PaperQuestionDto> getPaperIdeologyQuestionList(@Param("classroomId") String classroomId, @Param("paperId") String paperId);

    List<PaperQuestionDto> getPracticeIdeologyQuestionList(@Param("classroomId") String classroomId,@Param("paperId") String paperId);
}
