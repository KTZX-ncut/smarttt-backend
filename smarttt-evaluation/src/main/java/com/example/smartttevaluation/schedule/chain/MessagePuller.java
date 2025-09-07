package com.example.smartttevaluation.schedule.chain;

import com.example.smartttevaluation.mapper.GlobalMessageMapper;
import com.example.smartttevaluation.schedule.entity.EduMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息拉取器
 * 负责从数据库拉取待处理的消息
 */
@Component
@Slf4j
public class MessagePuller {
    
    private final GlobalMessageMapper globalMessageMapper;
    private final MessageChainManager chainManager;
    
    public MessagePuller(GlobalMessageMapper globalMessageMapper, MessageChainManager chainManager) {
        this.globalMessageMapper = globalMessageMapper;
        this.chainManager = chainManager;
    }
    
    /**
     * 拉取并处理消息
     * @return 处理结果
     */
    public boolean pullAndProcessMessages() {
        try {
            // 获取所有支持的消息类型
            List<String> supportedTypes = chainManager.getAllSupportedMessageTypes();
            
            if (supportedTypes.isEmpty()) {
                log.debug("没有支持的消息类型");
                return true;
            }
            
            // 从数据库拉取消息
            List<EduMessage> messages = globalMessageMapper.pullMessage(supportedTypes);
            
            if (messages == null || messages.isEmpty()) {
                log.debug("没有拉取到待处理的消息");
                return true;
            }
            
            log.info("拉取到 {} 条待处理消息", messages.size());
            
            // 使用责任链处理消息
            boolean result = chainManager.processMessages(messages);
            
            if (result) {
                // 处理成功，更新消息状态为完成
                List<Long> messageIds = messages.stream()
                                .map(EduMessage::getId)
                                .collect(Collectors.toList());
                updateMessageStateToComplete(messageIds);
                log.info("消息处理完成，已更新 {} 条消息状态", messageIds.size());
            } else {
                log.warn("消息处理失败");
            }
            
            return result;
            
        } catch (Exception e) {
            log.error("拉取和处理消息时发生异常: {}", e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * 更新消息状态为完成
     * @param messageIds 消息ID列表
     */
    private void updateMessageStateToComplete(List<Long> messageIds) {
        if (messageIds == null || messageIds.isEmpty()) {
            return;
        }
        
        try {
            globalMessageMapper.updateMessageState(messageIds, "complete");
        } catch (Exception e) {
            log.error("更新消息状态失败: {}", e.getMessage(), e);
        }
    }
}
