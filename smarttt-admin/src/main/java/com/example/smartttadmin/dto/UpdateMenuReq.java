package com.example.smartttadmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 超级管理员配置权限时，更新指定用户的权限菜单的状态
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UpdateMenuReq", description = "角色权限状态更新参数")
public class UpdateMenuReq {
    @ApiModelProperty(value = "菜单 ID", example = "531500340-menu")
    private String id;//菜单的id
    @ApiModelProperty(value = "角色 ID", example = "516761049-role")
    private String roleid;//角色代码
    @ApiModelProperty(value = "权限状态，通常为 0/1", example = "1")
    private String status;
}
