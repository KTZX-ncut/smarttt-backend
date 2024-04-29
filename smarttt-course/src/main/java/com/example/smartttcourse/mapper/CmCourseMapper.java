package com.example.smartttcourse.mapper;


import com.example.smartttcourse.dto.ResponsiblePerson;
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

    @Insert("INSERT INTO cm_course (id,schooltermId, courseChineseName, courseEnglishName, courseCode, professionName, professionId) " +
            "VALUES (#{id},#{schooltermId},#{courseChineseName},#{courseEnglishName},#{courseCode},#{professionName},#{professionId})")
    void createCourse(CmCourse cmcourse);

    void deleteCourseByID(List<String> ids);

    @Select("select id, courseChineseName, courseEnglishName, courseCode from cm_course where schooltermId = #{termID} and professionId = #{obsid}")
    List<SimpleCourse> historyCourseByTerm(@Param("termID") String termID,@Param("obsid") String obsid);

    @Select("select termname from cm_term where id = #{schooltermId}")
    String getTermName(String schooltermId);

    @Select("select obsname from sm_obs where id = #{professionId}")
    String getObsName(String professionId);

    @Select("select * from cm_course where id = #{id}")
    CmCourse getCopyCourse(String id);

    @Select("SELECT u.id, u.username, o.obsname\n" +
            "FROM st_roleuser ru\n" +
            "JOIN st_users u ON ru.userid = u.id\n" +
            "JOIN sm_teacher t ON u.id = t.usersid\n" +
            "JOIN sm_obs o ON t.obsid = o.id\n" +
            "WHERE ru.obsid = #{id};\n")
    List<ResponsiblePerson> getCourseRP(String id);
}

