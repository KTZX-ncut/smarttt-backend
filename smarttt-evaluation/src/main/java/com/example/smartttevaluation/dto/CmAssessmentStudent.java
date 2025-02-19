package com.example.smartttevaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 课堂中的学生信息
public class CmAssessmentStudent {
    private String id;              // userId
    private int rowNo;              // 课堂学生序号
    private String className;       // 学生的班级名
    private String stuno;           // 学号
    private String username;        // 姓名
    private String loginname;       // 登陆用户名
    private float score;            // 成绩
    private int evaluationState;    // 是否参与形成性/达成性评价，0-不参加，1-参加
}
