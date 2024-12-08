package com.example.smartttevaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomInfoReq {
    private String classroomId;     // 课堂id
    private String classroomName;   // 课堂名称
    private String termName;        // 学期名称
    private String courseName;      // 课程名称
    private String professionName;  // 专业名称
    private String teacherName;     // 教师名称
    private long time;               // 学时
    private Number score;           // 学分
    private String assistantName;   // 助教名称
}
