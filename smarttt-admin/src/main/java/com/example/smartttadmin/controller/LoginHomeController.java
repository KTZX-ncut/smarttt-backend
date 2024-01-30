package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.service.StMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/homes")
public class LoginHomeController {
   @Autowired
    private StMenusService stMenusService;

    /**
     * 超级管理员登录到首页的功能，目前也适用于其他角色。返回侧边栏的信息
     * @param loginHomeReq ...
     * @return Result
     */
    @PostMapping("/superadminhome")
    public Result superAdminHome(@RequestBody LoginHomeReq loginHomeReq){

        return stMenusService.getMenusList(loginHomeReq);
    }

}
