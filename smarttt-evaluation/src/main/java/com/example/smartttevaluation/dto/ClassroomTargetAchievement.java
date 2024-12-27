package com.example.smartttevaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomTargetAchievement {
    private String id;              // 标识
    private String stuId;           // 学生userId
    private String classroomId;     // 班级id
    private String targetId;        // 课程目标id
    private float degree;           // 课程目标达成度
}
