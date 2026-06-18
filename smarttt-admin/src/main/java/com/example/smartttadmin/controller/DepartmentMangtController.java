package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "6. 部门管理", description = "部门节点及部门负责人维护接口")
public class DepartmentMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @GetMapping("")
    @ApiOperation(value = "获取部门列表", notes = "根据当前 token 的组织节点查询其下属部门。")
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
    @ApiOperation(value = "新建部门", notes = "在当前组织节点下新增部门，并自动继承层级深度。")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result createOneDepartment(@ApiParam(value = "部门节点信息", required = true) @RequestBody SmObs smObs,HttpServletRequest request){
        Token token = getTokenFromContext();
        smObs.setObsdeep(token.getObsdeep()+1);
        smObs.setPid(token.getObsid());
        return smObsService.createOneObs(smObs);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新部门信息", notes = "修改部门名称、备注等信息。")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result updateOneDepartment(@ApiParam(value = "部门节点信息", required = true) @RequestBody SmObs smObs,HttpServletRequest request){
        return smObsService.updateOneObsByID(smObs);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除部门", notes = "按部门节点 ID 列表批量删除部门。")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result deleteDepartments(@ApiParam(value = "部门 ID 列表", required = true) @RequestBody List<String> ids,HttpServletRequest request){
        return smObsService.deleteObssByIDS(ids);
    }

    @GetMapping("/departmentRP")
    @ApiOperation(value = "获取部门负责人候选列表", notes = "查询当前部门可绑定的负责人候选人员列表。")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479",isReadOnly = true)
    public Result CollegeRPList(HttpServletRequest request){
        //低于系（当前配置的教师级别）,就回溯到有教师的级别,然后显示级别的所有数据
//        String obsID = smObsService.getSchoolObs();
        Token token = getTokenFromContext();
        String obsID = token.getObsid();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping ("/departmentRP/delete")
    @ApiOperation(value = "删除部门负责人", notes = "移除部门负责人的角色绑定关系。")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result deleteCollageRP(@ApiParam(value = "负责人角色绑定信息", required = true) @RequestBody StRoleUser stRoleUser,HttpServletRequest request){
        stRoleUser.setRoleid("516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/departmentRP/create")
    @ApiOperation(value = "新增部门负责人", notes = "为指定用户绑定部门负责人角色。")
    @AuthRequired(type = "admin",menu = "531500340-f47ac10b-58cc-4372-a567-0e02b2c3d479")
    public Result createCollageRP(@ApiParam(value = "负责人角色绑定信息", required = true) @RequestBody StRoleUser stRoleUser,HttpServletRequest request){
        Token token = getTokenFromContext();
        stRoleUser.setRoleid("516761049-bac1cf5a-5360-4caa-b062-3e2800bdfd58");
        stRoleUser.setTermid(token.getTermid());
        return stUsersService.createOneRP(stRoleUser);
    }
}
