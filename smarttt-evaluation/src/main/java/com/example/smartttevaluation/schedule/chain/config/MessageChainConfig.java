package com.example.smartttevaluation.schedule.chain.config;

import com.example.smartttevaluation.schedule.chain.MessageChainHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 消息责任链配置类
 * 使用@Order注解自动配置处理器的优先级和顺序
 */
@Configuration
public class MessageChainConfig {
    
    /**
     * 配置消息处理器链
     * Spring会自动根据@Order注解对处理器进行排序
     * 数值越小，优先级越高
     */
    @Bean
    public List<MessageChainHandler> messageChainHandlers(List<MessageChainHandler> chainHandlers) {
        // Spring会自动注入所有MessageChainHandler的实现类
        // 并根据@Order注解进行排序
        return chainHandlers;
    }
}
