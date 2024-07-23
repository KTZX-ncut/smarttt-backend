package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.pojo.CmCourseAssessment;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCourseAssessmentMapper {
    //获取课程目标数量
    @Select("select count(*) from cm_course_target where courseid=#{courseid}")
    int getCourseTargetNum (@Param("courseid") String courseid);

    @Select("select * from cm_course_target where courseid=#{courseid}")
    List<CmCoursetarget> getCourseTarget (@Param("courseid") String courseid);

    @Select("select * from cm_course_checkitem where  courseid=#{courseid} order by orderno")
    List<CmCheckitem> getCourseCheckItem (@Param("courseid") String courseid);
    @Select("select * from cm_course_assessment where courseid=#{courseid}")
    List<CmCourseAssessment> selectCourseAssessment(@Param("courseid") String courseid);

    @Select("select count(*) from cm_course_assessment where checkitemId=#{checkitemId} and coursetargetId=#{coursetargetId} and courseId=#{courseid}")
    int selectAssessmentCount(CmCourseAssessment cmCourseAssessment);
    @Update("update cm_course_assessment set standardScore=#{standardScore} where checkitemId=#{checkitemId} and coursetargetId=#{coursetargetId}")
    void updateAssessment(CmCourseAssessment cmCourseAssessment);
    @Insert("insert into cm_course_assessment(id,courseId,coursetargetId,checkitemId,standardScore) value(#{id},#{courseid},#{coursetargetId},#{checkitemId},#{standardScore})")
    void insertAssessment(CmCourseAssessment cmCourseAssessment);



    @Delete("delete from cm_course_assessment where checkitemId=#{checkitemId} and coursetargetId=#{coursetargetId}")
    void deleteAssessment(CmCourseAssessment cmCourseAssessment);
    @Update("update cm_course_checkitem set percent=#{percent} where id=#{id}")
    void updatePercent(CmCheckitem cmCourseCheckItem);
}
