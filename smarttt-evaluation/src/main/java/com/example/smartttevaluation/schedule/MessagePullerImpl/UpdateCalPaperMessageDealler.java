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
 * 根据消息更新配置试卷的信息
 */
@Component
public class UpdateCalPaperMessageDealler implements MessageDealler {

    private final GlobalMessageMapper globalMessageMapper;

    private final List<String> typeList = Arrays.asList("test_update");

    public UpdateCalPaperMessageDealler(GlobalMessageMapper globalMessageMapper) {
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
        for (EduMessage message : messageList) {
            // 1. 转化context为map结构
            String context = message.getContext();
            Map<String,Object> contextMap = JSON.parseObject(context, Map.class);
            // 目前只考虑更新名字
            String testId = message.getIndexId();
            // 2. 更新配置试卷信息
            String paperName = (String) contextMap.get("paperName");
            String testName = (String) contextMap.get("testName");
            // 目前只考虑更新名字
            globalMessageMapper.updateCalPaperName(testId,paperName,testName);
        }
        return true;
    }

    @Override
    public void updateStateToComplete(List<Long> messageIdList) {
        if (CollectionUtil.isEmpty(messageIdList)){
            return;
        }
        // 修改消息状态
        globalMessageMapper.updateMessageState(messageIdList,"complete");
    }
}
