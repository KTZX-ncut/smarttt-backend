package com.example.smartttevaluation.dto;

import lombok.Data;


@Data
public class KwaEvalDto {
    /**
     * kwaId
     */
    private String id;
    /**
     * kwa总平均分
     */
    private Double avgLibScore;
    /**
     * 学生得的kwa平均分
     */
    private Double avgLibStuScore;
}
