package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmKeywords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CmKeywordsMapper {

    @Select("select id, name, datavalue, importantlevelid, remark, courseid from cm_keywords")
    List<CmKeywords> getKeywords ();

    @Update("INSERT INTO cm_keywords (id, name, datavalue, importantlevelid, remark) " +
            "VALUES (#{id},#{name},#{datavalue},#{importantlevelid},#{remark})")
    void createKeywords(CmKeywords cmKeywords);

    void deleteKeywordsByIDs(@Param("ids")List<String> ids);

    void updateKeywordsByID(CmKeywords cmKeywords);

}
