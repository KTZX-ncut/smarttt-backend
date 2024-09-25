package com.example.smartttevaluation.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.CreateAbilityReq;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmCourse;
import com.example.smartttevaluation.service.CourseService;
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
    @Autowired
    private CourseService courseService;

    /**
     * 能力列表
     */
    @GetMapping("")
    public Result getAbilityListByProId(@RequestParam("courseId") String courseId){
        // 先拿到课程id
        //Token token = getTokenFromContext();
        //String courseId = token.getObsid();
        if (StringUtils.isBlank(courseId)){
            return Result.error(-710,"课程id不能为空，请登录");
        }
        // 根据课程id 去 课程表 拿到专业id
        String proId = courseService.getProIdByCourseId(courseId);
        if (StringUtils.isBlank(proId)){
            return Result.error(-710,"非法请求");
        }
        // 根据专业id去查对应的能力
        return cmAbilityService.getAbilityTreeByProId(proId);
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
