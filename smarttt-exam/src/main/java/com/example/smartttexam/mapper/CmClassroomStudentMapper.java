package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.CmClassroomStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 课堂学生查询Mapper
 */
@Mapper
public interface CmClassroomStudentMapper {

    /**
     * 根据课堂ID查询所有学生
     */
    @Select("SELECT id, classroomId, userId, obsId, userName, obsName, proName, loginname, rowNo, courseScore " +
            "FROM cm_classroom_student WHERE classroomId = #{classroomId} ORDER BY rowNo")
    List<CmClassroomStudent> getStudentsByClassroomId(@Param("classroomId") String classroomId);
}
