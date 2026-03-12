package com.example.smartttevaluation.mapper;


import com.example.smartttevaluation.pojo.IdeologyValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdeologyValueMapper {


    void createFirstLevelNode(@Param("id") String id, @Param("courseId") String courseId);

    void modifyValueName(@Param("ideologyValue") IdeologyValue ideologyValue);

    void addSameLevelNode(@Param("id") String id, @Param("ideologyValue") IdeologyValue ideologyValue);

    void addSubLevelNode(@Param("id") String id, @Param("ideologyValue") IdeologyValue ideologyValue);

    void deleteNode(@Param("id") String id);

    List<IdeologyValue> selectAllNode(@Param("courseId")  String courseId);

    IdeologyValue selectById(@Param("id") String id);

    /**
     * 获取 V 类型列表（一级节点）
     */
    List<IdeologyValue> selectValueTypes(@Param("courseId") String courseId);
}
