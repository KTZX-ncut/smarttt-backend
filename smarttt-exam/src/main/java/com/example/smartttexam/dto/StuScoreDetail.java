package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 学生得分明细（含姓名和逐题分） */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuScoreDetail {
    private String stuId;
    private String stuName;
    private long totalScore;
    private long maxScore;
    private java.util.List<QuestionScore> questions;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class QuestionScore {
    private String libId;
    private String content;
    private long libScore;      // 满分
    private long libStuScore;   // 学生得分
}
