package com.example.smartttcourse.mapper;


import com.example.smartttcourse.dto.CourseClassroomReq;
import com.example.smartttcourse.dto.ResponsiblePerson;
import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCourseMapper {
    /**
     * 获取学期信息
     */
    @Select("select id,courseChineseName, courseEnglishName, courseCode, professionName,professionId from cm_course where professionId=#{obsid} and schooltermId = #{termid}")
    List<SimpleCourse> getCourse (Token token);
    @Select("select id,courseChineseName from cm_course where professionId=#{obsid} and schooltermId = #{termid}")
    List<CourseClassroomReq> getCourseName(Token token);

    @Insert("INSERT INTO cm_course (id,schooltermId, courseChineseName, courseEnglishName, courseCode, professionName, professionId) " +
            "VALUES (#{id},#{schooltermId},#{courseChineseName},#{courseEnglishName},#{courseCode},#{professionName},#{professionId})")
    void createCourse(CmCourse cmcourse);

    void deleteCourseByID(@Param("ids") List<String> ids);

    @Select("SELECT c.id, c.courseChineseName, c.courseEnglishName, c.courseCode, " +
            "c.professionName, c.professionId " +
            "FROM cm_course c " +
            "INNER JOIN cm_profession p ON c.professionId = p.obsid " +
            "WHERE c.schooltermId = #{termID} " +
            "AND p.procode = (SELECT procode FROM cm_profession WHERE obsid = #{obsid})")
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

    @Select("select * from st_roleuser where obsid = #{id}")
    List<StRoleUser> getHistoryRP(String id);

    void updateOneCourse(CmCourse cmCourse);

    @Select("select cm_course.id,cm_course.courseChineseName from cm_course\n" +
            "where cm_course.id in (select obsid as id from st_roleuser where roleid =#{roleid}  and userid=#{id})")
    List<CourseClassroomReq> getTeacherOtherCourse(Token token);


    String getTermIdByCourseId( @Param("courseId") String courseId);

    void deleteCourseRoleUser(@Param("courseIdList") List<String> courseIdList);

    @Select("select count(*) from cm_course where id = #{courseIdOrClassroomId}")
    Integer countByCourseId(String courseIdOrClassroomId);

    @Select("select courseCode from cm_course where id = #{obsId}")
    String getCourseCode(String obsId);

    @Select("select id, courseChineseName, courseEnglishName, courseCode, professionName, professionId " +
            "from cm_course where schooltermId = #{termId} and courseCode = #{courseCode}")
    List<SimpleCourse> getPreCourseByCode(@Param("termId") String termId, @Param("courseCode") String courseCode);

    @Select("select * from cm_keywords where courseid = #{pastId}")
    List<CmKeywords> getPastKeyword(String pastId);

    @Delete("delete from cm_keywords where courseid = #{obsId}")
    void deleteKeyword(String obsId);

    void copyKeyword(@Param("keywords") List<CmKeywords> keywords);

    @Select("select * from cm_getability where courseid = #{pastId}")
    List<CmGetability> getPastAbility(String pastId);

    @Delete("delete from cm_getability where courseid = #{obsId}")
    void deleteAbility(String obsId);

    void copyAbility(@Param("abilities") List<CmGetability> abilities);

    @Delete("delete from cm_kwadict where courseid = #{obsId}")
    void deleteKwa(String obsId);

    @Select("select * from cm_kwadict where courseid = #{pastId}")
    List<CmKwadict> getPastKwa(String pastId);

    void copyKwa(@Param("kwas") List<CmKwadict> kwas);

    @Delete("delete from cm_course_unit where courseId = #{obsId}")
    void deleteUnit(String obsId);

    @Select("select * from cm_course_unit where courseId = #{pastId}")
    List<CmKnowledgeUnit> getPastUnit(String pastId);

    void copyUnit(@Param("units") List<CmKnowledgeUnit> units);

    @Delete("delete from cm_course_unit_kwa where unitId = #{unitId}")
    void deleteUnitKwa(String unitId);

    @Select("select * from cm_course_unit_kwa where unitId = #{unitId}")
    List<CmKnowledgeUnitKwa> getPastUnitKwa(String unitId);

    void copyUnitKwa(@Param("unitKwas") List<CmKnowledgeUnitKwa> unitKwas);

    @Delete("delete from cm_lines where courseid = #{obsId}")
    void deleteLine(String obsId);

    @Select("select * from cm_lines where courseid = #{pastId}")
    List<CmLines> getPastLine(String PastId);

    void copyLine(@Param("lines") List<CmLines> lines);

    @Delete("delete from cm_course_target where courseid = #{obsId}")
    void deleteTarget(String obsId);

    @Select("select * from cm_course_target where courseid = #{pastId}")
    List<CmCoursetarget> getPastTarget(String pastId);

    void copyTarget(@Param("targets") List<CmCoursetarget> targets);

    @Delete("delete from cm_course_target_kwa where obsId = #{obsId}")
    void deleteTargetKwa(String obsId);

    @Select("select * from cm_course_target_kwa where obsId = #{pastId}")
    List<CmCoursetargetKwa> getPastTargetKwa(String pastId);

    void copyTargetKwa(@Param("targetKwas") List<CmCoursetargetKwa> targetKwas);

    @Delete("delete from cm_course_checkitem where courseid = #{obsId}")
    void deleteCheckitem(String obsId);

    @Select("select * from cm_course_checkitem where courseid = #{pastId}")
    List<CmCheckitem> getPastCheckitem(String pastId);

    void copyCheckitem(@Param("checkitems") List<CmCheckitem> checkitems);

    @Delete("delete from cm_course_assessment where courseId = #{obsId}")
    void deleteAssessment(String obsId);

    @Select("select * from cm_course_assessment where courseId = #{pastId}")
    List<CmCourseAssessment> getPastAssessment(String pastId);

    void copyAssessment(@Param("assessments") List<CmCourseAssessment> assessments);

}

