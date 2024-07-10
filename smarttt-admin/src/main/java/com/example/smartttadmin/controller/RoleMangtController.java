package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 超级管理员-角色管理
 */
@RestController
@RequestMapping("/sysmangt/rolemangt")
public class RoleMangtController {
    @Autowired
    private StRolesService stRolesService;

    /**
     *超管获取全部角色的信息
     * @return Result
     */
    @GetMapping
    public Result getRoleList() {
        return stRolesService.getStRoleMangtList();
    }

    @PostMapping("/update")
    public Result updateRole(@RequestBody StRoles stRoles) {

        return stRolesService.updateRoles(stRoles);
    }

    /**
     * 角色删除有误，应该把相关的表里的数据一起删除（修改）
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Result deleteRole(@RequestBody List<String> ids) {

        return stRolesService.deleteRoles(ids);
    }

    /**
     * 新建角色
     * @param stRoles
     * @return
     */
    @PostMapping
    public Result createRole(@RequestBody StRoles stRoles) {

        return stRolesService.createRole(stRoles);
    }
}

