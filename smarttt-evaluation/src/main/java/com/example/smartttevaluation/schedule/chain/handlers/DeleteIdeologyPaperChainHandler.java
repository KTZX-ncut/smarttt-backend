package com.example.smartttevaluation.schedule.chain.handlers;

import cn.hutool.core.collection.CollectionUtil;
import com.example.smartttevaluation.mapper.GlobalMessageMapper;
import com.example.smartttevaluation.schedule.chain.AbstractMessageChainHandler;
import com.example.smartttevaluation.schedule.entity.EduMessage;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author lunSir
 * @create 2025-12-07 20:49
 */
@Component
@Order(3)
public class DeleteIdeologyPaperChainHandler extends AbstractMessageChainHandler {
    private final List<String> supportedTypes = Arrays.asList("test_delete", "practice_delete");
    private final GlobalMessageMapper globalMessageMapper;

    public DeleteIdeologyPaperChainHandler(GlobalMessageMapper globalMessageMapper) {
        super("DeleteIdeologyPaperChainHandler");
        this.globalMessageMapper = globalMessageMapper;
    }

    @Override
    protected boolean doHandle(List<EduMessage> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            return true;
        }

        try {
            // 删除思政评价配置试卷信息
            for (EduMessage message : messages) {
                String testId = message.getIndexId();
                // 删除配置试卷信息
                globalMessageMapper.deleteIdeologyPaper(testId);
            }
            // 保持与原有实现一致，返回true表示处理成功
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除配置试卷信息失败", e);
        }
    }

    @Override
    public List<String> getSupportedMessageTypes() {
        return supportedTypes;
    }
}
