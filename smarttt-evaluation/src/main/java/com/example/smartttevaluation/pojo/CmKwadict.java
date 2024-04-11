package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmKwadict {
    /**
     * KWA信息列表
     */
    private String id;//Kwaid
    private String name;//Kwa名称
    private String keywordid;//关键字id
    private String abilityid;//能力id
    private String keywordname;//关键字名称
    private String abilityname;//能力名称
    private String courseid;//课程
    private String datavalue;//数值
    private String status;//完成状态
}
