package com.example.smartttevaluation.dto;

import lombok.Data;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-12-27 20:47
 */
@Data
public class CalculatePortraitReq {
    String courseId;
    String classroomId;
    /**
     * 留个口子
     */
    List<String> stuIdList;
    List<String> paperIdList;
    List<String> excludeStuIdList;
    List<String> excludePaperIdList;
}
