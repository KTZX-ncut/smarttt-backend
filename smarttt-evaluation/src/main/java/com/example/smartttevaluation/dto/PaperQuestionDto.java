package com.example.smartttevaluation.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaperQuestionDto {
    private String questionId;
    private String valueId;
    private String valueTypeId;
    private String questionTitle;
    private String questionContent;
    private String valueTypeName;
    private String valueName;
    private Integer valueCount;
}
