package com.example.smartttcourse.controller;

import com.example.smartttcourse.dto.CreateAbilityReq;
import com.example.smartttcourse.pojo.CmAbility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.service.CmAbilityService;

@RestController
@RequestMapping("/coursemangt/ability")
public class AbilityController {

    @Autowired
    private CmAbilityService cmAbilityService;

    @PostMapping
    public Result createNewAbility(@RequestBody CmAbility cmAbility){
        cmAbility.setAbilitydeep(2);
        cmAbility.setPid("237675254");
        return cmAbilityService.createOneAbility(cmAbility);
    }

    @GetMapping
    Result getAbilityList(){
            return cmAbilityService.getAbilityTree();
        }

    @PostMapping("/create")
   Result createByTeachingSecretary(@RequestBody CreateAbilityReq createAbilityReq){
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

   @PostMapping("/delete")
   public Result deleteAbilityByIDs(@RequestBody List<String> ids){
       return cmAbilityService.deleteAbilityByIDS(ids);
   }

   @GetMapping("/upgrade")
   public Result upgradeOneAbility(@RequestParam(name = "id")String id){
            return cmAbilityService.upgradeOneAbilityByID(id);
    }
}
