package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.UpdateMenuReq;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 超级管理员-角色授权
 */
@RestController
@RequestMapping("/sysmangt/rolepurview")
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
    public Result getRoleList() {

       return stRolesService.getRolePurviewList();
   }

    /**
     * 获取相应角色（用rolecode）的层级列表
     * @param id rolecode
     * @return Result
     */
   @GetMapping("/menus")
    public Result getMenuList(@RequestParam(name = "id")String id) {
        return stMenusService.getMenuTree(id);
   }

    /**
     * 更新相应角色的权限列表的状态
     * @param updateMenuReq ...
     * @return Result
     */
   @PostMapping
    public Result UpdateMenuList(@RequestBody UpdateMenuReq updateMenuReq) {
        return stMenusService.updateMenuStatus(updateMenuReq);
   }
}
