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

    /** 从token推断classroomId：如果obsid是课堂则直接返回，否则返回null */
    private String resolveClassroomId(HttpServletRequest request) {
        Token token = CommonFunctions.getToken(request);
        if (token == null || token.getObsid() == null) return null;
        String obsid = token.getObsid();
        // obsid是课程 → 不是课堂，返回null
        if (classroomMapper.isCourse(obsid) > 0) return null;
        // obsid不是课程 → 是课堂ID
        return obsid;
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
            if (request.getClassroomId() == null || request.getClassroomId().isEmpty())
                request.setClassroomId(resolveClassroomId(httpRequest));
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
            if (request.getClassroomId() == null || request.getClassroomId().isEmpty())
                request.setClassroomId(resolveClassroomId(httpRequest));
        }
        return paperService.manualGenerate(request);
    }

    @GetMapping("/list")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getPaperList(HttpServletRequest request,
                                @RequestParam(required = false) String courseId,
                                @RequestParam(required = false) String classroomId) {
        if (courseId == null || courseId.isEmpty()) courseId = resolveCourseId(request);
        if (classroomId == null || classroomId.isEmpty()) classroomId = resolveClassroomId(request);
        return paperService.getPaperList(courseId != null ? courseId : "", classroomId);
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
