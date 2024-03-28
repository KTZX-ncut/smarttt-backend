package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmGetability {

    /**
     * 能力信息列表
     */
    private String id;//课程能力id
    private long orderno;//顺序号
    private long abilitydeep;//深度
    private String levelcode;//层级码
    private String name;//名称
    private String datavalue;//数值
    private String importantlevel;//重要程度
    private String remark;//备注
    private String courseid;//课程id

}
