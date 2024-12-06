package com.example.smartttevaluation.dto;

import lombok.Data;


@Data
public class UnitEvalDto {
    /**
     * unitId
     */
    private String id;
    /**
     * 知识单元总平均分
     */
    private Double avgLibScore;
    /**
     * 该学生平均分
     */
    private Double avgLibStuScore;
}
