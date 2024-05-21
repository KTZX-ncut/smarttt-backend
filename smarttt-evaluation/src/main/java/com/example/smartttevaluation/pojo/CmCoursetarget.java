package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCoursetarget {
    /**
     * 课程目标信息列表
     */
    private String id;//课程目标id
    private String code;//代码
    private String name;//课程目标名称
    private String remark;//备注
    private String unitid;//基本教学目标单元id
    private String courseid;//课程id
}
