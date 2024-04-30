package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCoursetarget;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmCoursetargetMapper {

    @Select("select id, code, name, remark, unitid, courseid, kwaid from cm_coursetarget")
    List<CmCoursetarget> getCoursetarget ();

    @Update("INSERT INTO cm_coursetarget (id, code, name, remark, unitid, courseid, kwaid) " +
            "VALUES (#{id},#{code},#{name},#{remark},#{unitid},#{courseid},#{kwaid})")
    void createCoursetarget(CmCoursetarget cmCoursetarget);

    void deleteCoursetargetByIDs(@Param("ids")List<String> ids);

    void updateCoursetargetByID(CmCoursetarget cmCoursetarget);

    void insertunit(@Param("courseid") String courseid,@Param("ids") List<String>  ids);

    //通过id查找基本教学目标，可用来判断基本教学目标是否存在
    @Select("Select count(*) from cm_knowledge_unit_kwa where unitid=#{unitid}")
    long getNumOfUnitById(@Param("unitid") String unitid);

    //通过id查找对应课程数量，可用来判断课程是否存在
    @Select("Select count(*) from cm_course where id=#{id}")
    long getNumOfCourseById(@Param("id") String id);

    //通过课程目标id和课程id寻找记录数量
    @Select("Select count(*) from cm_coursetarget where id=#{id} and courseid=#{courseid}")
    long getNumOfUnitIdAndCourseById(@Param("id") String id,@Param("courseid") String courseId);
}
