package com.example.smartttevaluation.vo;

import lombok.Data;

@Data
public class ReachObjectiveEvaluationVO {
    private String userId;
    private String stuNo;
    private String studentName;
    private String objectiveId;
    private String objectiveName;
    private String achievementScore;
}
