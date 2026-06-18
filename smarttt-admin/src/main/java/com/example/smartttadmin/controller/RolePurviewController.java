package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.UpdateMenuReq;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 超级管理员-角色授权
 */
@RestController
@RequestMapping("/sysmangt/rolepurview")
@Api(tags = "11. 角色授权", description = "角色菜单权限配置接口")
public class RolePurviewController {
    @Autowired
    private StRolesService stRolesService;
    @Autowired
    private StMenusService stMenusService;

    /**
     *超管获取全部角色的信息
     * @return Result
     */
    @GetMapping
    @ApiOperation(value = "获取角色授权列表", notes = "查询可配置权限的角色列表。")
    public Result getRoleList() {

       return stRolesService.getRolePurviewList();
   }

    /**
     * 获取相应角色（用rolecode）的层级列表
     * @param id rolecode
     * @return Result
     */
   @GetMapping("/menus")
    @ApiOperation(value = "获取角色菜单树", notes = "按角色 ID 或角色编码查询当前角色可配置的菜单树。")
    public Result getMenuList(@ApiParam(value = "角色 ID 或角色编码", required = true, example = "admin") @RequestParam(name = "id")String id) {
        return stMenusService.getMenuTree(id);
   }

    /**
     * 更新相应角色的权限列表的状态
     * @param updateMenuReq ...
     * @return Result
     */
   @PostMapping
    @ApiOperation(value = "更新角色菜单权限状态", notes = "更新某个角色对指定菜单的启用状态。")
    public Result UpdateMenuList(@ApiParam(value = "权限更新请求体", required = true) @RequestBody UpdateMenuReq updateMenuReq) {
        return stMenusService.updateMenuStatus(updateMenuReq);
   }
}
