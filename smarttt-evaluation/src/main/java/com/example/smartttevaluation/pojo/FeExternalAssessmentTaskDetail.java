package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 外部考核任务详情表
 */
@Data
@TableName("fe_external_assessment_task_detail")
public class FeExternalAssessmentTaskDetail {

    /** 主键ID（字符串自动生成） */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 外部考核任务ID（外键引用 fe_external_assessment_task.id） */
    private String exAssessmentTaskId;

    /** 学号 */
    private String stuno;

    /** 学生姓名 */
    private String studentName;

    /** 学生得分 */
    private Double stuScore;

    /** 满分（默认100） */
    private Double fullScore;
}
