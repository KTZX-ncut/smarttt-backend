package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.dto.QuestionGenRequest;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.Token;
import com.example.smartttexam.mapper.CmClassroomMapper;
import com.example.smartttexam.service.QuestionGenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "AI出题")
@RestController
@RequestMapping("/exam/questiongen")
public class QuestionGenController {

    @Autowired
    private QuestionGenService questionGenService;

    @Autowired
    private CmClassroomMapper classroomMapper;

    private String resolveCourseId(HttpServletRequest request) {
        Token token = CommonFunctions.getToken(request);
        if (token == null || token.getObsid() == null) return "";
        String obsid = token.getObsid();
        if (classroomMapper.isCourse(obsid) > 0) return obsid;
        String cid = classroomMapper.getCourseIdByClassroomId(obsid);
        if (cid != null) return cid;
        return obsid;
    }

    @GetMapping("/kwa")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getCourseKwa(HttpServletRequest request,
                                @RequestParam(required = false) String courseId) {
        if (courseId == null || courseId.isEmpty()) courseId = resolveCourseId(request);
        return questionGenService.getCourseKwa(courseId != null ? courseId : "");
    }

    @PostMapping("/generate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result generateQuestions(HttpServletRequest httpRequest,
                                     @RequestBody QuestionGenRequest request) {
        Token token = CommonFunctions.getToken(httpRequest);
        if (token != null) {
            request.setCreatorId(token.getId());
            if (request.getCourseId() == null || request.getCourseId().isEmpty()) {
                String cid = resolveCourseId(httpRequest);
                if (!cid.isEmpty()) request.setCourseId(cid);
            }
        }
        return questionGenService.generateQuestions(request);
    }

    @GetMapping("/page")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getQuestionsPaged(HttpServletRequest request,
                                @RequestParam(required = false) String courseId,
                                @RequestParam(defaultValue = "1") int pageIndex,
                                @RequestParam(defaultValue = "20") int pageSize) {
        if (courseId == null || courseId.isEmpty()) courseId = resolveCourseId(request);
        return questionGenService.getQuestionsByCourse(courseId != null ? courseId : "", pageIndex, pageSize);
    }

    @PostMapping("/delete")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result deleteQuestions(@RequestBody List<String> libIds) {
        return questionGenService.deleteQuestions(libIds);
    }
}
