package com.example.smartttevaluation.schedule;

import com.example.smartttevaluation.schedule.entity.EduMessage;

import java.util.List;

/**
 * 消息拉取类
 */
public interface MessageDealler {

    /**
     * 拉取相应的type消息
     */
    List<EduMessage> pull();

    /**
     * 消费消息
     */
    Boolean process(List<EduMessage> messageList);

    /**
     * 修改消息状态
     */
    void updateStateToComplete(List<Long> messageIdList);
}
