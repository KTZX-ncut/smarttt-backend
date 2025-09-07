package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 课程目标表
 */
@Data
@TableName("fe_course_objectives")
public class FeCourseObjectives{

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
     * 目标名称
     */
    @TableField("objective_name")
    private String objectiveName;

    /**
     * 目标描述
     */
    @TableField("objective_description")
    private String objectiveDescription;

    /**
     * 权重（0-1之间）
     */
    @TableField("weight")
    private BigDecimal weight;

    /**
     * 展示顺序（支持拖动调整）
     */
    @TableField("sort_order")
    private Integer sortOrder;

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
