package com.example.smartttexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分数模拟请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreSimulationRequest {

    /** 试卷ID（pm_testpaper.id） */
    private String paperId;
    /** 已发布的考试ID（pm_test.id），paperId和testId二选一 */
    private String testId;
    /** 课堂ID，用于获取学生列表 */
    private String classroomId;
}
