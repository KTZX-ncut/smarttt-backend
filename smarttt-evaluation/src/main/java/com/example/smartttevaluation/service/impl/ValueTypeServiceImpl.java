package com.example.smartttevaluation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.dto.ValueTypeDto;
import com.example.smartttevaluation.mapper.ValueTypeMapper;
import com.example.smartttevaluation.pojo.ValueTag;
import com.example.smartttevaluation.pojo.ValueType;
import com.example.smartttevaluation.service.ValueTagService;
import com.example.smartttevaluation.service.ValueTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ValueTypeServiceImpl implements ValueTypeService {

    @Resource
    private ValueTypeMapper valueTypeMapper;

    @Resource
    private ValueTagService valueTagService;

    @Override
    public List<ValueTypeDto> getAllValueType() {
        List<ValueTypeDto> allValueType = valueTypeMapper.getAllValueType();
        List<ValueTag> allValue = valueTagService.getAllValue();
        Map<Long, List<ValueTag>> listValueTagMap = allValue.stream().collect(Collectors.groupingBy(ValueTag::getTypeId));
        for (ValueTypeDto valueTypeDto : allValueType) {
            List<ValueTag> valueTags = listValueTagMap.get(valueTypeDto.getId());
            valueTypeDto.setValueTagList(valueTags);
        }
        return allValueType;
    }

    @Override
    public Boolean create(ValueType valueType) {
        valueType.setCreateTime(LocalDateTime.now());
        long id = IdWorker.getId();
        valueType.setId(id);
        if(StrUtil.isBlank(valueType.getRemark())){
            valueType.setRemark("无");
        }
        return valueTypeMapper.create(valueType);
    }

    @Override
    public Integer getValueTypeByName(String name) {
        return valueTypeMapper.getValueTypeByName(name);
    }

    @Override
    public Boolean update(ValueType valueType) {
        valueType.setCreateTime(LocalDateTime.now());
        return valueTypeMapper.update(valueType);
    }

    @Override
    @Transactional
    public Boolean delete(List<Long> idList) {
        valueTypeMapper.delete(idList);
        valueTagService.deleteValueTagByTypeId(idList);
        return true;
    }

    @Override
    public Integer countById(Long id) {
        return valueTypeMapper.countById(id);
    }
}
