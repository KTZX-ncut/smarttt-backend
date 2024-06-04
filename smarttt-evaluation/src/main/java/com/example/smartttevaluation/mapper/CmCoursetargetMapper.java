package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCoursetargetMapper {

    @Select("select id, code, name, remark, courseid from cm_course_target")
    List<CmCoursetarget> getCoursetarget ();

    @Update("INSERT INTO cm_course_target (id, code, name, remark, courseid) " +
            "VALUES (#{id},#{code},#{name},#{remark},#{courseid})")
    void createCoursetarget(CmCoursetarget cmCoursetarget);

    void deleteCoursetargetByIDs(@Param("ids")List<String> ids);

    void updateCoursetargetByID(CmCoursetarget cmCoursetarget);

    @Update("update cm_course_unit set name=#{name},datavalue=#{datavalue} where id=#{id}")
    void updateCoursetarget(CmKnowledgeUnit cmKnowledgeUnit);

    @Update("update cm_course_unit_kwa set status=#{status} where unitid=#{unitid} ")
    void updateCoursetargetUnit(CmCoursetargetUnit cmCoursetargetUnit);

    void insertunit(@Param("courseid") String courseid,@Param("ids") List<String>  ids);

    @Select("select count(*) from cm_course_unit_kwa where unitid=#{unitid}")
    long getUnitCount(CmCoursetargetUnit cmCoursetargetUnit);

    @Insert("insert into cm_course_target_unit(unitid,unitname,targetid) values(#{unitid},#{unitname},#{targetid})")
    void insertCoursetargetUnit(@Param("courseid") String courseid, CmCoursetargetUnit cmCoursetargetUnit);

    @Select("select count(*) from cm_course_target_unit where unitid=#{unitid}")
    long getUnitCountByUnitId(@Param("unitid") String unitid, @Param("targetid") String targetid);

    List<String> selectAllidByids(@Param("ids") List<String> ids);

    //批量删除unit
    void deleteCoursetargetUnit(@Param("unitid") String unitid);

    //通过id查找基本教学目标，可用来判断基本教学目标是否存在
    @Select("Select count(*) from cm_course_unit where id=#{id}")
    long getNumOfUnitById(@Param("id") String id);

    //通过id查找对应课程数量，可用来判断课程是否存在
    @Select("Select count(*) from cm_course where id=#{id}")
    long getNumOfCourseById(@Param("id") String id);

    //通过课程目标id和课程id寻找记录数量
    @Select("Select count(*) from cm_course_target where id=#{id} and courseid=#{courseid}")
    long getNumOfTargetIdAndCourseById(@Param("id") String id,@Param("courseid") String courseId);
}
