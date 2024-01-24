package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/login")
    public Result login(@RequestBody LoginReq loginReq){
       Result result = stUsersService.login(loginReq);
       if(result.getCode() == 400){
           return result;
       }
       return stRoleUserService.getSimpleRolesList((StUsers) result.getData());
    }
}