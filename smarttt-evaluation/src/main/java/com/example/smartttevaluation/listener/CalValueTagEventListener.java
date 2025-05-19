package com.example.smartttevaluation.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.example.smartttevaluation.dto.KwaBindReq;
import com.example.smartttevaluation.dto.KwaValueTagBindReq;
import com.example.smartttevaluation.event.CalValueTagEvent;
import com.example.smartttevaluation.service.ValueTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CalValueTagEventListener {

    @Resource
    private ValueTagService valueTagService;

    @Async
    @EventListener
    public void handleCalculateRequest(CalValueTagEvent event) {
        try {
            log.info("valueTag: 接收到事件：" + event.getKwaBindReq());
            // 获取属性
            KwaBindReq kwaBindReq = event.getKwaBindReq();
            String vTagListJson= event.getvTagListJson();
            List<KwaValueTagBindReq> kwaValueTagBindReqList = kwaBindReq.getKwaValueTagBindReqList();
            // 1. 对于这个kwa下的旧的v，做扣减功能
            List<KwaValueTagBindReq> valueTagList = JSONUtil.parseArray(vTagListJson).toList(KwaValueTagBindReq.class);
            if(Objects.nonNull(valueTagList) && CollectionUtil.isNotEmpty(valueTagList)){
                // 做扣减
                valueTagService.decreaseValueTagCalCount(valueTagList.stream().map(KwaValueTagBindReq::getId).collect(Collectors.toList()));
            }
            // 2. 当前要添加的v，做添加功能
            if(Objects.nonNull(kwaValueTagBindReqList) && CollectionUtil.isNotEmpty(kwaValueTagBindReqList)){
                List<Long> newIds = kwaValueTagBindReqList.stream().map(KwaValueTagBindReq::getId).collect(Collectors.toList());
                valueTagService.increaseValueTagCalCount(newIds);
            }

            log.info("CalValueTagEventListener.handleCalculateRequest: valueTag中的cal_count字段维护完成！");

        }catch (Exception e){
            log.error("计算失败：" + event.getKwaBindReq() + "原因：" + e.getMessage());
        }

    }
}
