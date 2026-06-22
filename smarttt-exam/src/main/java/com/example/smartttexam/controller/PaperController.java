package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.Utils.CommonFunctions;
import com.example.smartttexam.dto.*;
import com.example.smartttexam.mapper.CmClassroomMapper;
import com.example.smartttexam.service.PaperService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "组卷管理")
@RestController
@RequestMapping("/exam/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private CmClassroomMapper classroomMapper;

    private String resolveCourseId(HttpServletRequest request) {
        Token token = CommonFunctions.getToken(request);
        if (token == null || token.getObsid() == null) return "";
        String obsid = token.getObsid();
        if (classroomMapper.isCourse(obsid) > 0) return obsid;
        String cid = classroomMapper.getCourseIdByClassroomId(obsid);
        return cid != null ? cid : obsid;
    }

    @PostMapping("/autoGenerate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result autoGenerate(HttpServletRequest httpRequest,
                                @RequestBody PaperAutoGenerateRequest request) {
        Token token = CommonFunctions.getToken(httpRequest);
        if (token != null) {
            request.setCreatorId(token.getId());
            if (request.getCourseId() == null || request.getCourseId().isEmpty())
                request.setCourseId(resolveCourseId(httpRequest));
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
            if (request.getCourseId() == null || request.getCourseId().isEmpty())
                request.setCourseId(resolveCourseId(httpRequest));
        }
        return paperService.manualGenerate(request);
    }

    @GetMapping("/list")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getPaperList(HttpServletRequest request,
                                @RequestParam(required = false) String courseId) {
        if (courseId == null || courseId.isEmpty()) courseId = resolveCourseId(request);
        return paperService.getPaperList(courseId != null ? courseId : "");
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
