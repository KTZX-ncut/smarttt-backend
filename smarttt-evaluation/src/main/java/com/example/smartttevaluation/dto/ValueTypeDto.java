package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.ValueTag;
import com.example.smartttevaluation.pojo.ValueType;
import lombok.Data;

import java.util.List;

/**
 * @author lunSir
 * @create 2025-04-26 14:51
 */
@Data
public class ValueTypeDto extends ValueType {
    private List<ValueTag> valueTagList;
}
