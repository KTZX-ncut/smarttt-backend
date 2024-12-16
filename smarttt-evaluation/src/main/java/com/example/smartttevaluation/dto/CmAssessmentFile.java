package com.example.smartttevaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 考核方案表的编辑弹窗中显示所有作业/测试/实验/自上传的成绩 的统一结构
public class CmAssessmentFile {
    private String id;  // 标识
    private String name;    // 名称
    private String createTime;    // 创建时间
    private String creator;     // 创建者
    private int totalScore;     // 总分
    private int type;   // 1-作业，2-测试，3-实验，4-用户自己上传的成绩
}
