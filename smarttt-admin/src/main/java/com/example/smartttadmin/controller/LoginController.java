package com.example.smartttadmin.controller;

import com.example.smartttadmin.pojo.LoginReq;
import com.example.smartttadmin.pojo.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.serive.StRolesSerive;
import com.example.smartttadmin.serive.StUsersSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private StUsersSerive stUsersSerive;
    @Autowired
    private StRolesSerive stRoleUserSerive;
    @PostMapping("/login")
    public Result login(@RequestBody LoginReq loginReq){
       Result result = stUsersSerive.login(loginReq);
       if(result.getCode() == 400){
           return result;
       }
       return stRoleUserSerive.getStRolesList((StUsers) result.getData());
    }
}