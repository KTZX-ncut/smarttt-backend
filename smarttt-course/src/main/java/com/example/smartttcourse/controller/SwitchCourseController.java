package com.example.smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.service.CmCourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;
import static com.example.smartttcourse.Utils.CommonFunctions.TokenSK;
import static com.example.smartttcourse.Utils.JwtTokenUtils.getToken;

@RestController
@RequestMapping("/coursemangt/switch")
public class SwitchCourseController {
    @Autowired
    private CmCourseService cmCourseService;
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result getTeacherCourse(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCourseService.getTeacherCourse(token);
    }
    @GetMapping("/switchteachercourse")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result switchTeacherCourse(@Param("id")String id,HttpServletRequest request){
        Token token = getTokenFromContext();
        token.setObsid(id);
        return Result.success(getToken(token,TokenSK));
    }
}
