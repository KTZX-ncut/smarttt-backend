package com.example.smartttevaluation.dto;

import lombok.Data;

/**
 * 处理达成性评价的数据传输模型（可扩展）
 */
@Data
public class ReachEvaluationDto {
    /**
     * 学生得分
     */
    private Integer stuScore;
    

    /**
     * 试卷总分
     */
    private Integer fullScore;
}
