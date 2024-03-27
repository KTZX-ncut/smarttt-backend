package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.service.CmGetabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 能力管理
 */
@RestController
@RequestMapping("/evaluation/getability")
public class GetabilityController {

    @Autowired
    private CmGetabilityService cmGetabilityService;

    @GetMapping
    public Result getGetability() {
        return cmGetabilityService.getGetability();
    }


    @PostMapping("/delete")
    public Result deleteGetabilityByID(@RequestBody List<String> ids) {
        return cmGetabilityService.deleteGetabilityByID(ids);
    }

    @PostMapping
    public Result updateGetability(@RequestBody CmAbility cmAbility){
        return cmGetabilityService.updateGetability(cmAbility);
    }
}
