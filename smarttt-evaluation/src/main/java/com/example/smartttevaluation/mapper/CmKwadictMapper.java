package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmGetability;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.pojo.CmKwadict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmKwadictMapper {
    /**
     *获取kwa列表
     */
    @Select("select id, name, keywordid, abilityid, keywordname, abilityname, courseid, datavalue from cm_kwadict where courseid = #{courseid}")
    List<CmKwadict> getKwadict (String courseid);


    //@Update("INSERT INTO cm_kwadict (id, name, keywordid, abilityid, keywordname, abilityname, courseid, datavalue) " +
    //        "SELECT k.id, k.name, #{cmKwadict.keywordid}, #{cmKwadict.abilityid}, #{cmKwadict.keywordname}, #{cmKwadict.abilityname}, #{cmKwadict.courseid}, #{cmKwadict.datavalue} " +
    //        "FROM cm_keywords k " +
    //        "JOIN cm_getability a ON k.id = a.id " +
    //        "WHERE k.id = #{cmKwadict.id} AND a.id = #{cmKwadict.abilityid}")
    //void createKwadict(CmKwadict cmKwadict);
//"WHERE kw.id = #{id} AND kw.name = #{name} AND kw.keywordid = #{keywordid} AND kw.abilityid = #{abilityid} AND kw.keywordname = #{keywordname} AND kw.abilityname =#{abilityname} AND kw.courseid = #{courseid} AND kw.datavalue = #{datavalue}")
    /**
     *新建kwa
     */
    @Update("INSERT INTO cm_kwadict (id, name, keywordid, abilityid, keywordname, abilityname, courseid, datavalue) " +
            "VALUES (#{id},#{name},#{keywordid},#{abilityid}, #{keywordname}, #{abilityname}, #{courseid}, #{datavalue})")
    void createKwadict(CmKwadict cmKwadict);


    @Select("INSERT INTO cm_kwadict (id, name, keywordid, abilityid, keywordname, abilityname, courseid, datavalue)"+
            "SELECT kw.id, kw.name, k.id, a.id, k.name, a.name, kw.courseid, kw.datavalue\n" +
            "FROM cm_kwadict kw\n" +
            "JOIN cm_getability a ON a.id = kw.abilityid AND a.name = kw.abilityname\n" +
            "JOIN cm_keywords k ON k.id = kw.keywordid AND k.name = kw.keywordname\n" +
            "WHERE k.id = #{keywordid} AND a.id = #{abilityid} AND k.name = #{keywordname} AND a.name = #{abilityname}")
    void IScreateKwadict(CmKwadict cmKwadict);
    /**
     *批量删除kwa
     */
    void deleteKwadictByIDs(@Param("ids")List<String> ids);
    /**
     *更新kwa
     */
    void updateKwadictByID(CmKwadict cmKwadict);
    /**
     *通过KeywordId获取关键字
     */
    @Select("SELECT k.id, k.name, k.datavalue, k.importantlevelid, k.remark, k.courseid\n" +
            "FROM cm_keywords k\n"+
            "JOIN cm_kwadict kw ON k.id = kw.keywordid\n"+
            "JOIN cm_kwadict kw ON k.name = kw.keywordname\n"+
            "WHERE k.id = #{keywordid}")
    CmKeywords getKeywordsByKeywordId(@Param("keywordid") String keywordid);

    //@Insert("INSERT INTO cm_kwadict (keywordid, keywordname) " +
    //        "SELECT k.id, k.name " +
    //        "FROM cm_keywords k " +
    //        "WHERE k.id = #{keywordid}")
    //List<Keywordlist> getKeywordsByKeywordId(@Param("keywordid") String keywordid);
    /**
     *通过AbilityId获取能力
     */
    @Select("SELECT a.id, a.name, a.datavalue, a.importantlevel, a.remark, a.courseid\n" +
            "FROM cm_getability a\n"+
            "JOIN cm_kwadict kw ON a.id = kw.abilityid\n" +
            "JOIN cm_kwadict kw ON a.name = kw.abilityname\n"+
            "WHERE a.id = #{abilityid}")
    CmGetability getAbilityByAbilityId(@Param("abilityid") String abilityid);
    /**
     *通过keywordid和abilityid获取kwa
     */
    @Select("SELECT k.id AS keywordid , k.name AS keywordname, " +
            "a.id AS abilityid, a.name AS abilityname\n" +
            "FROM cm_kwadict kw \n"+
            "JOIN cm_getability a ON a.id = kw.abilityid\n"+
            "JOIN cm_keywords k ON k.id = kw.keywordid\n"+
            "WHERE k.id = #{keywordid} AND a.id = #{abilityid}")
    Object getKwadictBykeywordidAndabilityid(@Param("keywordid")String keywordid, @Param("abilityid")String abilityid);
    /**
     *获取关键字字典
     */
    @Select("select id, name, datavalue, importantlevelid, remark, courseid from cm_keywords")
    List<CmKeywords> getKeywordsDict(@Param("courseid") String courseid);
    /**
     *获取能力字典
     */
    @Select("select id, orderno, abilitydeep, levelcode, name, datavalue, importantlevel, remark, courseid from cm_getability")
    List<CmGetability> getAbilityDict(@Param("courseid") String courseid);

}
