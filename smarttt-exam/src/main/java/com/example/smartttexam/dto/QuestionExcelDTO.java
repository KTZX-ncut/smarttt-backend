package com.example.smartttexam.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EasyExcel读取AI生成的题目Excel时使用的映射DTO
 * Excel列: question(题目), level(难度1-3), type(1=客观/2=主观), score(分值), keywords(关联关键字)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionExcelDTO {

    @ExcelProperty("question")
    private String question;

    @ExcelProperty("level")
    private Integer level;

    @ExcelProperty("type")
    private Integer type;

    @ExcelProperty("score")
    private Integer score;

    @ExcelProperty("KWA")
    private String keywords;
}
