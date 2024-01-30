package com.example.smartttadmin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.UpdateMenuReq;
import com.example.smartttadmin.service.StMenusService;
import com.example.smartttadmin.service.StRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 超级管理员-角色管理
 */
@RestController
@RequestMapping("/sysmangt/rolemangt")
public class RoleMangtController {
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

        return stRolesService.getStRolesList();
    }
}

