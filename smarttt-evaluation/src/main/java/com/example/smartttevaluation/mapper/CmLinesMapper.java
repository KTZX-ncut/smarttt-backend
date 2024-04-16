package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmLines;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface CmLinesMapper {

    @Select("select id, startid, endid, remark, courseid from cm_lines")
    List<CmLines> getLines ();

    @Update("INSERT INTO cm_lines (id, startid, endid, remark, courseid) " +
            "VALUES (#{id},#{startid},#{endid},#{remark},#{courseid})")
    void createLines(CmLines cmLines);

    void deleteLinesByIDs(@Param("ids")List<String> ids);

}
