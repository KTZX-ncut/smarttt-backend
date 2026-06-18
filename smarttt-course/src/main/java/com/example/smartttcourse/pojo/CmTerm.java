package com.example.smartttcourse.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 学期管理信息列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CmTerm", description = "学期信息")
public class CmTerm {
    @ApiModelProperty(value = "学期 ID", example = "term-2025-2")
    private String id;//学期的id
    @ApiModelProperty(value = "学期名称", example = "2025-2026 学年第二学期")
    private String termname;//学期名称
    @ApiModelProperty(value = "开始日期", example = "2026-02-23")
    private LocalDate begindate;//开始时间
    @ApiModelProperty(value = "结束日期", example = "2026-07-05")
    private LocalDate enddate;//结束时间
    @ApiModelProperty(value = "备注", example = "春季学期")
    private String remark;//备注
    @ApiModelProperty(value = "是否当前学期，1 表示是", example = "1")
    private String iscurrentterm;
    private String createtime;

}
