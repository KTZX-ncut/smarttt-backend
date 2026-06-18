package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.CmCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;
import static com.example.smartttcourse.Utils.CommonFunctions.TokenSK;
import static com.example.smartttcourse.Utils.JwtTokenUtils.getToken;

/**
 * 课程转换
 */
@RestController
@RequestMapping("/coursemangt/switch")
@Api(tags = "1. 课程切换", description = "教师可授课程列表和当前课程上下文切换接口")
public class SwitchCourseController {
    @Autowired
    private CmCourseService cmCourseService;
    @GetMapping("")
    @ApiOperation(value = "获取教师可授课程列表", notes = "根据当前 token 返回当前教师可切换的课程列表。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result getTeacherCourse(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCourseService.getTeacherCourse(token);
    }
    @GetMapping("/switchteachercourse")
    @ApiOperation(value = "切换教师当前课程", notes = "将当前 token 中的 obsid 切换为指定课程 ID，并返回新的 token。")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result switchTeacherCourse(@ApiParam(value = "课程 ID", required = true, example = "course-001") @RequestParam("id") String id,HttpServletRequest request){
        Token token = getTokenFromContext();
        token.setObsid(id);
        return Result.success(getToken(token,TokenSK));
    }
}
