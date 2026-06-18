package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.CmClass;
import com.example.smartttadmin.pojo.SmObs;
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
import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;

@RestController
@RequestMapping("/sysmangt/classmangt")
@Api(tags = "8. 班级管理", description = "班级节点维护和班级学生查询接口")
public class ClassMangtController {
    @Autowired
    SmObsService smObsService;
    @Autowired
    StUsersService stUsersService;
    @Autowired
    StLevelService stLevelService;

    /**
     *
     * @param request
     * @return
     */
    @GetMapping("")
    @ApiOperation(value = "获取班级列表", notes = "根据当前 token 的组织节点，查询其下属班级列表。")
    @AuthRequired(type = "admin",menu = "531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90",isReadOnly = true)
    public Result getClassList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getClassList(token.getObsid());
    }

    /**
     * 传入的id为专业id
     * @param cmClass
     * @return
     */
    @PostMapping("/create")
    @ApiOperation(value = "新建班级", notes = "先创建班级对应的组织节点，再写入班级业务信息。请求体中的 id 需传专业 ID。")
    //@AuthRequired(type = "admin",menu = "531500340-63929fcc-e8f9-11ee-934c-fa163efa1f90")
    public Result createOneClass(@ApiParam(value = "班级信息", required = true) @RequestBody CmClass cmClass,HttpServletRequest request){
        Token token = getTokenFromContext();
        String ID = generateEnhancedID("sm_obs");
        long deep = stLevelService.getObsLevel("105");
        SmObs smObs = new SmObs(ID, cmClass.getId(), deep,cmClass.getClassname(),cmClass.getRemark());
        Result result = smObsService.createOneObs(smObs);
        if(result.getCode()!=200)return result;
        cmClass.setObsid(ID);
        return smObsService.createOneClass(cmClass);
    }
    @PostMapping("/delete")
    @ApiOperation(value = "删除班级", notes = "按班级或对应组织节点 ID 列表批量删除班级。")
    public Result deleteClassesByIDs(@ApiParam(value = "班级 ID 列表", required = true) @RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
    @PostMapping("/update")
    @ApiOperation(value = "更新班级信息", notes = "修改班级名称、年级、备注等信息。")
    public Result updateClass(@ApiParam(value = "班级信息", required = true) @RequestBody CmClass cmClass){
        return smObsService.updateClass(cmClass);
    }

    @GetMapping("/student")
    @ApiOperation(value = "查询班级学生列表", notes = "根据班级 ID 查询该班级下的学生列表。")
    public Result getStudentList(@ApiParam(value = "班级 ID", required = true, example = "class-001") @RequestParam(name = "id")String id){//班级的Id
        return stUsersService.getStudentByClassID(id);
    }

}
