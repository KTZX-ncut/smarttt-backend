package com.example.smartttexam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 思政价值Mapper
 */
@Mapper
public interface VIdeologyValueMapper {

    /** 从当前课程随机取一个叶子节点的思政价值ID */
    @Select("SELECT id FROM v_ideology_value WHERE course_id = #{courseId} AND leaf = 1 ORDER BY RAND() LIMIT 1")
    String getRandomVIdByCourseId(@Param("courseId") String courseId);
}