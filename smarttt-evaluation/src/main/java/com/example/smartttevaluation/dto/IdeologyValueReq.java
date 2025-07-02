package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.IdeologyValue;
import lombok.Data;

@Data
public class IdeologyValueReq extends IdeologyValue {
    /**
     * 对于字符串类型，是否支持模糊查询，默认不支持
     */
    private Boolean fuzzyQuery;
}
