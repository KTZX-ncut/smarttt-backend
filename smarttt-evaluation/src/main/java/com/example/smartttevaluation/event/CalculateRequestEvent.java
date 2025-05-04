package com.example.smartttevaluation.event;

import com.example.smartttevaluation.dto.CalculatePortraitReq;
import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * 异步处理形成性评价的一键计算功能
 */
public class CalculateRequestEvent extends ApplicationEvent {

    private final CalculatePortraitReq calculatePortraitReq;

    public CalculateRequestEvent(Object source, CalculatePortraitReq calculatePortraitReq) {
        super(source);
        this.calculatePortraitReq = calculatePortraitReq;
    }

    public CalculatePortraitReq getCalculatePortraitReq() {
        return calculatePortraitReq;
    }
}
