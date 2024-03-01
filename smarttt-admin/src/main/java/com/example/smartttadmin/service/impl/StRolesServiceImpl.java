package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.LoginResponse;
import com.example.smartttadmin.mapper.StRolesMapper;
import com.example.smartttadmin.dto.SimpleRole;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StRolesServiceImpl implements StRolesService {
    @Autowired
    private StRolesMapper stRolesMapper;
    public Result getSimpleRolesList(StUsers stUsers){
        List<SimpleRole> simpleRoleList = new ArrayList<>();
        if(Objects.equals(stUsers.getCatelog(), "1")){
            SimpleRole simpleRole = new SimpleRole("学生",null,"homes/studenthome");
            simpleRoleList.add(simpleRole);
        }
        else simpleRoleList= stRolesMapper.getRolesByUserID(stUsers.getId());
       LoginResponse loginResponse =new LoginResponse(stUsers.getId().toString(),stUsers.getCatelog(),simpleRoleList.size(),simpleRoleList);
       if(simpleRoleList.isEmpty())return Result.error(404,"无可用角色");
       return Result.success(loginResponse);
    }
    public Result getStRolesList(){
        List<StRoles> stRolesList = stRolesMapper.getRoles();
        return Result.success(stRolesList);
    }

    @Override
    public Result updateRoles() {
        return null;
    }

    @Override
    public Result deleteRoles() {
        return null;
    }
}
