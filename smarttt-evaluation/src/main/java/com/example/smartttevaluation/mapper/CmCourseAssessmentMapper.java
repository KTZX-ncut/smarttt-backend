package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.pojo.CmCourseAssessment;
import com.example.smartttevaluation.pojo.CmCourseCheckitemFile;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import org.apache.ibatis.annotations.*;
import org.w3c.dom.ls.LSException;

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

    @Select("select * from cm_course_assessment_file where obsid=#{obsid} order by createTime")
    List<CmCourseCheckitemFile> getFiles(String obsid);

    @Select("select fileId from cm_course_assessment_checkitem_file where checkitemId=#{checkitemId}")
    List<String> getAssociateFileIds(String checkitemId);

    @Select("select * from cm_course_checkitem where id in (select checkitemId from cm_course_assessment_checkitem_file where fileId=#{fileId}) and courseid=#{obsid}")
    List<CmCheckitem> getAssociateCheckitems(@Param("fileId") String fileId,@Param("obsid") String obsid);

    @Insert("insert into cm_course_assessment_file(id, obsid, fileName, fileData, createTime) values(#{id}, #{obsid}, #{fileName}, #{fileData}, #{createTime})")
    void uploadFile(CmCourseCheckitemFile cmCourseCheckitemFile);

    @Delete("delete from cm_course_assessment_file where obsid=#{obsid} and id=#{id}")
    void deleteFile(CmCourseCheckitemFile cmCourseCheckitemFile);

    void associate(@Param("checkitemId") String checkitemId, @Param("fileIds") List<String> fileIds);

    void disassociate(@Param("checkitemId") String checkitemId, @Param("fileIds") List<String> fileIds);
}
