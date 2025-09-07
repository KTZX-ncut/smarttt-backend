package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 考核项表
 */
@Data
@TableName("fe_assessment_items")
public class FeAssessmentItems {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 所属类别ID，外键引用fe_assessment_categories.id
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 所属课程ID，外键引用cm_course.id
     */
    @TableField("course_id")
    private String courseId;

    /**
     * 所属课堂ID，外键引用cm_classroom.id
     */
    @TableField("classroom_id")
    private String classroomId;

    /**
     * 考核项名称（如"作业1"、"期末考试"）
     */
    @TableField("item_name")
    private String itemName;

    /**
     * 类型（作业、实验、考试等）
     */
    @TableField("item_type")
    private String itemType;

    /**
     * 绑定的课程目标ID，外键引用fe_course_objectives.id（一个考核项一个目标）
     */
    @TableField("objective_id")
    private String objectiveId;

    /**
     * 考核项描述
     */
    @TableField("item_description")
    private String itemDescription;

    /**
     * 类型ID（对应实验ID/作业ID/考试ID）
     */
    @TableField("type_id")
    private String typeId;

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
