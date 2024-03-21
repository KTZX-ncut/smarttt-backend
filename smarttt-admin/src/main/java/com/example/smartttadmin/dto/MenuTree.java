package com.example.smartttadmin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 指定角色的权限菜单的层级列表，用于生成嵌套格式的json，返回的参数设为not_null
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuTree {
    private String id;
    private String pid;
    private Integer orderno;
    private String name;
    private String status;//该角色在这个权限菜单的状态
    private List<MenuTree> children;//子菜单

    public MenuTree(String id,String name, String status, List<MenuTree> menuTrees) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.children = menuTrees;
    }
}
