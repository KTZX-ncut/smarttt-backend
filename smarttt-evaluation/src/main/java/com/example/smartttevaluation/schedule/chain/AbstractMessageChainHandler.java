package com.example.smartttevaluation.schedule.chain;

import com.example.smartttevaluation.schedule.entity.EduMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息责任链处理器抽象类
 * 提供责任链的基础实现
 */
@Slf4j
public abstract class AbstractMessageChainHandler implements MessageChainHandler {
    
    private MessageChainHandler nextHandler;
    private final String handlerName;
    
    public AbstractMessageChainHandler(String handlerName) {
        this.handlerName = handlerName;
    }
    
    @Override
    public boolean handle(List<EduMessage> messages) {
        if (messages == null || messages.isEmpty()) {
            return true;
        }
        
        // 过滤出当前处理器支持的消息类型
        List<EduMessage> supportedMessages = messages.stream()
                .filter(message -> getSupportedMessageTypes().contains(message.getType()))
                .collect(Collectors.toList());
        
        if (supportedMessages.isEmpty()) {
            log.debug("处理器 {} 没有匹配的消息类型，跳过处理", handlerName);
            return handleNext(messages);
        }
        
        try {
            log.info("处理器 {} 开始处理 {} 条消息", handlerName, supportedMessages.size());
            boolean result = doHandle(supportedMessages);
            
            if (result) {
                log.info("处理器 {} 处理成功", handlerName);
            } else {
                log.warn("处理器 {} 处理失败", handlerName);
            }
            
            // 无论当前处理器是否成功，都继续传递给下一个处理器
            return handleNext(messages);
            
        } catch (Exception e) {
            log.error("处理器 {} 处理消息时发生异常: {}", handlerName, e.getMessage(), e);
            // 发生异常时也继续传递给下一个处理器
            return handleNext(messages);
        }
    }
    
    /**
     * 具体的消息处理逻辑，由子类实现
     * @param messages 需要处理的消息列表
     * @return 处理结果
     */
    protected abstract boolean doHandle(List<EduMessage> messages);
    
    /**
     * 处理下一个处理器
     * @param messages 消息列表
     * @return 处理结果
     */
    private boolean handleNext(List<EduMessage> messages) {
        if (nextHandler != null) {
            return nextHandler.handle(messages);
        }
        return true;
    }
    
    @Override
    public void setNext(MessageChainHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    @Override
    public MessageChainHandler getNext() {
        return nextHandler;
    }
    
    @Override
    public String getHandlerName() {
        return handlerName;
    }
}
