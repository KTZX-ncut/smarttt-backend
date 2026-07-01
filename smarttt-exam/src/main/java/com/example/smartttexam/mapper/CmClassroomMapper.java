package com.example.smartttexam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 课堂Mapper - 查课堂所属课程
 */
@Mapper
public interface CmClassroomMapper {

    /** 根据课堂ID查课程ID */
    @Select("SELECT courseId FROM cm_classroom WHERE id = #{classroomId} LIMIT 1")
    String getCourseIdByClassroomId(@Param("classroomId") String classroomId);

    /** 判断ID是否为课程（查cm_course表） */
    @Select("SELECT count(1) FROM cm_course WHERE id = #{id}")
    int isCourse(@Param("id") String id);

    /** 根据课程ID查课程名称 */
    @Select("SELECT courseChineseName FROM cm_course WHERE id = #{courseId} LIMIT 1")
    String getCourseNameById(@Param("courseId") String courseId);

    /** 根据课堂ID查课堂名称 */
    @Select("SELECT classroomName FROM cm_classroom WHERE id = #{classroomId} LIMIT 1")
    String getClassroomNameById(@Param("classroomId") String classroomId);
}
