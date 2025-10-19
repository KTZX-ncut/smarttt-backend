package com.example.smartttevaluation.mapper;


import com.example.smartttevaluation.dto.ReachEvaluationDto;
import com.example.smartttevaluation.vo.ReachCategoryEvaluationVO;
import com.example.smartttevaluation.vo.ReachObjectiveEvaluationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReachEvaluationMapper {

    String getCourseIdByClassroomId(@Param("classroomId") String classroomId);

    ReachEvaluationDto searchReachEvaluationData(@Param("typeId") String typeId, @Param("userId") String userId);

    String getStuNoByUserId(@Param("userId") String userId);

    ReachEvaluationDto searchExternalData(@Param("typeId") String typeId, @Param("stuNo") String stuNo);

    Double getObjectiveScore(@Param("objectiveId") String objectiveId, @Param("categoryId") String categoryId);

    Integer searchReachEvaluationResultCount(@Param("objectiveId") String objectiveId,
                                             @Param("userId") String userId,
                                             @Param("classroomId") String classroomId);

    void updateAchievement(@Param("reachScore") Double reachScore,
                           @Param("objectiveId") String objectiveId,
                           @Param("userId") String userId,
                           @Param("classroomId") String classroomId,
                           @Param("courseId") String courseId);

    void insertAchievement(@Param("id") String id,
                            @Param("reachScore") Double reachScore,
                           @Param("objectiveId") String objectiveId,
                           @Param("userId") String userId,
                           @Param("classroomId") String classroomId,
                           @Param("courseId") String courseId);

    Integer searchReachCategoryEvaluationResultCount(@Param("categoryId") String categoryId,
                                                     @Param("userId") String userId,
                                                     @Param("classroomId") String classroomId);

    void updateCategoryAchievement(@Param("reachScore") Double reachScore,
                                   @Param("categoryId") String categoryId,
                                   @Param("userId") String userId,
                                   @Param("classroomId") String classroomId,
                                   @Param("courseId") String courseId);

    void insertCategoryAchievement(@Param("id") String id,
                                   @Param("reachScore") Double reachScore,
                                   @Param("categoryId") String categoryId,
                                   @Param("userId") String userId,
                                   @Param("classroomId") String classroomId,
                                   @Param("courseId") String courseId);

    List<ReachCategoryEvaluationVO> getReachCategoryEvaluation(@Param("classroomId") String classroomId);

    String getStudentNameByUserId(@Param("userId") String userId);

    List<ReachObjectiveEvaluationVO> getReachObjectiveEvaluation(@Param("classroomId") String classroomId);

    List<String> getUserIdList(@Param("classroomId") String classroomId);
}
