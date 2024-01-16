package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.Result;

public interface StUsersService {
    public Result login(LoginReq loginReq);
}
