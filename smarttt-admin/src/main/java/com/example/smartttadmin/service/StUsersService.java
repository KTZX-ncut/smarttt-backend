package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.Result;

import java.util.List;

public interface StUsersService {
    /**
     * 登录
     * @param loginReq ...
     * @return ...
     */
    Result login(LoginReq loginReq);

    /**
     * 需修改为判断是否成立
     * @param ids
     * @return
     */
    Result deleteUsersByIDs(List<String> ids);
}
