package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 超级管理员配置权限时，更新指定用户的权限菜单的状态
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMenuReq {
    private String id;//菜单的id
    private String rolecode;//角色代码
    private String status;
}
