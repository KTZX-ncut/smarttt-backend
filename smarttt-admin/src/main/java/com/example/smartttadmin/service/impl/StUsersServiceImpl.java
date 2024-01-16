package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StUsersServiceImpl implements StUsersService {
    @Autowired
    private StUsersMapper stUsersMapper;
    public Result login(LoginReq loginReq) {
        StUsers stUsers = stUsersMapper.getStUsersByUsernameAndPwdAndCatelog(loginReq);
        if(stUsers == null){
            return Result.error(400,"用户名或密码错误");
        }
        return Result.success(stUsers);
    }
}
