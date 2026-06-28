package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CmKnowledgeUnitMapper {
    @Select("select id,pId,name,orderNum as type,dataValue,orderNum from cm_course_unit where courseid=#{courseid} and pId=0 order by orderNum")
    List<CmKnowledgeUnit> getChapter (@Param("courseid") String courseid);

    @Select("select id,pId,name,concat(#{p_type},\".\",orderNum) as type,dataValue,orderNum from cm_course_unit where pId=#{pid} order by orderNum ")
    List<CmKnowledgeUnit> getSection (@Param("pid") String pid,@Param("p_type") String p_type);

    @Select("select unitKwa.id as id, unitKwa.unitId as unitId, unitKwa.kwaId as kwaid,kwa.name as name, unitKwa.status as status from cm_course_unit_kwa unitKwa, cm_kwadict kwa " +
            "where unitId=#{obsid} and kwaId=kwa.id")
    List<CmKnowledgeUnitKwa> getKnowledgeUnitKwa (String obsid);

    @Insert("insert into cm_course_unit(id,pId,name,nodeType,dataValue,courseId,orderNum) values(#{id},0,#{name},#{type},#{datavalue},#{courseid},#{ordernum})")
    void insertChapter(CmKnowledgeUnit cmKnowledgeUnit);

    @Insert("insert into cm_course_unit(id,pId,name,nodeType,dataValue,courseId,orderNum) values(#{id},#{pid},#{name},#{type},#{datavalue},#{courseid},#{ordernum})")
    void insertSection(CmKnowledgeUnit cmKnowledgeUnit);

    //添加能力单元kwa
    @Insert("insert into cm_course_unit_kwa(id,unitId,kwaId,status) values(#{id},#{unitid},#{kwaid},#{status})")
    void insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    //批量删除同一小节中kwa
    void deleteKnowledgeUnitKwa(@Param("unitid") String unitid , @Param("ids") List<String> kwaids);

    List<String> selectAllUnitidByUnitids(@Param("unitids") List<String> unitids);
    //查询所有删除项的父列表id
    List<String> selectAllPUnitidByUnitids(@Param("unitids") List<String> unitids);
    //批量删除UnitKwa
    void deleteKnowledgeUnitKwaByUnitids(@Param("unitids") List<String> unitids);
    // 根据kwaid批量删除Unitkwa
    void deleteKnowledgeUnitKwaByKwaIds(@Param("kwaIds") List<String> kwaIds);
    //批量删除Unit
    void deleteKnowledgeUnitByUnitids(@Param("unitids") List<String> unitids);
    // 删除所有unit的连线
    void deleteLineByUnitIds(@Param("unitids") List<String> unitids);
    //更新知识单元
    @Update("update cm_course_unit set name=#{name},nodeType=#{type},dataValue=#{datavalue} where id=#{id}")
    void updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit);
    //更改kwa状态
    @Update("update cm_course_unit_kwa set status=#{status} where kwaId=#{kwaid} and unitId=#{unitid}")
    void updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    @Select("select count(*) from cm_course_unit where id=#{id}")
    long getUnitCountByUnitId(@Param("id") String id);

    @Select("select count(*) from cm_course_unit_kwa where kwaId=#{kwaid} and unitid=#{unitid}")
    long getUnitKwaCount(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);

    @Select("select count(*) from cm_course where id=#{id}")
    long getCourseCountByid(@Param("id") String id);

    // 查询某课程下所有知识单元（完整字段，用于复制）
    @Select("select id, pId as pid, name, nodeType as type, dataValue as datavalue, courseId as courseid, orderNum as ordernum " +
            "from cm_course_unit where courseId=#{courseId} order by orderNum asc")
    List<CmKnowledgeUnit> selectAllUnitsByCourseId(@Param("courseId") String courseId);

    // 删除某课程下所有知识单元
    @Delete("delete from cm_course_unit where courseId=#{courseId}")
    void deleteUnitsByCourseId(@Param("courseId") String courseId);

    // 批量插入知识单元
    @Insert("<script>" +
            "insert into cm_course_unit(id, courseId, pId, orderNum, name, nodeType, dataValue) values " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.id}, #{item.courseid}, #{item.pid}, #{item.ordernum}, #{item.name}, #{item.type}, #{item.datavalue})" +
            "</foreach>" +
            "</script>")
    void batchInsertUnits(@Param("list") List<CmKnowledgeUnit> list);

    void flashKnowledgeUnitOrdernum(@Param("unitid") String unitid,@Param("courseid") String courseid,@Param("preOrdernum") long preOrdernum,@Param("beginOrdernum") long beginOdernum,@Param("endOrdernum") long endOrdernum);

    void updateKnowledgeUnitOrdernum(@Param("id") String id,@Param("newOrdernum") long newOrdernum);

    @Select("select ifnull(max(orderNum),0) from cm_course_unit where  pId=#{pid} and courseId=#{courseid}")
    long selectMaxOrdernum(@Param("pid") String pid,@Param("courseid") String courseid);

    //平移区间内ordernum
    @Update("update cm_course_unit set orderNum=orderNum+#{changeValue} where pId=#{pid} and courseId=#{courseid} and orderNum>=#{minOrdernum} and orderNum<=#{maxOrdernum}")
    void updateOtherKnowledgeUnitOrdernum(@Param("changeValue") int changeValue,@Param("pid") String unitid,@Param("courseid") String courseid,@Param("minOrdernum") long newOrdernum,@Param("maxOrdernum") long oldOrdernum);

    @Select("select orderNum from cm_course_unit where id=#{id}")
    long getOrdernumById(@Param("id") String id);
}
