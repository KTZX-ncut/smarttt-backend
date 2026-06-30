package com.example.smartttevaluation.service;


import com.example.smartttevaluation.pojo.IdeologyValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdeologyValueService {
    void createFirstLevelNode(String courseId);

    void modifyValueName(IdeologyValue ideologyValue);

    void addSameLevelNode(IdeologyValue ideologyValue);

    void addSubLevelNode(IdeologyValue ideologyValue);


    void deleteNode(String id);

    List<IdeologyValue> selectAllNode(String courseId);

    IdeologyValue selectById(String id);
}
