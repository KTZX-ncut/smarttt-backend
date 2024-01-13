package com.example.smartttadmin.serive.impl;

import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.pojo.LoginReq;
import com.example.smartttadmin.pojo.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.serive.StUsersSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StUsersSeriveImpl implements StUsersSerive {
    @Autowired
    private StUsersMapper stUsersMapper;
    public Result login(LoginReq loginReq) {
        StUsers stUsers = stUsersMapper.GetStUsersByUsernameAndPwdAndCatelog(loginReq);
        if(stUsers == null){
            return Result.error("用户名或密码错误");
        }
        System.out.println(stUsers);
        return Result.success(stUsers);
    }
}
