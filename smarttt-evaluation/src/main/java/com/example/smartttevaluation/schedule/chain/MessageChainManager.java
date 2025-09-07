package com.example.smartttevaluation.schedule.chain;

import com.example.smartttevaluation.schedule.entity.EduMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息责任链管理器
 * 负责组织和管理消息处理器的责任链
 */
@Component
@Slf4j
public class MessageChainManager {
    
    private final List<MessageChainHandler> chainHandlers;
    
    public MessageChainManager(List<MessageChainHandler> chainHandlers) {
        this.chainHandlers = chainHandlers;
        buildChain();
    }
    
    /**
     * 构建责任链
     * Spring已经根据@Order注解对处理器进行了排序
     */
    private void buildChain() {
        if (chainHandlers == null || chainHandlers.isEmpty()) {
            log.warn("没有找到任何消息链处理器");
            return;
        }
        
        // Spring已经根据@Order注解对处理器进行了排序，直接构建责任链
        for (int i = 0; i < chainHandlers.size() - 1; i++) {
            chainHandlers.get(i).setNext(chainHandlers.get(i + 1));
        }
        
        log.info("消息责任链构建完成，处理器数量: {}", chainHandlers.size());
        logChainStructure();
    }
    
    /**
     * 处理消息
     * @param messages 消息列表
     * @return 处理结果
     */
    public boolean processMessages(List<EduMessage> messages) {
        if (messages == null || messages.isEmpty()) {
            log.debug("没有消息需要处理");
            return true;
        }
        
        if (chainHandlers == null || chainHandlers.isEmpty()) {
            log.warn("没有可用的消息处理器");
            return false;
        }
        
        // 按消息类型分组
        Map<String, List<EduMessage>> messagesByType = messages.stream()
                .collect(Collectors.groupingBy(EduMessage::getType));
        
        log.info("开始处理消息，总数量: {}, 类型分布: {}", 
                messages.size(), 
                messagesByType.keySet());
        
        // 从第一个处理器开始处理
        MessageChainHandler firstHandler = chainHandlers.get(0);
        return firstHandler.handle(messages);
    }
    
    /**
     * 打印责任链结构
     */
    private void logChainStructure() {
        if (chainHandlers.isEmpty()) {
            return;
        }
        
        StringBuilder chainInfo = new StringBuilder("责任链结构: ");
        MessageChainHandler current = chainHandlers.get(0);
        
        while (current != null) {
            chainInfo.append(current.getHandlerName());
            chainInfo.append("(").append(current.getSupportedMessageTypes()).append(")");
            
            current = current.getNext();
            if (current != null) {
                chainInfo.append(" -> ");
            }
        }
        
        log.info(chainInfo.toString());
    }
    
    /**
     * 获取所有支持的消息类型
     * @return 支持的消息类型集合
     */
    public List<String> getAllSupportedMessageTypes() {
        return chainHandlers.stream()
                .flatMap(handler -> handler.getSupportedMessageTypes().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
