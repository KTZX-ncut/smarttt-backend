package com.example.smartttevaluation.mapper;


import com.example.smartttevaluation.dto.IdeologyEvalDto;
import com.example.smartttevaluation.pojo.ClassroomIdeologyEvaluation;
import com.example.smartttevaluation.pojo.IdeologyCalculatePaper;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.pojo.StudentIdeologyEvaluation;
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
}
