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
 * 达成度表
 */
@Data
@TableName("fe_achievements")
public class FeAchievements {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 学生ID，外键引用cm_users.id
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 课程ID，外键引用cm_course.id
     */
    @TableField("course_id")
    private String courseId;

    /**
     * 课程目标ID，外键引用fe_course_objectives.id
     */
    @TableField("objective_id")
    private String objectiveId;

    /**
     * 课堂id，外键引用cm_classroom.id
     */
    @TableField("classroom_id")
    private String classroomId;

    /**
     * 达成度分数（0-100或0-1，根据业务规范）
     */
    @TableField("achievement_score")
    private BigDecimal achievementScore;

    /**
     * 数据来源（手动输入、Excel导入、系统计算）
     */
    @TableField("source")
    private String source;

    /**
     * 备注（如导入批次）
     */
    @TableField("remarks")
    private String remarks;

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
