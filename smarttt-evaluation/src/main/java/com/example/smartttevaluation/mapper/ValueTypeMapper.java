package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.dto.ValueTypeDto;
import com.example.smartttevaluation.pojo.ValueType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ValueTypeMapper {


    List<ValueTypeDto> getAllValueType();

    Boolean create(ValueType valueType);

    Integer getValueTypeByName(@Param("name") String name);

    Boolean update(ValueType valueType);

    Boolean delete(@Param("idList") List<Long> idList);

    Integer countById(@Param("id") Long id);
}
