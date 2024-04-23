package com.example.smartttcourse.mapper;


import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmCourse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCourseMapper {
    /**
     * 获取学期信息
     */
    @Select("select id,courseChineseName, courseEnglishName, courseCode, professionName,professionId from cm_course where professionId=#{obsid} and schooltermId = (select id from cm_term where iscurrentterm = 1)")
    List<SimpleCourse> getCourse (Token token);

    @Insert("INSERT INTO cm_course (id,schooltermId, coursechinesename, courseenglishname, coursecode, professionname, professionId) " +
            "VALUES (#{id},#{schooltermId},#{coursechinesename},#{courseenglishname},#{coursecode},#{professionname},#{profession})")
    void createCourse(CmCourse cmcourse);

    void deleteCourseByID(List<String> ids);

    @Select("select id, courseChineseName, courseEnglishName, courseCode from cm_course where schooltermId = #{termID} and professionId = #{obsid}")
    Object historyCourseByTerm(@Param("termID") String termID,@Param("obsid") String obsid);

    @Select("select termname from cm_term where id = #{schooltermId}")
    String getTermName(String schooltermId);

    @Select("select obsname from sm_obs where id = #{professionId}")
    String getObsName(String professionId);

    @Select("select * from cm_course where id = #{id}")
    CmCourse getCopyCourse(String id);
}

