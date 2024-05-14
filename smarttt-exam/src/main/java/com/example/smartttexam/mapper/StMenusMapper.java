package com.example.smartttexam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StMenusMapper {
    @Select("select status from st_rolemenu where roleid = #{roleid} and menuid = #{menuid}")
    List<String> getStatueInRoleUser(@Param("roleid") String roleid, @Param("menuid") String menu);
}
