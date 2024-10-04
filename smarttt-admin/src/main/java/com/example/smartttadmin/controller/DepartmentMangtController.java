package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 部门管理
 */
@RestController
@RequestMapping("/sysmangt/department")
public class DepartmentMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479",isReadOnly = true)
    public Result getDepartmentList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getAllObsList(token.getObsid());
    }

    /**
     * 新建部门
     * @param smObs
     * @return
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result createOneDepartment(@RequestBody SmObs smObs,HttpServletRequest request){
        Token token = getTokenFromContext();
        smObs.setObsdeep(token.getObsdeep()+1);
        smObs.setPid(token.getObsid());
        return smObsService.createOneObs(smObs);
    }

    @PostMapping("/update")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result updateOneDepartment(@RequestBody SmObs smObs,HttpServletRequest request){
        return smObsService.updateOneObsByID(smObs);
    }

    @PostMapping("/delete")
    public Result deleteDepartments(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }

    @GetMapping("/departmentRP")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479",isReadOnly = true)
    public Result CollegeRPList(HttpServletRequest request){
        //低于系（当前配置的教师级别）,就回溯到有教师的级别,然后显示级别的所有数据
//        String obsID = smObsService.getSchoolObs();
        Token token = getTokenFromContext();
        String obsID = token.getObsid();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping ("/departmentRP/delete")
    public Result deleteCollageRP(@RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/departmentRP/create")
    public Result createCollageRP(@RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58");
        return stUsersService.createOneRP(stRoleUser);
    }
}
