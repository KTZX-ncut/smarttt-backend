package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmAbility {
    /**
     * 能力字典信息列表
     */
    private String id;//能力id
    private String pid;//父节点
    private long orderno;//顺序号
    private long abilitydeep;//深度
    private String levelcode;//层级码
    private String name;//名称
    private String datavalue;//数值
    private String importantlevel;//重要程度
    private String remark;//备注
    private String professionid;//专业id

}