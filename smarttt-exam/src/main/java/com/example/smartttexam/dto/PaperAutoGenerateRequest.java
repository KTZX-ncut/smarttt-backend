package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自动组卷请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperAutoGenerateRequest {

    /** 试卷名称 */
    private String name;
    /** 课程ID */
    private String courseId;
    /** 课堂ID */
    private String classroomId;
    /** 目标难度等级 1=简单, 2=中等, 3=困难 */
    private Integer targetDifficulty;
    /** 客观题数量（填空） */
    private Integer objectiveCount;
    /** 主观题数量（简答） */
    private Integer subjectiveCount;
    /** 试卷类型：1=作业, 2=考试 */
    private String catelog;
    /** 创建人ID（后端填充） */
    private String creatorId;
    /** 创建人姓名（后端填充） */
    private String creator;
}
