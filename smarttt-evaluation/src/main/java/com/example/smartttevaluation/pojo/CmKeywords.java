package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmKeywords {
    /**
     * 关键字信息列表
     */
    private String id;//关键字id
    private String name;//名称
    private String datavalue;//数值
    private String importantlevelid;//重要程度
    private String remark;//备注
    private String courseid;//课程id

}
