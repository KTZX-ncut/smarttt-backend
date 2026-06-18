package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.UserInforReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import com.example.smartttadmin.service.StUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/login")
@RestController
@Api(tags = "1. 登录认证", description = "登录、用户信息获取和登录链路测试接口")
public class LoginController {
    @Autowired
    private StUsersService stUsersService;
    @Autowired
    private StRolesService stRoleUserService;

    @GetMapping("")
    @ApiOperation(value = "登录模块连通性测试", notes = "用于开发阶段验证登录模块相关事务调用是否正常。")
    public Result test(){
        return stUsersService.testTran();
    }

    /**
     * 登录功能，返回角色列表
     * @param loginReq ...
     * @return Result
     */
    @PostMapping("")
    @ApiOperation(value = "用户登录", notes = "校验账号密码。学生登录时直接返回学生信息；教师/管理员登录时返回可选角色列表。")
    public Result login(@ApiParam(value = "登录请求体", required = true) @RequestBody LoginReq loginReq){
       // 验证登录名与密码是否正确
       Result result = stUsersService.login(loginReq);
       if(result.getCode() == 400){
           return result;
       }
       if(Objects.equals(loginReq.getCatelog(), "1")) {
           return stUsersService.getStudentInfor((StUsers) result.getData());
       }
       return stRoleUserService.getSimpleRolesList((StUsers) result.getData());
    }
    @PostMapping("/user")
    @ApiOperation(value = "获取教师端用户信息", notes = "用户选择角色后，查询该角色下的教师信息与 token。")
    public Result getUserInformation(@ApiParam(value = "用户信息请求体", required = true) @RequestBody UserInforReq userInforReq){
        return stUsersService.getTeaInfor(userInforReq);
    }
}
