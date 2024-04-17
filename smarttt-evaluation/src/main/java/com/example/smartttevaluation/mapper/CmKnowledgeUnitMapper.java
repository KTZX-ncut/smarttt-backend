package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.pojo.CmGetability;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CmKnowledgeUnitMapper {
    @Select("select id,pid,name,type from cm_knowledge_unit where courseid=#{courseid} and pid=0")
    List<CmKnowledgeUnit> getChapter (@Param("courseid") String courseid);

    @Select("select id,pid,name,type from cm_knowledge_unit where pid=#{pid}")
    List<CmKnowledgeUnit> getSection (@Param("pid") String pid);

    @Select("select cm_knowledge_unit_kwa.kwaid,cm_kwadict.name,cm_knowledge_unit_kwa.status from cm_knowledge_unit_kwa,cm_kwadict where cm_knowledge_unit_kwa.kwaid=cm_kwadict.id and unitid=#{unitid}")
    List<CmKnowledgeUnitKwa> getKnowledgeUnitKwa (@Param("unitid") String unitid);

    @Insert("insert into cm_knowledge_unit(id,pid,name,type,courseid) values(#{id},0,#{name},#{type},#{courseid})")
    void insertChapter(CmKnowledgeUnit cmKnowledgeUnit);

    @Insert("insert into cm_knowledge_unit(id,pid,name,type,courseid) values(#{id},#{pid},#{name},#{type},#{courseid})")
    void insertSection(CmKnowledgeUnit cmKnowledgeUnit);


    //添加能力单元kwa
    @Insert("insert into cm_knowledge_unit_kwa(unitid,kwaid,status) values(#{unitid},#{kwaid},#{status})")
    void insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    //批量删除同一小节中kwa
    void deleteKnowledgeUnitKwa(@Param("unitid") String unitid , @Param("ids") List<String> kwaids);


    //批量删除UnitKwa
    void deleteKnowledgeUnitKwaByUnitids(@Param("unitids") List<String> unitids);
    //批量删除Unit
    void deleteKnowledgeUnitByUnitids(@Param("unitids") List<String> unitids);
    //更新知识单元
    @Update("update cm_knowledge_unit set name=#{name},type=#{type} where id=#{id}")
    void updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit);
    //更改kwa状态
    @Update("update cm_knowledge_unit_kwa set status=#{status} where unitid=#{unitid} and kwaid=#{kwaid}")
    void updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    @Select("select count(*) from cm_knowledge_unit where id=#{id}")
    long getUnitCountByUnitId(String id);

    @Select("select count(*) from cm_knowledge_unit_kwa where kwaid=#{kwaid} and unitid=#{unitid}")
    long getUnitKwaCount(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    @Select("select count(*) from cm_course where id=#{id}")
    long getCourseCountByid(String id);
}
