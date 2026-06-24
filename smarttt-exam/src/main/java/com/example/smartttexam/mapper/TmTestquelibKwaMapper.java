package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.TmTestquelibKwa;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TmTestquelibKwaMapper {

    int batchInsert(@Param("list") List<TmTestquelibKwa> list);

    /** 根据题目ID列表批量查KWA */
    List<TmTestquelibKwa> getByLibIds(@Param("libIds") List<String> libIds);
}
