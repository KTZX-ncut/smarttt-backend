package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmGetability;
import com.example.smartttevaluation.pojo.CmKwadict;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CmGetabilityMapper {
    /*Y*/
    /**
     *查询能力表
     */
    @Select("select ga.id, ga.orderno, ga.abilitydeep, a.levelcode, " +
            "a.name, ga.importantlevel, a.remark, ga.courseid " +
            "from cm_ability a,cm_getability ga where a.id = ga.id and ga.courseid=#{courseid}")
    List<CmGetability> getGetability (@Param("courseid") String courseid);
    /**
     *查询能力字典
     */
    @Select("select id, datavalue, remark from cm_ability")
    List<CmAbility> getAbility ();
    /**
     *添加能力
     */
    void insertGetability(@Param("courseid") String courseid,@Param("ids") List<String>  ids);
    /**
     *删除能力
     */
    void deleteGetabilityByIDs(@Param("courseid") String courseid ,@Param("ids") List<String> ids);
/*
    //更新能力
    void updateGetabilityByID(CmGetability cmGetability);
*/
    /**
     *通过id查找能力字典中能力，可用来判断能力是否存在
     */
    @Select("Select count(*) from cm_ability where id=#{id}")
    long getNumOfAbilityById(@Param("id") String id);
    /**
     *通过id查找对应课程数量，可用来判断课程是否存在
     */
    @Select("Select count(*) from cm_course where id=#{id}")
    long getNumOfCourseById(@Param("id") String id);
    /**
     *通过能力id和课程id寻找记录数量
     */
    @Select("Select count(*) from cm_getability where id=#{id} and courseid=#{courseid}")
    long getNumOfAbilityIdAndCourseById(@Param("id") String abilityId,@Param("courseid") String courseId);
    /**
     *查找能力id在能力字典中的pid
     */
    @Select("Select pid from cm_ability where id=#{id}")
    String getPidById(@Param("id") String id);
    /**
     *通过能力id获取能力
     */
    @Select("Select * from cm_ability where id=#{id}")
    CmAbility getAbilityById(@Param("id") String id);
    /**
     *通过能力id获取kwa列表
     */
    List<CmKwadict> getCourseKwaByGetabilityId(@Param("obsId") String obsId, @Param("ids") List<String> ids);
}
