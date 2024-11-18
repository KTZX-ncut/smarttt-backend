package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmGetability;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.pojo.CmKwadict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CmKwadictMapper {
    /**
     * 获取kwa列表
     */
//    @Select("select kwa.id as id, kwa.keywordid as keywordid, kwa.abilityid as abilityid, k.name as keywordname, a.name as abilityname, kwa.courseid as courseid, kwa.datavalue as datavalue from cm_kwadict kwa " +
//            "inner join cm_keywords k on k.id = kwa.keywordid " +
//            "inner join cm_ability a on a.id = kwa.abilityid where kwa.courseid = #{obsid}")
//    List<CmKwadict> getKwadict(String obsid);
    @Select("select *, k.name as keywordname, a.name as abilityname from cm_kwadict kwa " +
            "inner join cm_keywords k on k.id = kwa.keywordid " +
            "inner join cm_ability a on a.id = kwa.abilityid where kwa.courseid=#{obsId}")
    List<CmKwadict> getKwadict(String obsId);

    /**
     * 新建kwa
     */
    @Update("INSERT INTO cm_kwadict (id, name, keywordid, abilityid, courseid, datavalue, createTime) " +
            "VALUES (#{kwa.id}, #{kwa.name}, #{kwa.keywordid}, #{kwa.abilityid}, #{kwa.courseid}, #{kwa.datavalue}, #{createTime})")
    void createKwadict(@Param("kwa") CmKwadict cmKwadict, @Param("createTime") LocalDateTime createTime);

    /**
     * 批量删除kwa
     */
    void deleteKwadictByIds(@Param("ids") List<String> ids);

    /**
     * 批量更新kwa
     */
    void updateKwadictByID(@Param("kwa") CmKwadict cmKwadict);

    /**
     * 通过keywordid和abilityid获取kwa
     */
    @Select("SELECT k.id AS keywordid , k.name AS keywordname, " +
            "a.id AS abilityid, a.name AS abilityname\n" +
            "FROM cm_kwadict kw \n" +
            "JOIN cm_getability a ON a.id = kw.abilityid\n" +
            "JOIN cm_keywords k ON k.id = kw.keywordid\n" +
            "WHERE k.id = #{keywordid} AND a.id = #{abilityid}")
    Object getKwadictBykeywordidAndabilityid(@Param("keywordid") String keywordid, @Param("abilityid") String abilityid);

    /**
     * 获取关键字字典
     */
    @Select("select id, name, datavalue, importantlevelid, remark, courseid from cm_keywords where courseid = #{courseid}")
    List<CmKeywords> getKeywordsDict(@Param("courseid") String courseid);

    /**
     * 获取能力字典
     */
    @Select("select cm_getability.id as id, cm_getability.orderno as orderno, cm_getability.abilitydeep as abilitydeep, cm_getability.levelcode as levelcode, cm_ability.name as name, " +
            "cm_getability.datavalue as datavalue, cm_getability.importantlevel as importantlevel, cm_getability.remark as remark, cm_getability.courseid as courseid from cm_getability, cm_ability " +
            "where courseid = #{courseid} and cm_getability.id = cm_ability.id")
    List<CmGetability> getAbilityDict(@Param("courseid") String courseid);

}
