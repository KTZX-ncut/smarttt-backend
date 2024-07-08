package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmLines;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface CmLinesMapper {
    /**
     *获取连线列表
     */
    @Select("select id, startkwaid, endkwaid, remark, courseid, startunitid, endunitid from cm_lines")
    List<CmLines> getLines ();
    /**
     *创建连线
     */
    @Update("INSERT INTO cm_lines (id, startkwaid, endkwaid, remark, courseid, startunitid, endunitid) " +
            "VALUES (#{id},#{startkwaid},#{endkwaid},#{remark},#{courseid},#{startunitid},#{endunitid})")
    void createLines(CmLines cmLines);
    /**
     *删除连线
     */
    void deleteLinesByIDs(@Param("ids")List<String> ids);

}
