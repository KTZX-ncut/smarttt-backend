package com.example.smartttevaluation.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 课程目标搜索请求类
 */
@Data
public class CourseObjectiveSearchReq {

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 目标名称（模糊搜索）
     */
    private String objectiveName;

    /**
     * 目标描述（模糊搜索）
     */
    private String objectiveDescription;

    /**
     * 最小权重
     */
    private BigDecimal minWeight;

    /**
     * 最大权重
     */
    private BigDecimal maxWeight;

    /**
     * 当前页（分页参数）
     */
    private Long current = 1L;

    /**
     * 每页大小（分页参数，-1表示不分页）
     */
    private Long size = -1L;

    /**
     * 排序字段（sortOrder, createdAt, updatedAt）
     */
    private String sortField = "sortOrder";

    /**
     * 排序方向（asc, desc）
     */
    private String sortOrder = "asc";
}
