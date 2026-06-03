package com.example.smartttadmin.controller;

import cn.hutool.core.util.StrUtil;
import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.CmProfession;
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
import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;

@RestController
@RequestMapping("/sysmangt/professionmangt")
@Api(tags = "7. 专业管理", description = "专业节点及专业负责人维护接口")
public class ProfessionMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @GetMapping("")
    @ApiOperation(value = "获取专业列表", notes = "根据当前 token 的组织节点查询其下属专业列表。")
    @AuthRequired(type = "admin",menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90",isReadOnly = true)
    public Result getProfessionList(HttpServletRequest request){
        Token token = getTokenFromContext();
        // 获取学院的obsid
        String pid = smObsService.getPisById(token.getObsid());
        if (StrUtil.isEmpty(pid)) return Result.error("obsid不合法");
        return smObsService.getAllProfessionList(pid);
    }

    /**
     * @param cmProfession
     * @return
     */
    @PostMapping("/create")
    @ApiOperation(value = "新建专业", notes = "先创建专业对应的组织节点，再写入专业业务信息。")
    @AuthRequired(type = "admin", menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90")
    public Result createOneProfession(@ApiParam(value = "专业信息", required = true) @RequestBody CmProfession cmProfession, HttpServletRequest request){
        String ID = generateEnhancedID("sm_obs");
        Token token = getTokenFromContext();
        SmObs smObs = new SmObs(ID, token.getObsid(), token.getObsdeep()+1,cmProfession.getProname(),cmProfession.getRemark());
        Result result = smObsService.createOneObs(smObs);
        if(result.getCode() != 200)return result;
        cmProfession.setObsid(ID);
        return smObsService.createOneProfession(cmProfession);
    }
    @PostMapping("/delete")
    @ApiOperation(value = "删除专业", notes = "按专业或对应组织节点 ID 列表批量删除专业。")
    public Result deleteProfessionByIDs(@ApiParam(value = "专业 ID 列表", required = true) @RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新专业信息", notes = "修改专业名称、代码、达成度和备注等信息。")
    public Result updateProfession(@ApiParam(value = "专业信息", required = true) @RequestBody CmProfession cmProfession){
        return smObsService.updateOneProfession(cmProfession);
    }

    @GetMapping("/professionRP")
    @ApiOperation(value = "获取专业负责人候选列表", notes = "查询专业负责人可绑定的候选人员列表。")
    @AuthRequired(type = "admin",menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90",isReadOnly = true)
    public Result CollegeRPList( HttpServletRequest request){
        //低于系（当前配置的教师级别）,就回溯到有教师的级别,然后显示级别的所有数据
        String obsID = smObsService.getSchoolObs();
        Token token = getTokenFromContext();
        return smObsService.getObsRPList(obsID);
    }
    @PostMapping ("/professionRP/delete")
    @ApiOperation(value = "删除专业负责人", notes = "移除专业负责人的角色绑定关系。")
    public Result deleteCollageRP(@ApiParam(value = "负责人角色绑定信息", required = true) @RequestBody StRoleUser stRoleUser){
        stRoleUser.setRoleid("516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60");
        return stUsersService.deleteRP(stRoleUser);
    }
    @PostMapping("/professionRP/create")
    @ApiOperation(value = "新增专业负责人", notes = "为指定用户绑定专业负责人角色。")
    //@AuthRequired(type = "admin",menu = "531500340-910116aa-e8f8-11ee-934c-fa163efa1f90")
    public Result createCollageRP(@ApiParam(value = "负责人角色绑定信息", required = true) @RequestBody StRoleUser stRoleUser,HttpServletRequest request){
        Token token = getTokenFromContext();
        stRoleUser.setRoleid("516761049-916b5c26-ea1d-4f50-8e57-fe7c2e3aaf60");
        //stRoleUser.setTermid(token.getTermid());
        return stUsersService.createOneRP(stRoleUser);
    }
}
