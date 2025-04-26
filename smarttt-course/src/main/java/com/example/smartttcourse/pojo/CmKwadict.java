package com.example.smartttcourse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private String importantlevelid;// 重要程度
    private String status;//完成状态
    private String v;// 标签
    private LocalDateTime createTime;
}
