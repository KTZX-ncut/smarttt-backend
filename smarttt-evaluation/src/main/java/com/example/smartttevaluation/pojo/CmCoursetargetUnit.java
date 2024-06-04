package com.example.smartttevaluation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCoursetargetUnit {
    /**
     * 课程目标Unit信息列表
     */
    private String targetid;//课程目标id
    private String unitname;//基本教学目标单元名称
    private String unitid;//基本教学目标单元id
}
