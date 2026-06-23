package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.Token;
import com.example.smartttexam.service.TestQueLibService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttexam.Utils.AuthorizationAspect.getTokenFromContext;

@Api(tags = "课程题库")
@RestController
@RequestMapping("/exam/coursequelib")
public class CourseQueLibController {
    @Autowired
    private TestQueLibService testQueLibService;
    @GetMapping
    @AuthRequired(type = "admin",menu = "531500340-a9278b5d-f2ba-4429-854f-4662000f50e6",isReadOnly = true)
    public Result getCourseQL(HttpServletRequest request){
        Token token = getTokenFromContext();
        return testQueLibService.getCourseQL(token.getObsid());
    }
}
