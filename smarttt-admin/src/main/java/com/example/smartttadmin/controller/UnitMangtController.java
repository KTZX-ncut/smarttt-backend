package com.example.smartttadmin.controller;


import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.CreateUnitsReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;
import static com.example.smartttadmin.Utils.CommonFunctions.generateEnhancedID;

/**
 * 教学单位管理
 */
@Controller
@RestController
@RequestMapping("/sysmangt/units")
@Api(tags = "4. 教学单位", description = "教学单位组织树、节点维护和历史复制接口")
public class UnitMangtController {
    @Autowired
    private SmObsService smObsService;
    @GetMapping
    @ApiOperation(value = "获取教学单位组织树", notes = "查询完整教学单位组织结构树。")
    @AuthRequired(type = "admin",menu = "531500340-155d2725-4be7-4e83-9ac0-88552a02023f",isReadOnly = true)
    public Result getObsList(HttpServletRequest request){
        return smObsService.getObsTree();
    }
    @GetMapping("/student")
    @ApiOperation(value = "获取学生端组织树", notes = "查询学生端使用的教学单位组织结构树。")
    @AuthRequired(type = "admin",menu = "531500340-e7149e74-4856-4440-8d94-99f915731842",isReadOnly = true)
    public Result getStudentObsList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getObsTree();
    }
    @PostMapping("/create")
    @ApiOperation(value = "新建教学单位节点", notes = "支持同级新增或下级新增；type=1 表示同级新增，其他值表示下级新增。")
    @AuthRequired(type = "admin",menu = "531500340-155d2725-4be7-4e83-9ac0-88552a02023f")
    public Result createByTeachingSecretary(@ApiParam(value = "教学单位新增请求体", required = true) @RequestBody CreateUnitsReq createUnitsReq,HttpServletRequest request){
        SmObs smObs = createUnitsReq.getSmObs();
        Token token = getTokenFromContext();
        //同级新增
        if(Objects.equals(createUnitsReq.getType(), "1")){
            if (Objects.equals(createUnitsReq.getObsdeep(),2L)){
                smObs.setPid(createUnitsReq.getPid());
                smObs.setObsdeep(createUnitsReq.getObsdeep());
            }
            else return Result.error("只允许创建专业！！！");
        }
        //下级新增
        else {
            if (Objects.equals(createUnitsReq.getObsdeep(),1L)){
                smObs.setPid(createUnitsReq.getId());
                smObs.setObsdeep(createUnitsReq.getObsdeep()+1);
            }else return Result.error("只允许创建专业！！！");

        }
        smObs.setId(generateEnhancedID("sm_obs"));
        Result result = smObsService.createOneObs(smObs);
        if(result.getCode()!=200)return Result.error(result.getMsg());
        return smObsService.checkSmObs(smObs);
    }

    /**
     * 删除一个组织机构
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除教学单位节点", notes = "按组织节点 ID 列表批量删除教学单位。")
    public Result deleteObsByIDs(@ApiParam(value = "组织节点 ID 列表", required = true) @RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }

    /**
     * 采用拖拽方式，不需要升级，需修改
     * @param id
     * @return
     */
    @GetMapping("/upgrade")
    @ApiOperation(value = "升级教学单位节点", notes = "历史遗留接口，用于节点升级或拖拽调整场景。")
    public Result upgradeOneObs(@ApiParam(value = "组织节点 ID", required = true, example = "531500340-obs") @RequestParam(name = "id")String id){
        return smObsService.upgradeOneObsByID(id);
    }

    /**
     * 教学单位的编辑功能
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新教学单位节点", notes = "修改教学单位名称、备注等信息。")
    public Result updateOneObs(@ApiParam(value = "教学单位节点信息", required = true) @RequestBody SmObs smObs){
        return smObsService.updateOneObsByID(smObs);
    }

    @PostMapping("/copy")
    @ApiOperation(value = "复制历史组织结构", notes = "将指定学期的教学单位组织结构复制到当前学期。")
    @AuthRequired(type = "admin",menu = "531500340-155d2725-4be7-4e83-9ac0-88552a02023f")
    public Result copyHistoryObs(@ApiParam(value = "待复制的历史学期 ID", required = true, example = "2024-2025-1") @RequestParam(name="copyTerm")String copyTerm,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        return smObsService.copyHistoryObs(copyTerm,token.getTermid());
    }
}
