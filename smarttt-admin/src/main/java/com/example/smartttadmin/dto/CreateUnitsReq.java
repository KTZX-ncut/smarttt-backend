package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.SmObs;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "CreateUnitsReq", description = "教学单位新增请求参数")
public class CreateUnitsReq {
    @ApiModelProperty(value = "当前选中节点 ID；当 type=0 时表示作为父节点新增下级", example = "531500340-parent")
    private String id;
    @ApiModelProperty(value = "同级新增时使用的父节点 ID；当 type=1 时必填", example = "531500340-parent")
    private String pid;
    @ApiModelProperty(value = "当前节点层级深度", example = "2")
    private long obsdeep;
    @ApiModelProperty(value = "新增类型：1 表示同级新增，其他值表示下级新增", example = "1")
    private String type;
    @ApiModelProperty(value = "待新增的教学单位对象")
    private SmObs smObs;
}
