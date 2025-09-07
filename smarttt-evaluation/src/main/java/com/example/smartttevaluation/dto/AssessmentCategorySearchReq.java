package com.example.smartttevaluation.dto;

import lombok.Data;

/**
 * 考核项类别搜索请求类
 */
@Data
public class AssessmentCategorySearchReq {

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 类别名称（模糊搜索）
     */
    private String categoryName;

    /**
     * 类别描述（模糊搜索）
     */
    private String categoryDescription;

    /**
     * 最小分数
     */
    private Integer minScore;

    /**
     * 最大分数
     */
    private Integer maxScore;

    /**
     * 当前页（分页参数）
     */
    private Long current = 1L;

    /**
     * 每页大小（分页参数，-1表示不分页）
     */
    private Long size = -1L;

    /**
     * 排序字段（createdAt, updatedAt）
     */
    private String sortField = "createdAt";

    /**
     * 排序方向（asc, desc）
     */
    private String sortOrder = "asc";
}
