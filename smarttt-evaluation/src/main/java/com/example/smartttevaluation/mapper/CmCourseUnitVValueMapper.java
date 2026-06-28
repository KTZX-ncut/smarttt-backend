package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmCourseUnitVValue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmCourseUnitVValueMapper {
    // 查询知识单元的v值
    @Select("select unitV.id as id, unitV.unitId as unitid, unitV.vId as vid, v.vname as name, v.vname as vname, v.parent_id as parentId, v.level as level, unitV.status as status " +
            "from cm_course_unit_v_values unitV, v_ideology_value v " +
            "where unitV.unitId=#{unitid} and unitV.vId=v.id")
    List<CmCourseUnitVValue> getKnowledgeUnitVValue(String unitid);

    // 添加知识单元v值
    @Insert("insert into cm_course_unit_v_values(id,unitId,vId,status) values(#{id},#{unitid},#{vid},#{status})")
    void insertKnowledgeUnitVValue(CmCourseUnitVValue cmCourseUnitVValue);

    // 批量删除同一单元中的v值
    void deleteKnowledgeUnitVValue(@Param("unitid") String unitid, @Param("ids") List<String> vids);

    // 批量删除UnitVValue
    void deleteKnowledgeUnitVValueByUnitids(@Param("unitids") List<String> unitids);

    // 检查v值是否已存在
    @Select("select count(*) from cm_course_unit_v_values where vId=#{vid} and unitId=#{unitid}")
    long getUnitVValueCount(CmCourseUnitVValue cmCourseUnitVValue);

    // 查询历史课程下所有思政知识单元关联（通过 cm_course_unit 关联 courseId）
    @Select("SELECT uv.id, uv.unitId as unitid, uv.vId as vid, uv.status " +
            "FROM cm_course_unit_v_values uv " +
            "INNER JOIN cm_course_unit u ON uv.unitId = u.id " +
            "WHERE u.courseid = #{courseId}")
    List<CmCourseUnitVValue> selectByCourseId(@Param("courseId") String courseId);

    // 查询当前课程的知识单元（name+ordernum），用于匹配复制
    @Select("SELECT id, name, ordernum FROM cm_course_unit WHERE courseid = #{courseId}")
    List<java.util.Map<String, Object>> selectUnitBasicByCourseId(@Param("courseId") String courseId);

    // 删除当前课程下所有思政知识单元关联
    @Delete("DELETE uv FROM cm_course_unit_v_values uv " +
            "INNER JOIN cm_course_unit u ON uv.unitId = u.id " +
            "WHERE u.courseid = #{courseId}")
    void deleteByCourseId(@Param("courseId") String courseId);

    // 批量插入
    @Insert("<script>" +
            "INSERT INTO cm_course_unit_v_values(id, unitId, vId, status) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.unitid}, #{item.vid}, #{item.status})" +
            "</foreach>" +
            "</script>")
    void batchInsert(@Param("list") List<CmCourseUnitVValue> list);
}

