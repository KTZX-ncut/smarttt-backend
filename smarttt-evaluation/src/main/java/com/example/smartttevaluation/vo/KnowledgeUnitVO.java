package com.example.smartttevaluation.vo;

import lombok.Data;

import java.util.List;

/**
 * 知识单元的分析view
 * @author lunSir
 * @create 2024-12-01 17:23
 */
@Data
public class KnowledgeUnitVO {
    private String id;
    private String pid;
    private String unitName;
    private Double dataValue;
    private String type;
    private Integer orderNum;
    private Double stuDataValue;
}
