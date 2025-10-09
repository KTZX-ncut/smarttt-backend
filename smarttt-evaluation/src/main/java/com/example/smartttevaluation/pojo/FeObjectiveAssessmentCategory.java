package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("fe_objective_assessment_category")
public class FeObjectiveAssessmentCategory {

    @TableField("category_id")
    private String categoryId;

    @TableField("objective_id")
    private String objectiveId;

    @TableField("score")
    private Integer score;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
