package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.mapper.CmCourseMapper;
import com.example.smartttadmin.mapper.SmObsMapper;
import com.example.smartttadmin.mapper.StMenusMapper;
import com.example.smartttadmin.mapper.StRolesMapper;
import com.example.smartttadmin.pojo.*;
import com.example.smartttadmin.service.StRolesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.example.smartttadmin.Utils.CommonFunctions.*;
import static com.example.smartttadmin.Utils.JwtTokenUtils.getToken;

@Service
public class StRolesServiceImpl implements StRolesService {
    @Autowired
    private StRolesMapper stRolesMapper;
    @Autowired
    private StMenusMapper stMenusMapper;
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private CmCourseMapper cmCourseMapper;
    public Result getSimpleRolesList(StUsers stUsers){
        List<SimpleRole> simpleRoleList = stRolesMapper.getRolesByUserID(stUsers.getId());
        for(SimpleRole simpleRole : simpleRoleList){
            dealSimpleRole(simpleRole);
        }
       LoginResponse loginResponse =new LoginResponse(stUsers.getId(),stUsers.getCatelog(),simpleRoleList.size(),simpleRoleList);
       if(simpleRoleList.isEmpty())return Result.error(404,"无可用角色");
       return Result.success(loginResponse);
    }

    private String dealName(String obsName, String roleName) {
        if(obsName ==null || obsName.isEmpty())return roleName;
        List<String> ObsList = Arrays.asList("专业", "学院", "系");
        for(String name : ObsList ){
            int position1 = obsName.lastIndexOf(name);
            int position2 = roleName.indexOf(name);
            if(position1!=-1 && position2 ==0 && position1+name.length() == obsName.length())//保证一个首一个尾
                roleName = roleName.replaceAll(name,"");
        }
        return obsName+roleName;
    }

    public Result getStRoleMangtList(){
        List<StRoles> stRolesList = stRolesMapper.getRoles();
        return Result.success(stRolesList);
    }

    @Override
    public Result updateRoles(StRoles stRoles) {
        stRolesMapper.updateRoles(stRoles);
        return Result.success();
    }


    @Override
    public Result deleteRoles(List<String> ids) {
        for(String id : ids){
            stRolesMapper.deleteRoles(id);
        }
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

    @Override
    public Result getRolePurviewList() {
        List<StRoles> stRolesList = stRolesMapper.getSimpleRoles();
        return Result.success(stRolesList);
    }

    @Override
    public Result switchRole(String id) {
        List<SimpleRole> simpleRoleList = stRolesMapper.getRolesByUserID(id);
        for(SimpleRole simpleRole : simpleRoleList){
            dealSimpleRole(simpleRole);
            simpleRole.setId(id);
        }
        return Result.success(simpleRoleList);
    }

    private void dealSimpleRole(SimpleRole simpleRole) {
        if(simpleRole.getObsdeep() == -1){
            String courseName = cmCourseMapper.getCourseName(simpleRole.getObsid());
            if(courseName == null){
                courseName = cmCourseMapper.getClassroomName(simpleRole.getObsid());
            }
            simpleRole.setRolename(courseName+"-"+simpleRole.getRolename());
        }
        else simpleRole.setRolename(dealName(smObsMapper.getObsName(simpleRole),simpleRole.getRolename()));
    }

    @Override
    public Result getHistoryRole(String id) {
        List<TermRoles> termList = stRolesMapper.getTermList(id);
        for(TermRoles termRoles : termList) {
            List<SimpleRole> simpleRoleList = stRolesMapper.getHistoryRoles(id,termRoles.getId());
            for(SimpleRole simpleRole : simpleRoleList){
                dealSimpleRole(simpleRole);
                simpleRole.setId(id);
            }
            termRoles.setSimpleRoleList(simpleRoleList);
        }
        return Result.success(termList);
    }
}
