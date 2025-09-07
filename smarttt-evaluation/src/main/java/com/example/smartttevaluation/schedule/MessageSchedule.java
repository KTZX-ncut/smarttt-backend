package com.example.smartttevaluation.schedule;

import com.example.smartttevaluation.schedule.chain.MessagePuller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 消息任务处理器
 * 使用责任链模式处理消息
 */
@Slf4j
public class MessageSchedule {

    private final MessagePuller messagePuller;
    private final PlatformTransactionManager transactionManager;

    public MessageSchedule(MessagePuller messagePuller, PlatformTransactionManager transactionManager) {
        this.messagePuller = messagePuller;
        this.transactionManager = transactionManager;
    }

    @Scheduled(fixedDelay = 10000)
    public void task() {
        log.info("开始执行消息处理任务...");
        
        // 开启事务
        TransactionDefinition transactionDefinition =
                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            // 使用责任链模式拉取和处理消息
            boolean result = messagePuller.pullAndProcessMessages();
            
            if (result) {
                // 提交事务
                transactionManager.commit(transactionStatus);
                log.info("消息处理任务执行成功");
            } else {
                // 回滚事务
                transactionManager.rollback(transactionStatus);
                log.warn("消息处理任务执行失败，已回滚");
            }
        } catch (Exception e) {
            // 回滚事务
            transactionManager.rollback(transactionStatus);
            log.error("消息处理任务执行异常: {}", e.getMessage(), e);
        }
        
        log.info("消息处理任务执行结束...");
    }
}
