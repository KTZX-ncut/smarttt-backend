package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.service.CmAbilityService;
import com.example.smartttevaluation.service.CmGetabilityService;
import com.example.smartttevaluation.service.CourseService;
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
    @Autowired
    private CourseService courseService;

    /**
     * 课程能力列表
     */
    @GetMapping
    @AuthRequired(type = "admin", menu = "531500340-c0fc7d55-5459-433c-ad6a-593856295d51", isReadOnly = true)
    public Result getGetability(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmGetabilityService.getGetability(token.getObsid());
    }

    @GetMapping("/allability")
    @AuthRequired(type = "admin", menu = "531500340-c0fc7d55-5459-433c-ad6a-593856295d51", isReadOnly = true)
    public Result getAbility(HttpServletRequest request) {
        Token token = getTokenFromContext();
        String courseId = token.getObsid();
        if (StringUtils.isBlank(courseId)) {
            return Result.error(-710, "课程id不能为空，请登录");
        }
        // 根据课程id 去 课程表 拿到专业id
        String proId = courseService.getProIdByCourseId(courseId);
        if (StringUtils.isBlank(proId)) {
            return Result.error(-710, "非法请求");
        }
        // 根据专业id去查对应的能力
        return cmAbilityService.getAbilityTreeByProId(proId);
    }

    /**
     * 批量添加能力
     */
    @PostMapping("/insert")
    @AuthRequired(type = "admin",menu = "531500340-c0fc7d55-5459-433c-ad6a-593856295d51")
    public Result insertGetability(@RequestBody List<String> ids, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmGetabilityService.insertGetability(token.getObsid(), ids);
    }

    /**
     * 批量删除能力
     */
    @PostMapping("/delete")
    @AuthRequired(type = "admin",menu = "531500340-c0fc7d55-5459-433c-ad6a-593856295d51")
    public Result deleteGetabilityByID(@RequestBody List<String> ids, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmGetabilityService.deleteGetabilityByID(token.getObsid(), ids);
    }
/*
    @PostMapping("/update")
    public Result updateGetability(@RequestBody CmGetability cmGetability){
        return cmGetabilityService.updateGetability(cmGetability);
    }
    */

    /**
     * 通过能力id获取kwa
     */
    @PostMapping("/getKwaByGetabilityId")
    @AuthRequired(type = "admin",menu = "531500340-c0fc7d55-5459-433c-ad6a-593856295d51", isReadOnly = true)
    public Result getKwaByGetabilityId(@RequestBody List<String> ids, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmGetabilityService.getKwaByGetabilityId(token.getObsid(), ids);
    }

}
