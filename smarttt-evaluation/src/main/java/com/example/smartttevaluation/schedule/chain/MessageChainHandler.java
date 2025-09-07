package com.example.smartttevaluation.schedule.chain;

import com.example.smartttevaluation.schedule.entity.EduMessage;

import java.util.List;

/**
 * 消息责任链处理器接口
 * 每个处理器负责处理特定类型的消息
 */
public interface MessageChainHandler {
    
    /**
     * 处理消息
     * @param messages 消息列表
     * @return 处理结果
     */
    boolean handle(List<EduMessage> messages);
    
    /**
     * 设置下一个处理器
     * @param nextHandler 下一个处理器
     */
    void setNext(MessageChainHandler nextHandler);
    
    /**
     * 获取下一个处理器
     * @return 下一个处理器
     */
    MessageChainHandler getNext();
    
    /**
     * 获取处理器名称
     * @return 处理器名称
     */
    String getHandlerName();
    
    /**
     * 获取支持的消息类型
     * @return 支持的消息类型列表
     */
    List<String> getSupportedMessageTypes();
}
