package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.Result;

public interface StUsersService {
    /**
     * 登录
     * @param loginReq ...
     * @return ...
     */
    Result login(LoginReq loginReq);
}
