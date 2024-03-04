package com.example.smartttadmin.controller;


import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUnitService;
import com.example.smartttadmin.service.impl.StUnitServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.smartttadmin.pojo.EnhancedUniqueID.generateEnhancedID;

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
    //同级新增
    @PostMapping("/siblingadd")
    Result obsSiblingAdd(@RequestBody SmObs smObs){
        smObs.setId(generateEnhancedID("sm_obs"));
        return smObsService.createOneObs(smObs);
    }
    //下级新增


}

