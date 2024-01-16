package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.LoginResponse;
import com.example.smartttadmin.mapper.StRolesMapper;
import com.example.smartttadmin.dto.SimpleRole;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StRolesServiceImpl implements StRolesService {
    @Autowired
    private StRolesMapper stRolesMapper;
    public Result getStRolesList(StUsers stUsers){
       List<SimpleRole> simpleRoleList= stRolesMapper.getRolesByUserID(stUsers.getId());
       LoginResponse loginResponse =new LoginResponse(stUsers.getId().toString(),stUsers.getCatelog(),simpleRoleList.size(),simpleRoleList);
       if(Objects.equals(stUsers.getCatelog(), "1")){
           return Result.success(loginResponse);
       }
       if(simpleRoleList.isEmpty())return Result.error(404,"无可用角色");
       return Result.success(loginResponse);
    }

}
