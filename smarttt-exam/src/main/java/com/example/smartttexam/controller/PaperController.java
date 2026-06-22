package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.dto.PaperAutoGenerateRequest;
import com.example.smartttexam.dto.PaperManualGenerateRequest;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.Token;
import com.example.smartttexam.service.PaperService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 组卷控制器
 * 基础路径: /exam/paper
 */
@Api(tags = "组卷管理")
@RestController
@RequestMapping("/exam/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping("/autoGenerate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result autoGenerate(HttpServletRequest httpRequest,
                                @RequestBody PaperAutoGenerateRequest request) {
        Token token = CommonFunctions.getToken(httpRequest);
        if (token != null) {
            request.setCreatorId(token.getId());
        }
        return paperService.autoGenerate(request);
    }

    @PostMapping("/manualGenerate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result manualGenerate(HttpServletRequest httpRequest,
                                  @RequestBody PaperManualGenerateRequest request) {
        Token token = CommonFunctions.getToken(httpRequest);
        if (token != null) {
            request.setCreatorId(token.getId());
        }
        return paperService.manualGenerate(request);
    }

    @GetMapping("/list")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getPaperList(@RequestParam String courseId) {
        return paperService.getPaperList(courseId);
    }

    @GetMapping("/questions")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getPaperQuestions(@RequestParam String paperId) {
        return paperService.getPaperQuestions(paperId);
    }

    @PostMapping("/publish")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result publishPaper(@RequestParam String paperId,
                                @RequestParam String classroomId) {
        return paperService.publishPaper(paperId, classroomId);
    }

    @PostMapping("/delete")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result deletePaper(@RequestParam String paperId) {
        return paperService.deletePaper(paperId);
    }
}
