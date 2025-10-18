package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 考核项类别表
 */
@Data
@TableName("fe_assessment_categories")
public class FeAssessmentCategories {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属课程ID，外键引用cm_course.id
     */
    @TableField("course_id")
    private String courseId;

    /**
     * 类别名称（如作业、实验）
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 类别描述
     */
    @TableField("category_description")
    private String categoryDescription;

    /**
     * 类别分数（范围：0-100）
     */
    @TableField("score")
    private Integer score;

    @TableField("percent")
    private Double percent;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
