package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCoursetargetMapper {
    /**
     *课程目标列表
     */
    @Select("select id, code, name, remark, courseid from cm_course_target where courseid = #{obsid}")
    List<CmCoursetarget> getCoursetarget (String obsid);
    /**
     *创建课程目标
     */
    @Update("INSERT INTO cm_course_target (id, code, name, remark, courseid) " +
            "VALUES (#{id},#{code},#{name},#{remark},#{courseid})")
    void createCoursetarget(CmCoursetarget cmCoursetarget);
    /**
     *批量删除课程目标
     */
    void deleteCoursetargetByIDs(@Param("ids")List<String> ids);
    /**
     *更新课程目标
     */
    void updateCoursetargetByID(CmCoursetarget cmCoursetarget);

    @Update("update cm_course_unit set name=#{name},datavalue=#{datavalue} where id=#{id}")
    void updateCoursetarget(CmKnowledgeUnit cmKnowledgeUnit);
    /**
     *更新课程目标unit
     */
    @Update("update cm_course_unit_kwa set status=#{status} where unitid=#{unitid} ")
    void updateCoursetargetUnit(CmCoursetargetUnit cmCoursetargetUnit);
    /**
     *插入unit
     */
    void insertunit(@Param("courseid") String courseid,@Param("ids") List<String>  ids);
    /**
     *获取unit数量
     */
    @Select("select count(*) from cm_course_unit_kwa where unitid=#{unitid}")
    long getUnitCount(CmCoursetargetUnit cmCoursetargetUnit);
    /**
     *插入课程目标unit
     */
    @Insert("insert into cm_course_target_unit(unitid,unitname,targetid) values(#{unitid},#{unitname},#{targetid})")
    void insertCoursetargetUnit(@Param("courseid") String courseid, CmCoursetargetUnit cmCoursetargetUnit);
    /**
     *通过unitid获取unit数量
     */
    @Select("select count(*) from cm_course_target_unit where unitid=#{unitid}")
    long getUnitCountByUnitId(@Param("unitid") String unitid, @Param("targetid") String targetid);
    /**
     *通过ids获取id
     */
    List<String> selectAllidByids(@Param("ids") List<String> ids);
    /**
     *批量删除unit
     */
    void deleteCoursetargetUnit(@Param("unitid") String unitid);
    /**
     *通过id查找基本教学目标，可用来判断基本教学目标是否存在
     */
    @Select("Select count(*) from cm_course_unit where id=#{id}")
    long getNumOfUnitById(@Param("id") String id);
    /**
     *通过id查找对应课程数量，可用来判断课程是否存在
     */
    @Select("Select count(*) from cm_course where id=#{id}")
    long getNumOfCourseById(@Param("id") String id);
    /**
     *通过课程目标id和课程id寻找记录数量
     */
    @Select("Select count(*) from cm_course_target where id=#{id} and courseid=#{courseid}")
    long getNumOfTargetIdAndCourseById(@Param("id") String id,@Param("courseid") String courseId);
}
