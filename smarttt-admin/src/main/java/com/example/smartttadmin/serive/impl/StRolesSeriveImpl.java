package com.example.smartttadmin.serive.impl;

import com.example.smartttadmin.mapper.StRolesMapper;
import com.example.smartttadmin.pojo.LoginResponse;
import com.example.smartttadmin.pojo.Result;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.serive.StRolesSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StRolesSeriveImpl implements StRolesSerive {
    @Autowired
    private StRolesMapper stRolesMapper;
    public Result getStRolesList(StUsers stUsers){
       List<LoginResponse> loginResponses= stRolesMapper.GetRolesByUserID(stUsers.getId());
       if(loginResponses.isEmpty())return Result.error(404,"无可用角色");
       return Result.success(loginResponses);
    }

}
