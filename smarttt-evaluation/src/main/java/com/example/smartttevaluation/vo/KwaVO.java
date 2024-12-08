package com.example.smartttevaluation.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Value;

/**
 * @author lunSir
 * @create 2024-12-06 17:44
 */
@Data
// null不进行序列化
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class KwaVO {
    /**
     * id
     */
    private String id;
    /**
     * kwa
     */
    private String kwaName;
    /**
     * 重要程度
     */
    private Double importLevel;
    /**
     * kwa的分数
     */
    private Double dataValue;
    /**
     * 学生的得分率
     */
    private Double stuDataValue;
}
