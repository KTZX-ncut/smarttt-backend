package com.example.smartttevaluation.schedule.chain.handlers;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.example.smartttevaluation.mapper.GlobalMessageMapper;
import com.example.smartttevaluation.schedule.chain.AbstractMessageChainHandler;
import com.example.smartttevaluation.schedule.entity.EduMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 更新配置试卷消息的责任链处理器
 */
@Component
@Order(2) // 优先级第二
public class UpdateCalPaperChainHandler extends AbstractMessageChainHandler {
    
    private final List<String> supportedTypes = Arrays.asList("test_update");
    private final GlobalMessageMapper globalMessageMapper;
    
    public UpdateCalPaperChainHandler(GlobalMessageMapper globalMessageMapper) {
        super("UpdateCalPaperChainHandler");
        this.globalMessageMapper = globalMessageMapper;
    }
    
    @Override
    protected boolean doHandle(List<EduMessage> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            return true;
        }
        
        try {
            for (EduMessage message : messages) {
                // 1. 转化context为map结构
                String context = message.getContext();
                Map<String, Object> contextMap = JSON.parseObject(context, Map.class);
                
                String testId = message.getIndexId();
                // 2. 更新配置试卷信息
                String paperName = (String) contextMap.get("paperName");
                String testName = (String) contextMap.get("testName");
                // 目前只考虑更新名字
                globalMessageMapper.updateCalPaperName(testId, paperName, testName);
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException("更新配置试卷信息失败", e);
        }
    }
    
    @Override
    public List<String> getSupportedMessageTypes() {
        return supportedTypes;
    }
}
