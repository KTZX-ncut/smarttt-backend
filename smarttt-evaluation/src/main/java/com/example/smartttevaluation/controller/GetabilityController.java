package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmGetability;
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
    /**
     *课程能力列表
     */
    @GetMapping
    public Result getGetability(@RequestParam("courseid") String courseid) {
        return cmGetabilityService.getGetability(courseid);
    }
/*Y*/
    //获取全部能力字典
    /*@GetMapping("/allability")
    public Result getAbility() {
        return cmGetabilityService.getAbility();
    }*/
    /**
     *添加一条能力
     */
    @PostMapping("/insert")
    public Result insertGetability(@RequestParam("courseid") String courseid ,@RequestBody List<String> ids) {
        return cmGetabilityService.insertGetability(courseid,ids);
    }
    /**
     *删除一条能力
     */
    @PostMapping("/delete")
    public Result deleteGetabilityByID(@RequestParam("courseid") String courseid , @RequestBody List<String> ids) {
        return cmGetabilityService.deleteGetabilityByID(courseid,ids);
    }
/*
    @PostMapping("/update")
    public Result updateGetability(@RequestBody CmGetability cmGetability){
        return cmGetabilityService.updateGetability(cmGetability);
    }
    */
    /**
     *通过能力id获取kwa
     */
    @PostMapping("/getKwaByGetability")
    public Result getKwaByGetability(@RequestParam("courseid") String courseid , @RequestBody List<String> ids) {
        return cmGetabilityService.getKwaByGetability(courseid,ids);
    }
}
