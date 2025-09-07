package com.example.smartttevaluation.dto;

import lombok.Data;

/**
 * 考核项DTO
 */
@Data
public class AssessmentItemDto {

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 课堂ID
     */
    private String classroomId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课堂名称
     */
    private String classroomName;

    /**
     * 考核项名称
     */
    private String itemName;

    /**
     * 考核项类型（作业、实验、考试）
     */
    private String itemType;

    /**
     * 类型ID（对应实验ID/作业ID/考试ID）
     */
    private String typeId;
}
