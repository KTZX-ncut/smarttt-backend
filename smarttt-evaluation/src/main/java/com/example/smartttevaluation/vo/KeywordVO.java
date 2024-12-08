package com.example.smartttevaluation.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

/**
 * @author lunSir
 * @create 2024-11-29 13:28
 */
@Data
public class KeywordVO {
    /**
     * id
     */
    private String id;
    /**
     * 关键字
     */
    private String keywordName;
    /**
     * 重要程度
     */
    private Double importLevel;
    /**
     * keyword的分数
     */
    private Double dataValue;
    /**
     * 学生的得分数
     */
    private Double stuDataValue;
}
