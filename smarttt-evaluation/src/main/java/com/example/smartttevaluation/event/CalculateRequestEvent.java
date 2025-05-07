package com.example.smartttevaluation.event;

import com.example.smartttevaluation.dto.CalculatePortraitReq;
import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步处理形成性评价的一键计算功能
 */
public class CalculateRequestEvent extends ApplicationEvent {

    public static final ConcurrentHashMap<String, String> taskMap = new ConcurrentHashMap<>();
    private final CalculatePortraitReq calculatePortraitReq;

    private final String taskId;

    public CalculateRequestEvent(Object source, CalculatePortraitReq calculatePortraitReq) {
        super(source);
        this.calculatePortraitReq = calculatePortraitReq;
        this.taskId = "taskId_" + Clock.systemDefaultZone().millis();
    }

    public String getTaskId() {
        return taskId;
    }

    public CalculatePortraitReq getCalculatePortraitReq() {
        return calculatePortraitReq;
    }
}
