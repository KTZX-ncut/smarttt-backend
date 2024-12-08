package com.example.smartttevaluation.dto;

import lombok.Data;


@Data
public class KeywordEvalDto {
    /**
     * keywordId
     */
    private String id;
    /**
     * 关键字总平均分
     */
    private Double avgLibScore;
    /**
     * 学生得的关键字平均分
     */
    private Double avgLibStuScore;
}
