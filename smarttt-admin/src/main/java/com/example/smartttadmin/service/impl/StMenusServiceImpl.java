package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.MenusResponse;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.mapper.StMenusMapper;
import com.example.smartttadmin.service.StMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StMenusServiceImpl implements StMenusService {
    @Autowired
    private StMenusMapper stMenusMapper;
    public Result getMenusList(LoginHomeReq loginHomeReq){
        List<MenusResponse> menusResponseList = stMenusMapper.getMenusByRoleid(loginHomeReq.getRoleid());
        return Result.success(menusResponseList);
    }

}
