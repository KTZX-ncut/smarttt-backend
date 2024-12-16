package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKwadict;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CmCoursetargetMapper {
    /**
     * 课程目标列表
     */
    @Select("select id, code, name, remark, courseid from cm_course_target where courseid = #{obsId} order by createTime")
    List<CmCoursetarget> getCoursetarget(String obsId);

    /**
     * 获取课程目标的kwas
     */
    @Select("select targetKwa.kwaId as id, kwa.name, kwa.abilityid, kwa.keywordid, k.importantlevelid, ga.datavalue from cm_course_target_kwa targetKwa " +
            "inner join cm_kwadict kwa on kwa.id = targetKwa.kwaId " +
            "inner join cm_keywords k on k.id = kwa.keywordid " +
            "inner join cm_getability ga on ga.id = kwa.abilityid " +
            "where obsId = #{obsId} and targetId = #{targetId} and targetKwa.kwaId = kwa.id")
    List<CmKwadict> getKwas(@Param("targetId") String targetId, @Param("obsId") String obsId);

    /**
     * 创建课程目标
     */
    @Update("INSERT INTO cm_course_target (id, code, name, remark, courseid, createTime) " +
            "VALUES (#{cct.id},#{cct.code},#{cct.name},#{cct.remark},#{cct.courseid},#{createTime})")
    void createCoursetarget(@Param("cct") CmCoursetarget cmCoursetarget, @Param("createTime") LocalDateTime createTime);

    /**
     * 批量删除课程目标
     */
    void deleteCoursetargetByIds(@Param("targetIds") List<String> ids);

    /**
     * 根据targetId批量删除其关联的kwa
     */
    void deleteKwasByTargetIds(@Param("targetIds") List<String> targetIds);

    /**
     * 更新课程目标
     */
    void updateCoursetargetByID(CmCoursetarget cmCoursetarget);

    /**
     * 获取unit数量
     */
    @Select("select count(*) from cm_course_unit_kwa where unitid=#{unitid}")
    long getUnitCount(CmCoursetargetUnit cmCoursetargetUnit);
    /**
     * 根据课程目标id和kwaid新增课程目标的kwa
     */
    void createKwasByTargetIdAndKwaId(@Param("cmCoursetarget") CmCoursetarget cmCoursetarget);

    /**
     * 根据课程目标id和kwaid删除课程目标的kwa
     */
    void deleteKwasByTargetIdAndKwaId(@Param("cmCoursetarget") CmCoursetarget cmCoursetarget);

    /**
     * 根据kwaid删除课程目标的kwa
     */
    void deleteKwasByKwaIds(@Param("kwaIds") List<String> kwaIds);
}
