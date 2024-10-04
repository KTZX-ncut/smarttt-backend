package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.service.CmAbilityService;
import com.example.smartttevaluation.service.CmGetabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 能力
 */
@RestController
@RequestMapping("/evaluation/getability")
public class GetabilityController {

    @Autowired
    private CmGetabilityService cmGetabilityService;
    @Autowired
    private CmAbilityService cmAbilityService;
    /**
     *课程能力列表
     */
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-c0fc7d55-5459-433c-ad6a-593856295d51",isReadOnly = true)
    public Result getGetability(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmGetabilityService.getGetability(token.getObsid());
    }

    @GetMapping("/allability")
    @AuthRequired
    public Result getAbility(HttpServletRequest request) {
        // 从token里那获取专业ID
        Token token = getTokenFromContext();
        if(token == null) return Result.error(-710,"请登录");
        String proId = token.getObsid();
        if(proId == null) return Result.error(-710,"token不合法");
        return cmAbilityService.getAbilityTreeByProId(proId);
    }
    /**
     *批量添加能力
     */
    @PostMapping("/insert")
    public Result insertGetability(@RequestParam("courseid") String courseid ,@RequestBody List<String> ids) {
        return cmGetabilityService.insertGetability(courseid,ids);
    }
    /**
     *批量删除能力
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
