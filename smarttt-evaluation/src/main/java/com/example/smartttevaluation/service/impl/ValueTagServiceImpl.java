package com.example.smartttevaluation.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.dto.KwaBindReq;
import com.example.smartttevaluation.dto.KwaValueTagBindReq;
import com.example.smartttevaluation.mapper.ValueTagMapper;
import com.example.smartttevaluation.pojo.ValueTag;
import com.example.smartttevaluation.service.ValueTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author lunSir
 * @create 2025-04-26 14:06
 */
@Service
public class ValueTagServiceImpl implements ValueTagService {

    @Resource
    private ValueTagMapper valueTagMapper;
    @Override
    public List<ValueTag> getAllValue() {
        return valueTagMapper.getAllValue();
    }

    @Override
    public List<ValueTag> getValueByTypeId(Long typeId) {
        return valueTagMapper.getValueByTypeId(typeId);
    }

    @Override
    public Boolean deleteValueTagByTypeId(List<Long> typeIdList) {
        // 根据typeId查询所有的valueTag
        List<ValueTag> valueTagList = valueTagMapper.getValueTagByTypeIdList(typeIdList);
        if(CollectionUtil.isEmpty(valueTagList)) return true;
        for (ValueTag valueTag : valueTagList){
            if(valueTag.getCalCount()>0){
                throw new RuntimeException("该valueTag:"+valueTag.getName()+"被使用，不能删除");
            }
        }
        return valueTagMapper.deleteValueTagByTypeId(typeIdList);
    }

    @Override
    public Integer getValueByName(String name) {
        return valueTagMapper.getValueByName(name);
    }

    @Override
    public Boolean createValue(ValueTag valueTag) {
        valueTag.setCreateTime(LocalDateTime.now());
        if(StrUtil.isBlank(valueTag.getRemark())){
            valueTag.setRemark("无");
        }
        long id = IdWorker.getId();
        valueTag.setId(id);
        return valueTagMapper.createValue(valueTag);
    }

    @Override
    public Boolean update(ValueTag valueTag) {
        List<ValueTag> valueTagList = valueTagMapper.getValueTagByIdList(Arrays.asList(valueTag.getId()));
        if(CollectionUtil.isNotEmpty(valueTagList)){
            ValueTag tag = valueTagList.get(0);
            if(tag.getCalCount()>0){
                throw new RuntimeException("该valueTag:"+tag.getName()+"被使用，不能修改");
            }
        }
        valueTag.setCreateTime(LocalDateTime.now());
        return valueTagMapper.update(valueTag);
    }

    @Override
    public Boolean delete(List<Long> idList) {
        // 根据id获取整个记录信息
        List<ValueTag> valueTagList = valueTagMapper.getValueTagByIdList(idList);
        if(CollectionUtil.isEmpty(valueTagList)) return true;

        for (ValueTag valueTag : valueTagList){
            if(valueTag.getCalCount()>0){
                throw new RuntimeException("该valueTag:"+valueTag.getName()+"被使用，不能删除");
            }
        }
        return valueTagMapper.delete(idList);
    }

    @Override
    public List<KwaValueTagBindReq> validTagIdAndName(List<Long> tagIdList) {
        return valueTagMapper.validTagIdAndName(tagIdList);
    }

    @Override
    public Integer countKwaByKwaId(String kwaId) {
        return valueTagMapper.countKwaByKwaId(kwaId);
    }

    @Override
    public Boolean bindKwaAndValue(KwaBindReq kwaBindReq) {
        JSON parse = JSONUtil.parse(kwaBindReq.getKwaValueTagBindReqList());
        return valueTagMapper.bindKwaAndValue(kwaBindReq.getKwaId(), parse.toString());
    }

    @Override
    public String getValueTagListJson(String kwaId) {
        return valueTagMapper.getValueTagListJson(kwaId);
    }

    @Override
    public void decreaseValueTagCalCount(List<Long> ids) {
        valueTagMapper.decreaseValueTagCalCount(ids);
    }

    @Override
    public void increaseValueTagCalCount(List<Long> newIds) {
        valueTagMapper.increaseValueTagCalCount(newIds);
    }

}
