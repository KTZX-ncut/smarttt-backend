package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.LoginResponse;
import com.example.smartttadmin.mapper.StMenusMapper;
import com.example.smartttadmin.mapper.StRolesMapper;
import com.example.smartttadmin.dto.SimpleRole;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StMenus;
import com.example.smartttadmin.pojo.StRoleMenu;
import com.example.smartttadmin.pojo.StRoles;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.StRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.smartttadmin.pojo.CommonFunctions.generateEnhancedID;

@Service
public class StRolesServiceImpl implements StRolesService {
    @Autowired
    private StRolesMapper stRolesMapper;
    @Autowired
    private StMenusMapper stMenusMapper;
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
    public Result deleteRoles(String id) {
        stRolesMapper.deleteRoles(id);
        return Result.success();
    }

    public Result createRole(StRoles stRoles) {
        stRoles.setId(generateEnhancedID("st_roles"));
        LocalDateTime currentTime = LocalDateTime.now();
        stRoles.setCreatetime(currentTime.toString());
        stRolesMapper.createRoleTable(stRoles);
        List<StMenus> stMenusList = stMenusMapper.getAllMenus();
        for(StMenus stMenus : stMenusList){
            LocalDateTime menuCurrentTime = LocalDateTime.now();
            StRoleMenu stRoleMenu = new StRoleMenu(generateEnhancedID("st_rolemenu"),stRoles.getId(),stMenus.getId(),"3",menuCurrentTime.toString(),"");
            stRolesMapper.createRoleMenus(stRoleMenu);
        }
        return Result.success();
    }
}
