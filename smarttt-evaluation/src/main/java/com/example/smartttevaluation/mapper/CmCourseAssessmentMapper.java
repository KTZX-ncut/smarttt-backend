package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.dto.CmAssessmentFile;
import com.example.smartttevaluation.dto.CmAssessmentStudent;
import com.example.smartttevaluation.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCourseAssessmentMapper {
    //获取课程目标数量
    @Select("select count(*) from cm_course_target where courseid=#{courseid}")
    int getCourseTargetNum(@Param("courseid") String courseid);

    @Select("select * from cm_course_target where courseid=#{courseid} order by createTime")
    List<CmCoursetarget> getCourseTarget(@Param("courseid") String courseid);

    @Select("select * from cm_course_checkitem where  courseid=#{courseid} order by orderno")
    List<CmCheckitem> getCourseCheckItem(@Param("courseid") String courseid);

    @Select("select * from cm_course_assessment where courseid=#{courseid}")
    List<CmCourseAssessment> selectCourseAssessment(@Param("courseid") String courseid);

    @Select("select count(*) from cm_course_assessment where checkitemId=#{checkitemId} and coursetargetId=#{coursetargetId} and courseId=#{courseid}")
    int selectAssessmentCount(CmCourseAssessment cmCourseAssessment);

    @Update("update cm_course_assessment set standardScore=#{standardScore} where checkitemId=#{checkitemId} and coursetargetId=#{coursetargetId} and courseId = #{courseid}")
    void updateAssessment(CmCourseAssessment cmCourseAssessment);

    @Insert("insert into cm_course_assessment(id,courseId,coursetargetId,checkitemId,standardScore) value(#{id},#{courseid},#{coursetargetId},#{checkitemId},#{standardScore})")
    void insertAssessment(CmCourseAssessment cmCourseAssessment);

    @Delete("delete from cm_course_assessment where checkitemId=#{checkitemId} and coursetargetId=#{coursetargetId} and courseId = #{courseid}")
    void deleteAssessment(CmCourseAssessment cmCourseAssessment);

    @Update("update cm_course_checkitem set percent=#{percent} where id=#{id} and courseid = #{courseid}")
    void updatePercent(CmCheckitem cmCourseCheckItem);

    @Select("select * from pm_testpaper where id in (select paperId from pm_test where classroomId = #{classroomId} and status = 1) order by createTime")
    List<CmTestpaper> getTestpaper(String classroomId);

    @Select("select * from cm_course_assessment_file where classroomId = #{classroomId} order by createTime")
    List<CmAssessmentFile> getCustomFile(String classroomId);

    @Select("select su.id, ccafd.rowNo, so.obsname as className, ccafd.stuno, su.username, su.loginname, ccafd.score from cm_course_assessment_filedata ccafd " +
            "left join cm_course_assessment_file ccaf on ccaf.id = ccafd.fileId " +
            "left join sm_student ss on ss.stuno = ccafd.stuno " +
            "left join st_users su on su.id = ss.usersid " +
            "left join sm_obs so on so.id = ss.obsid " +
            "where ccafd.fileId = #{fileId} order by ccafd.rowNo")
    List<CmAssessmentStudent> showExcel(String fileId);

    @Select("select fileId as id, type from cm_course_assessment_checkitem_file where checkitemId=#{checkitemId} and classroomId = #{classroomId}")
    List<CmAssessmentFile> getAssociateFiles(@Param("checkitemId") String checkitemId, @Param("classroomId") String classroomId);

    @Select("select * from cm_course_checkitem where id in (select checkitemId from cm_course_assessment_checkitem_file where fileId = #{fileId})")
    List<CmCheckitem> getAssociateCheckitems(@Param("fileId") String fileId, @Param("obsid") String obsid);

    @Insert("insert into cm_course_assessment_file (id, name, createTime, classroomId) values(#{file.id}, #{file.name}, #{file.createTime}, #{classroomId})")
    void setFileInfo(@Param("file") CmAssessmentFile file, @Param("classroomId") String classroomId);

    @Insert("insert into cm_course_assessment_filedata(id, fileId, stuno, score, rowNo) values(#{id}, #{fileId}, #{stu.stuno}, #{stu.score}, #{stu.rowNo})")
    void setFileData(@Param("id") String id, @Param("stu") CmAssessmentStudent stu, @Param("fileId") String fileId);

    @Delete("delete from cm_course_assessment_file where classroomId = #{obsid} and id = #{id}")
    void deleteFile(CmCourseCheckitemFile cmCourseCheckitemFile);

    @Insert("insert into cm_course_assessment_checkitem_file(id, checkitemId, fileId, classroomId, type) values " +
            "(#{id}, #{checkitemId}, #{file.id}, #{classroomId}, #{file.type})")
    void associate(@Param("id") String id, @Param("checkitemId") String checkitemId, @Param("classroomId") String obsId, @Param("file") CmAssessmentFile file);

    void disassociate(@Param("checkitemId") String checkitemId, @Param("files") List<CmAssessmentFile> files);

    @Select("select score from cm_course_assessment_filedata ccafd " +
            "inner join sm_student ss on ss.stuno = ccafd.stuno " +
            "where ccafd.fileId = #{fileId} and ss.usersid = #{stuId}")
    float getUploadFileScore(@Param("fileId") String fileId, @Param("stuId") String stuId);
}
