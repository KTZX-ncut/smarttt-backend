package com.example.smartttadmin.controller;


import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.CreateUnitsReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class UnitMangtController {
    @Autowired
    private SmObsService smObsService;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-155d2725-4be7-4e83-9ac0-88552a02023f",isReadOnly = true)
    Result getObsList(HttpServletRequest request){
        return smObsService.getObsTree();
    }
    @GetMapping("/student")
    @AuthRequired(type = "admin",menu = "531500340-e7149e74-4856-4440-8d94-99f915731842",isReadOnly = true)
    Result getStudentObsList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return smObsService.getObsTree();
    }
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-155d2725-4be7-4e83-9ac0-88552a02023f")
    Result createByTeachingSecretary(@RequestBody CreateUnitsReq createUnitsReq,HttpServletRequest request){
        SmObs smObs = createUnitsReq.getSmObs();
        Token token = getTokenFromContext();
        //同级新增
        if(Objects.equals(createUnitsReq.getType(), "1")){
            smObs.setPid(createUnitsReq.getPid());
            smObs.setObsdeep(createUnitsReq.getObsdeep());
        }
        //下级新增
        else {
            smObs.setPid(createUnitsReq.getId());
            smObs.setObsdeep(createUnitsReq.getObsdeep()+1);
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
    public Result deleteObsByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }

    /**
     * 采用拖拽方式，不需要升级，需修改
     * @param id
     * @return
     */
    @GetMapping("/upgrade")
    public Result upgradeOneObs(@RequestParam(name = "id")String id){
        return smObsService.upgradeOneObsByID(id);
    }

    /**
     * 教学单位的编辑功能
     * @return
     */
    @PostMapping("/update")
    public Result updateOneObs(@RequestBody SmObs smObs){
        return smObsService.updateOneObsByID(smObs);
    }

    @PostMapping("/copy")
    @AuthRequired(type = "admin",menu = "531500340-155d2725-4be7-4e83-9ac0-88552a02023f")
    public Result copyHistoryObs(@RequestParam(name="copyTerm")String copyTerm,HttpServletRequest request) throws JsonProcessingException {
        Token token = getTokenFromContext();
        return smObsService.copyHistoryObs(copyTerm,token.getTermid());
    }
}

