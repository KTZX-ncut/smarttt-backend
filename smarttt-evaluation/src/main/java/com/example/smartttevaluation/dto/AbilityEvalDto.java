package com.example.smartttevaluation.dto;

import lombok.Data;


@Data
public class AbilityEvalDto {
    /**
     * abilityId
     */
    private String id;
    /**
     * 能力总平均分
     */
    private Double avgLibScore;
    /**
     * 学生得的能力平均分
     */
    private Double avgLibStuScore;
}
