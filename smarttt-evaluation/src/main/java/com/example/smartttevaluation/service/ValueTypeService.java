package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.ValueTypeDto;
import com.example.smartttevaluation.pojo.ValueType;

import java.util.List;

/**
 * @author lunSir
 * @create 2025-04-26 14:48
 */
public interface ValueTypeService {
    List<ValueTypeDto> getAllValueType();

    Boolean create(ValueType valueType);

    Integer getValueTypeByName(String name);

    Boolean update(ValueType valueType);

    Boolean delete(List<Long> idList);

    Integer countById(Long id);
}
