package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmClassroomUnit;
import com.example.smartttevaluation.pojo.CmClassroomUnitKwa;
import com.example.smartttevaluation.pojo.CmGetability;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CmClassroomUnitMapper {
    @Select("select id,pid,name,ordernum,type from cm_classroom_unit where courseid=#{courseid} and pid=0 order by ordernum")
    List<CmClassroomUnit> getChapter (@Param("courseid") String courseid);

    @Select("select id,pid,name,ordernum,type from cm_classroom_unit where pid=#{pid} order by ordernum")
    List<CmClassroomUnit> getSection (@Param("pid") String pid);

    @Select("select cm_classroom_unit_kwa.kwaid,cm_kwadict.name,cm_classroom_unit_kwa.statue from cm_classroom_unit_kwa,cm_kwadict where cm_classroom_unit_kwa.kwaid=cm_kwadict.id and unitid=#{unitid}")
    List<CmClassroomUnitKwa> getClassroomUnitKwa (@Param("unitid") String unitid);

    @Insert("insert into cm_classroom_unit(id,pid,name,type,ordernum,courseid) values(#{id},0,#{name},#{type},#{ordernum},#{courseid})")
    void insertChapter(@Param("id") String id,@Param("name") String name,@Param("type") String type,@Param("ordernum") long ordernum,@Param("courseid") String courseid);

    @Insert("insert into cm_classroom_unit(id,pid,name,type,ordernum,courseid) values(#{id},#{pid},#{name},#{type},#{ordernum},#{courseid})")
    void insertSection(@Param("id") String id,@Param("pid") String pid,@Param("name") String name,@Param("type") String type,@Param("ordernum") long ordernum,@Param("courseid") String courseid);

    //获取章最大顺序号
    @Select("select max(ordernum) from cm_classroom_unit where courseid=#{courseid} and pid=0")
    long getMaxChapterOrdernum(String courseid);


    //获取节最大顺序号
    @Select("select max(ordernum) from cm_classroom_unit where pid=#{pid}")
    long getMaxSectionOrdernum(String pid);

    //添加能力单元kwa
    @Insert("insert into cm_classroom_unit_kwa(unitid,kwaid,statue) values(#{unitid},#{kwaid},#{statue})")
    void insertClassroomUnitKwa(@Param("unitid") String unitid,@Param("kwaid") String kwaid,@Param("statue") int statue);

    //批量删除kwa
    void deleteClassroomUnitkwa(@Param("unitid") String unitid,@Param("ids") List<String> kwaids);
    //批量删除多个小节下所有kwa
    void deleteAllUnitkwaByUnitid(@Param("sectionUnitids") List<String> sectionUnitids);
    //批量删除一章下所有小节kwa
    void deleteAllUnitkwaByChapterUnitid(@Param("chapterUnitid") String chapterUnitid);
    //批量删除节
    void deleteSection(@Param("sectionids") List<String> sectionids);
    //删除一章下所有小节
    void deleteAllSectionByChapterUnitid(@Param("chapterUnitid") String chapterUnit);

    //删除章

    void deleteChapter(@Param("chapterUnitid") String chapterUnitid);
}
