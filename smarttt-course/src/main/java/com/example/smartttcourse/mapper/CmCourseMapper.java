package com.example.smartttcourse.mapper;


import com.example.smartttcourse.pojo.CmCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmCourseMapper {
    /**
     * 获取学期信息
     */
    @Select("select id, term, coursechinesename, courseenglishname, coursecode, professionname, dutymanid, dutyman from cm_course")
    List<CmCourse> getCourse ();

    @Update("INSERT INTO cm_course (term, coursechinesename, courseenglishname, coursecode, professionname, dutyman) " +
            "VALUES (#{term},#{coursechinesename},#{courseenglishname},#{coursecode},#{professionname},#{dutyman})")
    void createCourse(CmCourse cmcourse);

    @Delete("delete from cm_course where id = #{id}")
    void deleteCourseByID(String id);

    @Select("select id, term, coursechinesename, coursecode, dutyman from cm_course")
    Object historyCourseByTerm(String term);
}

