package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmKeywords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmKeywordsMapper {

    /**
     * 获取关键字列表
     */
    @Select("select id, name, datavalue, importantlevelid, remark, courseid from cm_keywords")
    List<CmKeywords> getKeywords ();
    /**
     * 创建关键字
     */
    @Update("INSERT INTO cm_keywords (id, name, datavalue, importantlevelid, remark) " +
            "VALUES (#{id},#{name},#{datavalue},#{importantlevelid},#{remark})")
    void createKeywords(CmKeywords cmKeywords);
    /**
     * 删除关键字
     */
    void deleteKeywordsByIDs(@Param("ids")List<String> ids);
    /**
     * 更新关键字
     */
    void updateKeywordsByID(CmKeywords cmKeywords);
    /**
     * 通过关键字id获取kwa
     */
    List<String> getKwaByKeywordsId(@Param("courseid") String courseid, @Param("ids") List<String> ids);
    /**
     * 通过id获取课程数量
     */
    @Select("Select count(*) from cm_course where id=#{id}")
    long getNumOfCourseById(@Param("id") String id);
}
