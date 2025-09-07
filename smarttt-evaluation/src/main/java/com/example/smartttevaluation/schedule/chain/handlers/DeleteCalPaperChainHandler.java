package com.example.smartttevaluation.schedule.chain.handlers;

import cn.hutool.core.collection.CollectionUtil;
import com.example.smartttevaluation.mapper.GlobalMessageMapper;
import com.example.smartttevaluation.schedule.chain.AbstractMessageChainHandler;
import com.example.smartttevaluation.schedule.entity.EduMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 删除配置试卷消息的责任链处理器
 */
@Component
@Order(1) // 优先级最高
public class DeleteCalPaperChainHandler extends AbstractMessageChainHandler {
    
    private final List<String> supportedTypes = Arrays.asList("test_delete", "practice_delete");
    private final GlobalMessageMapper globalMessageMapper;
    
    public DeleteCalPaperChainHandler(GlobalMessageMapper globalMessageMapper) {
        super("DeleteCalPaperChainHandler");
        this.globalMessageMapper = globalMessageMapper;
    }
    
    @Override
    protected boolean doHandle(List<EduMessage> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            return true;
        }
        
        try {
            // 删除配置试卷信息
            for (EduMessage message : messages) {
                String testId = message.getIndexId();
                // 删除配置试卷信息
                globalMessageMapper.deleteCalPaper(testId);
            }
            // 保持与原有实现一致，返回true表示处理成功
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除配置试卷信息失败", e);
        }
    }
    
    @Override
    public List<String> getSupportedMessageTypes() {
        return supportedTypes;
    }
}
