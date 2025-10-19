package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fe_external_assessment_task_detail")
public class ExternalAssessmentTaskDetail {

    private String id;

    private String exAssessmentTaskId;

    private String stuno;

    private String studentName;

    private Integer stuScore;

    private String fullScore;

    private String createdAt;

    private String updatedAt;
}
