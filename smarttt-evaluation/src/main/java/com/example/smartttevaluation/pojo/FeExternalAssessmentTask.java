package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 外部考核任务表
 */
@Data
@TableName("fe_external_assessment_task")
public class FeExternalAssessmentTask {

    /** 外部考核ID（字符串自动生成） */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 外部考核标签ID（外键引用 fe_external_assessment_task_label.id） */
    private String labelId;

    /** 外部考核名称 */
    private String exAssessmentName;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
