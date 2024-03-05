package com.example.smartttadmin.controller;


import com.example.smartttadmin.dto.CreateUnitsReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 学期管理
 */
@Controller
@RestController
@RequestMapping("/sysmangt/units")
public class UnitMangtController {

    @Autowired
    private StUnitService stUnitService;
    @Autowired
    private SmObsService smObsService;
    @GetMapping
    Result getObsList(){
        return smObsService.getObsTree();
    }
    @PostMapping("/create")
    Result createByTeachingSecretary(@RequestBody CreateUnitsReq createUnitsReq){
        SmObs smObs = createUnitsReq.getSmObs();
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
        return smObsService.createOneObs(smObs);
    }
    @GetMapping("/delete")
    public Result deleteOneObs(@RequestParam(name = "id")String id){
        return smObsService.deleteObsByID(id);
    }
    @GetMapping("/upgrade")
    public Result upgradeOneObs(@RequestParam(name = "id")String id){
        return smObsService.upgradeOneObsByID(id);
    }
}

