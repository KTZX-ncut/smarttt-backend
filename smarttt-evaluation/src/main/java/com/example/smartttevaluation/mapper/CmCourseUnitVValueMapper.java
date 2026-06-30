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
}

