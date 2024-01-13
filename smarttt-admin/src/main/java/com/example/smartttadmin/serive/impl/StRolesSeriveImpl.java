package com.example.smartttadmin.serive.impl;

import com.example.smartttadmin.mapper.StRolesMapper;
import com.example.smartttadmin.pojo.Result;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.serive.StRoleUserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StRolesSeriveImpl implements StRoleUserSerive{
    @Autowired
    private StRolesMapper stRolesMapper;
    public Result get(StUsers stUsers){
       List<StRoles> stRolesList= stRolesMapper.GetRolesByUserID(stUsers.getId());
       if(stRolesList == null)return Result.error("无角色");
       return Result.success(stRolesList);
    }

}
