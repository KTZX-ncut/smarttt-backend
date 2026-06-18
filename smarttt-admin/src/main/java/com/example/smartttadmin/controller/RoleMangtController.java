package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 超级管理员-角色管理
 */
@RestController
@RequestMapping("/sysmangt/rolemangt")
@Api(tags = "10. 角色管理", description = "角色的增删改查接口")
public class RoleMangtController {
    @Autowired
    private StRolesService stRolesService;

    /**
     *超管获取全部角色的信息
     * @return Result
     */
    @GetMapping
    @ApiOperation(value = "获取角色列表", notes = "查询系统中全部角色及其基本信息。")
    public Result getRoleList() {
        return stRolesService.getStRoleMangtList();
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新角色信息", notes = "修改角色名称、首页配置、备注等字段。")
    public Result updateRole(@ApiParam(value = "角色信息", required = true) @RequestBody StRoles stRoles) {

        return stRolesService.updateRoles(stRoles);
    }

    /**
     * 角色删除有误，应该把相关的表里的数据一起删除（修改）
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除角色", notes = "按角色 ID 列表删除角色。当前实现仅删除角色本身，联动删除逻辑需以后补强。")
    public Result deleteRole(@ApiParam(value = "角色 ID 列表", required = true) @RequestBody List<String> ids) {

        return stRolesService.deleteRoles(ids);
    }

    /**
     * 新建角色
     * @param stRoles
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新建角色", notes = "创建新的系统角色及首页配置。")
    public Result createRole(@ApiParam(value = "角色信息", required = true) @RequestBody StRoles stRoles) {

        return stRolesService.createRole(stRoles);
    }
}
