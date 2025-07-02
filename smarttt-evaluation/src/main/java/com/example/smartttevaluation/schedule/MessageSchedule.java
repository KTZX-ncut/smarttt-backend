package com.example.smartttevaluation.schedule;

import com.example.smartttevaluation.schedule.entity.EduMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息任务处理器
 */
@Component
@Slf4j
public class MessageSchedule {

    private final List<MessageDealler> MessageDeallerList;

    /**
     * 事务管理器
     */
    private final PlatformTransactionManager transactionManager;

    public MessageSchedule(List<MessageDealler> MessageDeallerList, PlatformTransactionManager transactionManager) {
        this.MessageDeallerList = MessageDeallerList;
        this.transactionManager = transactionManager;
    }


    @Scheduled(fixedDelay = 10000)
    public void task() {
        log.info("开始执行任务...");
        for (MessageDealler messageDealler : MessageDeallerList){
            // 开启事物
            TransactionDefinition transactionDefinition =
                    new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);

            TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

            try {
                List<EduMessage> messages = messageDealler.pull();
                messageDealler.process(messages);
                messageDealler.updateStateToComplete(messages.stream()
                        .map(EduMessage::getId)
                        .collect(Collectors.toList()));
                // 提交事物
                transactionManager.commit(transactionStatus);
            } catch (Exception e) {
                // 回滚事物
                transactionManager.rollback(transactionStatus);
                e.printStackTrace();
                log.error("任务执行异常:{}", e.getMessage());

            }
        }
        log.info("任务执行结束...");
    }
}
