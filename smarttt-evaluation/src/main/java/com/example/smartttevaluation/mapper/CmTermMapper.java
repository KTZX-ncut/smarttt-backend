package com.example.smartttevaluation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CmTermMapper {
    @Select("select orderno from cm_term where iscurrentterm = 1")
    String getCurrentTermNo();
}
