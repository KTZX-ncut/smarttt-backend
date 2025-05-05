package com.example.smartttevaluation.listener;

import cn.hutool.json.JSONUtil;
import com.example.smartttevaluation.event.CalculateRequestEvent;
import com.example.smartttevaluation.service.AiInStuAnsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 形成性评价一键计算监听器
 */
@Component
@Slf4j
public class CalculateEventListener {

    @Resource
    private AiInStuAnsInfoService aiInStuAnsInfoService;


    @Async
    @EventListener
    public void handleCalculateRequest(CalculateRequestEvent event) {
        try {
            // 处理业务逻辑
            CalculateRequestEvent.taskMap.put(event.getTaskId(), "pedding");
            log.info("接收到事件：" + event.getCalculatePortraitReq());
            aiInStuAnsInfoService.calculatePortrait(event.getCalculatePortraitReq());
            CalculateRequestEvent.taskMap.put(event.getTaskId(), "success");
        }catch (Exception e){
            CalculateRequestEvent.taskMap.put(event.getTaskId(), "failed");
            log.error("计算失败：" + event.getCalculatePortraitReq() + "原因：" + e.getMessage());
        }

    }

}
