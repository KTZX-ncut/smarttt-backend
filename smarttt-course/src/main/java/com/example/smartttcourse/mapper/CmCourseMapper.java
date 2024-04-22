package com.example.smartttcourse.mapper;


import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.pojo.CmCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCourseMapper {
    /**
     * 获取学期信息
     */
    @Select("select id, schooltermId, courseChineseName, courseEnglishName, courseCode, professionName,professionId from cm_course where professionId=#{id}")
    List<SimpleCourse> getCourse (String id);

    @Insert("INSERT INTO cm_course (id,schooltermId, coursechinesename, courseenglishname, coursecode, professionname, professionId) " +
            "VALUES (#{id},#{schooltermId},#{coursechinesename},#{courseenglishname},#{coursecode},#{professionname},#{profession})")
    void createCourse(CmCourse cmcourse);

    @Delete("delete from cm_course where id = #{id}")
    void deleteCourseByID(String id);

    @Select("select id, term, coursechinesename, coursecode, dutyman from cm_course")
    Object historyCourseByTerm(String term);

    @Select("select termname from cm_term where id = #{schooltermId}")
    String getTermName(String schooltermId);
}

