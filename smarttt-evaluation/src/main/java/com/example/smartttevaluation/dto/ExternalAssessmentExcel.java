package com.example.smartttevaluation.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@EqualsAndHashCode
public class ExternalAssessmentExcel {
    @ExcelProperty("学号")
    private String stuNo;
    @ExcelProperty("姓名")
    private String studentName;
    @ExcelProperty("班级")
    private String className;
    // 外部考核名称
    @ExcelIgnore
    private Map<String, Double> assessmentMap = new LinkedHashMap<>();
}
