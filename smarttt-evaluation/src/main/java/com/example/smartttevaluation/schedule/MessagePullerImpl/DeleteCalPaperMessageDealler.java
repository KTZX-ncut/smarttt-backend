package com.example.smartttevaluation.schedule.MessagePullerImpl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.example.smartttevaluation.mapper.GlobalMessageMapper;
import com.example.smartttevaluation.schedule.MessageDealler;
import com.example.smartttevaluation.schedule.entity.EduMessage;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 根据消息删除配置试卷的信息
 */
@Component
public class DeleteCalPaperMessageDealler implements MessageDealler {

    private final List<String> typeList = Arrays.asList("test_delete","practice_delete");
    private final GlobalMessageMapper globalMessageMapper;

    public DeleteCalPaperMessageDealler(GlobalMessageMapper globalMessageMapper) {
        this.globalMessageMapper = globalMessageMapper;
    }

    @Override
    public List<EduMessage> pull() {
        return globalMessageMapper.pullMessage(typeList);
    }

    @Override
    public Boolean process(List<EduMessage> messageList) {
        if (CollectionUtil.isEmpty(messageList)){
            return true;
        }
        // 删除配置试卷信息
        for (EduMessage message : messageList) {
            String testId = message.getIndexId();
            // 删除配置试卷信息
            globalMessageMapper.deleteCalPaper(testId);
        }
        return null;
    }

    @Override
    public void updateStateToComplete(List<Long> messageIdList) {
        if (CollectionUtil.isEmpty(messageIdList)){
            return;
        }
        globalMessageMapper.updateMessageState(messageIdList,"complete");
    }
}
