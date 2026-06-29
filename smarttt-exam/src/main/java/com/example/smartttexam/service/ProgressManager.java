package com.example.smartttexam.service;

import com.example.smartttexam.dto.TaskProgress;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** 任务进度管理器（内存存储） */
@Component
public class ProgressManager {

    private final Map<String, TaskProgress> tasks = new ConcurrentHashMap<>();

    public void create(String taskId) {
        TaskProgress p = new TaskProgress();
        p.setTaskId(taskId);
        p.setStatus("PENDING");
        p.setStage("准备中...");
        p.setPercent(0);
        tasks.put(taskId, p);
    }

    public void update(String taskId, int percent, String stage) {
        TaskProgress p = tasks.get(taskId);
        if (p != null) {
            p.setPercent(percent);
            p.setStage(stage);
            p.setStatus("RUNNING");
        }
    }

    public void done(String taskId, int questionCount) {
        TaskProgress p = tasks.get(taskId);
        if (p != null) {
            p.setStatus("DONE");
            p.setPercent(100);
            p.setStage("生成完成");
            p.setQuestionCount(questionCount);
        }
    }

    public void fail(String taskId, String errorMsg) {
        TaskProgress p = tasks.get(taskId);
        if (p != null) {
            p.setStatus("FAILED");
            p.setErrorMsg(errorMsg);
            p.setStage("生成失败");
        }
    }

    public TaskProgress get(String taskId) {
        return tasks.get(taskId);
    }
}
