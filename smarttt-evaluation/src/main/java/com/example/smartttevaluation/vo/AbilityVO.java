package com.example.smartttevaluation.vo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author lunSir
 * @create 2024-11-29 13:29
 */
@Data
public class AbilityVO {
    /**
     * id
     */
    private String id;
    /**
     * 关键字
     */
    private String abilityName;
    /**
     * 重要程度
     */
    private Double importLevel;
    /**
     * ability的分数
     */
    private Double dataValue;
    /**
     * 学生的得分数
     */
    private Double stuDataValue;
}
