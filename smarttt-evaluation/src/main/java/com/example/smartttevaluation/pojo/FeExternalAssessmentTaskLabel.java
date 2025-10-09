package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 外部考核任务标签表
 */
@Data
@TableName("fe_external_assessment_task_label")
public class FeExternalAssessmentTaskLabel {

    /** 主键ID（字符串自动生成） */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 课堂ID（外键引用 cm_classroom.id） */
    private String classroomId;

    /** 外部分类名称 */
    private String labelName;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
