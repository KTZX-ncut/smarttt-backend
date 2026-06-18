package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.mapper.StLevelMapper;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StLevelService;
import com.example.smartttadmin.service.StUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;


@RestController
@RequestMapping("/sysmangt/collegemangt")
@Api(tags = "5. 学院管理", description = "学院节点及学院负责人维护接口")
public class CollageMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @Autowired
    private StLevelService stLevelService;
    @GetMapping
    @ApiOperation(value = "获取学院列表", notes = "查询系统中的学院节点列表。")
    @AuthRequired(type = "admin",menu = "531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3",isReadOnly = true)
    public Result getCollegeList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getAllCollageList();
    }

    /**
     * 新建一个学院，先调用新建机构方法
     * @param smObs 学院
     * @return ...
     * 后续需要把每个字段都补全（修改）
     */
    @PostMapping
    @ApiOperation(value = "新建学院", notes = "创建学院组织节点，自动补齐学院层级和父节点信息。")
    @AuthRequired(type = "admin",menu = "531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3")
    public Result createNewCollege(@ApiParam(value = "学院节点信息", required = true) @RequestBody SmObs smObs,HttpServletRequest request){
        long obsDeep = stLevelService.getObsLevel("102");
        Token token = getTokenFromContext();
        smObs.setObsdeep(obsDeep);
        smObs.setPid(token.getObsid());
        return smObsService.createOneObs(smObs);
    }

    /**
     * 删除学院
     * @param ids id列表
     * @return Result
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除学院", notes = "按学院节点 ID 列表批量删除学院。")
    public Result deleteCollegeByIDs(@ApiParam(value = "学院 ID 列表", required = true) @RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新学院信息", notes = "修改学院名称、备注等组织节点信息。")
    public Result updateCollege(@ApiParam(value = "学院节点信息", required = true) @RequestBody SmObs smObs) {
        return smObsService.updateOneObsByID(smObs);
    }

    /**
     * 获取可选的教师角色列表
     * @param request
     * @return
     */
    @GetMapping("/collageRP")
    @ApiOperation(value = "获取学院负责人候选列表", notes = "查询学院负责人可选人员列表。")
    @AuthRequired(type = "admin",menu = "531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3",isReadOnly = true)
    public Result CollegeRPList(HttpServletRequest request){
        //低于系（当前配置的教师级别）,就回溯到有教师的级别,然后显示级别的所有数据
        Token token = getTokenFromContext();
        String obsID = smObsService.getSchoolObs();
        return smObsService.getObsRPList(obsID);
    }

    /**
     * 删除负责人,角色写死，需修改
     * @param stRoleUser ...
     * @return ...
     */
    @PostMapping ("/collageRP/delete")
    @ApiOperation(value = "删除学院负责人", notes = "移除学院负责人的角色绑定关系。")
    public Result deleteCollageRP(@ApiParam(value = "负责人角色绑定信息", required = true) @RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/collageRP/create")
    @ApiOperation(value = "新增学院负责人", notes = "为指定用户绑定学院负责人角色。")
    @AuthRequired(type = "admin",menu = "531500340-69ed23be-6d75-4e9b-8b27-d287ed22fce3")
    public Result createCollageRP(@ApiParam(value = "负责人角色绑定信息", required = true) @RequestBody StRoleUser stRoleUser,HttpServletRequest request){
        stRoleUser.setRoleid("516761049-de9ae949-6bfb-4314-be59-8b1f3c2626e4");
        Token token = getTokenFromContext();
        stRoleUser.setTermid(token.getTermid());
        return stUsersService.createOneRP(stRoleUser);
    }
}
