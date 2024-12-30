package com.example.smartttevaluation.mapper;


import org.apache.ibatis.annotations.Param;

public interface PortraitTotalMapper {

    int insert(@Param("courseId") String courseId,
                @Param("classroomId") String classroomId,
                @Param("totalNums") int totalNums);


    int update(@Param("courseId") String courseId,
                @Param("classroomId") String classroomId,
                @Param("totalNums") int totalNums);

    Integer selectByCondition(@Param("courseId") String courseId,
                               @Param("classroomId") String classroomId);

    Integer getNumByCondition(@Param("courseId") String courseId, @Param("classroomId") String classroomId);
}
