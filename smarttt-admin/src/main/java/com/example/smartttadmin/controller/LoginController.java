package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.UserInforReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    private StUsersService stUsersService;
    @Autowired
    private StRolesService stRoleUserService;
    @GetMapping("")
    public Result test(){
        return Result.success("成功访问");
    }

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
       if(Objects.equals(loginReq.getCatelog(), "1")){
           UserInforReq userInforReq = new UserInforReq((StUsers) result.getData());
           return stUsersService.getUserInfor(userInforReq);
       }
       return stRoleUserService.getSimpleRolesList((StUsers) result.getData());
    }
    @PostMapping("/user")
    public Result getUserInformation(@RequestBody UserInforReq userInforReq){
        return stUsersService.getUserInfor(userInforReq);
    }
}