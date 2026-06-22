package com.example.smartttexam.mapper;

import com.example.smartttexam.pojo.TmTestquelibKwa;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目-KWA关联Mapper
 */
@Mapper
public interface TmTestquelibKwaMapper {

    /**
     * 批量插入题目-KWA关联
     */
    int batchInsert(@Param("list") List<TmTestquelibKwa> list);
}
