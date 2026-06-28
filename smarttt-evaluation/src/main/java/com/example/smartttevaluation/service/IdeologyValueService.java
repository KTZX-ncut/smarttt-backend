package com.example.smartttevaluation.service;


import com.example.smartttevaluation.pojo.IdeologyValue;

import java.util.List;
import java.util.Map;

public interface IdeologyValueService {
    void createFirstLevelNode(String courseId);

    void modifyValueName(IdeologyValue ideologyValue);

    void addSameLevelNode(IdeologyValue ideologyValue);

    void addSubLevelNode(IdeologyValue ideologyValue);


    void deleteNode(String id);

    List<IdeologyValue> selectAllNode(String courseId);

    IdeologyValue selectById(String id);

    /**
     * 获取 V 类型列表（一级节点）
     */
    List<IdeologyValue> selectValueTypes(String courseId);

    /**
     * 复制历史课程思政价值标签树到当前课程，返回 oldId->newId 映射
     */
    Map<String, String> copyIdeologyValues(String pastCourseId, String currentCourseId);
}
