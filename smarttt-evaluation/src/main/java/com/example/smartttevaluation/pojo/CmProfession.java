package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmProfession {

    /**
     * 专业信息列表——参照v4版本
     */

    private String id;//专业id
    private String proname;//专业名称
    private String procode;//专业代码
    private String produtymanid;//专业负责人id
    private String produtyman;//专业负责人
    private String remark;//备注
    private String reachpercent;//课程目标达成阈值

}
