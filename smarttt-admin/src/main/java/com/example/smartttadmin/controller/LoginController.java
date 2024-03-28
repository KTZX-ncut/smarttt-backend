package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.TeaInforReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    private StUsersService stUsersService;
    @Autowired
    private StRolesService stRoleUserService;

    /**
     * 登录功能，返回角色列表
     * @param loginReq ...
     * @return Result
     */
    @PostMapping("")
    public Result login(@RequestBody LoginReq loginReq){
       Result result = stUsersService.login(loginReq);
       if(result.getCode() == 400){
           return result;
       }
       return stRoleUserService.getSimpleRolesList((StUsers) result.getData());
    }
    @PostMapping("/user")
    public Result getUserInformation(@RequestBody TeaInforReq teaInforReq){
        return stUsersService.getUserInfor(teaInforReq);
    }
}