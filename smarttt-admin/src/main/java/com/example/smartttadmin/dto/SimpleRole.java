package com.example.smartttadmin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简化版的角色列表，用于登录成功之后返回的角色列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleRole {
    private String id;
    private String roleid;
    private String rolename;
    private String obsid;
    private long obsdeep;

    public SimpleRole(String roleid,String rolename) {
        this.rolename = rolename;
        this.roleid = roleid;
    }
}
