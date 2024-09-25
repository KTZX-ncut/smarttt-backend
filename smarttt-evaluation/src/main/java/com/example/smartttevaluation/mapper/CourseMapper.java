package com.example.smartttevaluation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.CmCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author lunSir
 * @create 2024-09-25 14:58
 */
public interface CourseMapper extends BaseMapper<CmCourse> {
    @Select("SELECT professionId FROM cm_course WHERE id = #{courseId}")
    String getProIdByCourseId(@Param("courseId") String courseId);
}
