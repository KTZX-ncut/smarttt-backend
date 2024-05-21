package com.example.smartttadmin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CmCourseMapper {
    @Select("select courseChineseName from cm_course where id = #{obsid}")
    String getCourseName(String obsid);

    @Select("select courseChineseName from cm_course where id = (select courseId from cm_classroom where id = #{obsid})")
    String getCourseNameByClassroom(String obsid);
}
