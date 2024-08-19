package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.CreateAbilityReq;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmAbility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.service.CmAbilityService;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 能力字典
 */
@RestController
@RequestMapping("/evaluation/ability")
public class AbilityController {

    @Autowired
    private CmAbilityService cmAbilityService;

    /**
     * 能力列表
     */
    @GetMapping("")
    public Result getAbilityList(){
        return cmAbilityService.getAbilityTree();
    }

    /**
     * 同级新增&下级新增
     */
    @PostMapping("/create")
   Result createByAbilitySecretary(@RequestBody CreateAbilityReq createAbilityReq){
        CmAbility cmAbility = createAbilityReq.getCmAbility();
        //同级新增
        if(Objects.equals(createAbilityReq.getType(), "1")){
            cmAbility.setPid(createAbilityReq.getPid());
            cmAbility.setAbilitydeep(createAbilityReq.getAbilitydeep());
        }
        //下级新增
        else {
            cmAbility.setPid(createAbilityReq.getId());
            cmAbility.setAbilitydeep(createAbilityReq.getAbilitydeep()+1);
        }
        return cmAbilityService.createOneAbility(cmAbility);
   }

    /**
     * 删除能力——批量删除
     */
   @PostMapping("/delete")
   public Result deleteAbilityByIDs(@RequestBody List<String> ids){
       return cmAbilityService.deleteAbilityByIDs(ids);
   }

    /**
     * 升级能力
     */
   @GetMapping("/upgrade")
   public Result upgradeOneAbility(@RequestParam(name = "id")String id){
            return cmAbilityService.upgradeOneAbilityByID(id);
    }
    @PostMapping("/update")
    public Result updateAbility(@RequestBody CmAbility cmAbility){
        return cmAbilityService.updateOneAbility(cmAbility);
    }
}
