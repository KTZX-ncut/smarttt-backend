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
    @Select("select id,pid,name,ordernum as type,datavalue,ordernum from cm_knowledge_unit where courseid=#{courseid} and pid=0 order by ordernum")
    List<CmKnowledgeUnit> getChapter (@Param("courseid") String courseid);

    @Select("select id,pid,name,concat(#{p_type},\".\",ordernum) as type,datavalue,ordernum from cm_knowledge_unit where pid=#{pid} order by ordernum ")
    List<CmKnowledgeUnit> getSection (@Param("pid") String pid,@Param("p_type") String p_type);

    @Select("select #{unitid} as unitid,cm_knowledge_unit_kwa.kwaid,cm_kwadict.name,cm_knowledge_unit_kwa.status from cm_knowledge_unit_kwa,cm_kwadict where cm_knowledge_unit_kwa.kwaid=cm_kwadict.id and unitid=#{unitid}")
    List<CmKnowledgeUnitKwa> getKnowledgeUnitKwa (@Param("unitid") String unitid);

    @Insert("insert into cm_knowledge_unit(id,pid,name,type,datavalue,courseid,ordernum) values(#{id},0,#{name},#{type},#{datavalue},#{courseid},#{ordernum})")
    void insertChapter(CmKnowledgeUnit cmKnowledgeUnit);

    @Insert("insert into cm_knowledge_unit(id,pid,name,type,datavalue,courseid,ordernum) values(#{id},#{pid},#{name},#{type},#{datavalue},#{courseid},#{ordernum})")
    void insertSection(CmKnowledgeUnit cmKnowledgeUnit);


    //添加能力单元kwa
    @Insert("insert into cm_knowledge_unit_kwa(unitid,kwaid,status) values(#{unitid},#{kwaid},#{status})")
    void insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    //批量删除同一小节中kwa
    void deleteKnowledgeUnitKwa(@Param("unitid") String unitid , @Param("ids") List<String> kwaids);

    List<String> selectAllUnitidByUnitids(@Param("unitids") List<String> unitids);
    //查询所有删除项的父列表id
    List<String> selectAllPUnitidByUnitids(@Param("unitids") List<String> unitids);
    //批量删除UnitKwa
    void deleteKnowledgeUnitKwaByUnitids(@Param("unitids") List<String> unitids);
    //批量删除Unit
    void deleteKnowledgeUnitByUnitids(@Param("unitids") List<String> unitids);
    //更新知识单元
    @Update("update cm_knowledge_unit set name=#{name},type=#{type},datavalue=#{datavalue} where id=#{id}")
    void updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit);
    //更改kwa状态
    @Update("update cm_knowledge_unit_kwa set status=#{status} where unitid=#{unitid} and kwaid=#{kwaid}")
    void updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    @Select("select count(*) from cm_knowledge_unit where id=#{id}")
    long getUnitCountByUnitId(@Param("id") String id);

    @Select("select count(*) from cm_knowledge_unit_kwa where kwaid=#{kwaid} and unitid=#{unitid}")
    long getUnitKwaCount(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    @Select("select count(*) from cm_course where id=#{id}")
    long getCourseCountByid(@Param("id") String id);
/*
    @Update("update cm_knowledge_unit set type=,where")*/
    void flashKnowledgeUnitOrdernum(@Param("unitid") String unitid,@Param("courseid") String courseid,@Param("preOrdernum") long preOrdernum,@Param("beginOrdernum") long beginOdernum,@Param("endOrdernum") long endOrdernum);

    void updateKnowledgeUnitOrdernum(@Param("id") String id,@Param("newOrdernum") long newOrdernum);
    @Select("select ifnull(max(ordernum),0) from cm_knowledge_unit where  pid=#{pid} and courseid=#{courseid}")
    long selectMaxOrdernum(@Param("pid") String pid,@Param("courseid") String courseid);

    //平移区间内ordernum
    @Update("update cm_knowledge_unit set ordernum=ordernum+#{changeValue} where pid=#{pid} and courseid=#{courseid} and ordernum>=#{minOrdernum} and ordernum<=#{maxOrdernum}")
    void updateOtherKnowledgeUnitOrdernum(@Param("changeValue") int changeValue,@Param("pid") String unitid,@Param("courseid") String courseid,@Param("minOrdernum") long newOrdernum,@Param("maxOrdernum") long oldOrdernum);
    @Select("select ordernum from cm_knowledge_unit where id=#{id}")
    long getOrdernumById(@Param("id") String id);
}
