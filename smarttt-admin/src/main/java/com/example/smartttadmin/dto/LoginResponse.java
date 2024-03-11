package com.example.smartttadmin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户登录成功之后返回角色列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String userid;
    private String username;
    private String catelog;
    private Integer rolescount;//角色列表的计数
    private List<SimpleRole> simpleRoleList;//角色列表
}
