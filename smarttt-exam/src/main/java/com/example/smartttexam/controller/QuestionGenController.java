package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.dto.QuestionGenRequest;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.Token;
import com.example.smartttexam.service.QuestionGenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * AI出题控制器
 * 基础路径: /exam/questiongen
 */
@Api(tags = "AI出题")
@RestController
@RequestMapping("/exam/questiongen")
public class QuestionGenController {

    @Autowired
    private QuestionGenService questionGenService;

    @GetMapping("/kwa")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getCourseKwa(@RequestParam String courseId) {
        return questionGenService.getCourseKwa(courseId);
    }

    @PostMapping("/generate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result generateQuestions(HttpServletRequest httpRequest,
                                     @RequestBody QuestionGenRequest request) {
        Token token = CommonFunctions.getToken(httpRequest);
        if (token != null) {
            request.setCreatorId(token.getId());
        }
        return questionGenService.generateQuestions(request);
    }

    @GetMapping("/list")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getQuestions(@RequestParam String courseId) {
        return questionGenService.getQuestionsByCourse(courseId);
    }

    @PostMapping("/delete")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result deleteQuestions(@RequestBody List<String> libIds) {
        return questionGenService.deleteQuestions(libIds);
    }
}
