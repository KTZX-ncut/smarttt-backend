package com.example.smartttadmin.dto;

import java.awt.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回生成角色信息列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMangtpopupRep{
    private String rolename;
    private String roletype;
    private String rolecode;
    private String homename;
    private String remark;
    private List<SimpleRole> simpleRoleList;
}

