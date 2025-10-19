package com.example.smartttevaluation.vo;

import lombok.Data;

@Data
public class ReachCategoryEvaluationVO {
    private String userId;
    private String stuNo;
    private String studentName;
    private String assessmentCategoryId;
    private String assessmentCategoryName;
    private Double percent;
    private String achievementScore;
}
