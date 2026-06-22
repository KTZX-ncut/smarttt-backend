package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 手动组卷请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperManualGenerateRequest {

    /** 试卷名称 */
    private String name;
    /** 课程ID */
    private String courseId;
    /** 课堂ID */
    private String classroomId;
    /** 手动选择的题目ID列表 */
    private List<String> libIds;
    /** 每道题对应的分值（与libIds一一对应） */
    private List<Integer> scores;
    /** 试卷类型：1=作业, 2=考试 */
    private String catelog;
    /** 创建人ID（后端填充） */
    private String creatorId;
    /** 创建人姓名（后端填充） */
    private String creator;
}
