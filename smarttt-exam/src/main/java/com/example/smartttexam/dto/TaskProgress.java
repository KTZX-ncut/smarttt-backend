package com.example.smartttexam.dto;

import lombok.Data;

/** AI生成题目任务进度 */
@Data
public class TaskProgress {
    private String taskId;
    private String status;    // PENDING / RUNNING / DONE / FAILED
    private String stage;     // 当前阶段描述
    private int percent;      // 0-100
    private String errorMsg;
    private int questionCount; // 完成后：生成了多少题
}
