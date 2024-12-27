package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.pojo.CmCourseCheckitemFile;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 考核项方案
 */
@RestController
@RequestMapping("/evaluation/assessmentPlan")
public class CourseAssessmentController {
    @Autowired
    private CmCourseAssessmentService cmAssessmentPlanService;

    /**
     * 获取考核方案表格
     */
    @GetMapping("/getAssessmentTable")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3", isReadOnly = true)
    Result getAssessmentPlanTable(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.getAssessmentTable(token.getObsid());
    }

    /**
     * 批量修改考核方案表格
     */
    @PostMapping("/updateAssessmentTable")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3")
    Result updateAssessmentTable(@RequestBody Map<String, Object> data, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.updateAssessmentTable(data, token.getObsid());
    }
    /**
     * 获取课堂的作业实验考试列表
     */
    @GetMapping("/getFileList")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3", isReadOnly = true)
    Result getFiles(@RequestParam("checkitemId") String checkitemId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        CmCheckitem cmCheckitem = new CmCheckitem();
        cmCheckitem.setId(checkitemId);
        // 这里的obsid其实是课堂id，因为任课教师会用这个接口
        cmCheckitem.setCourseid(token.getObsid());
        return cmAssessmentPlanService.getFiles(cmCheckitem);
    }
    /**
     * 预览用户上传的excel表
     */
    @GetMapping("showExcel")
    public Result showExcel(@RequestParam("fileId") String fileId) {
        return cmAssessmentPlanService.showExcel(fileId);
    }
    /**
     * 获取上传模板
     */
    @GetMapping("getUploadTemplate")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3", isReadOnly = true)
    Result getUploadTemplate(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.getUploadTemplate(token.getObsid());
    }
    /**
     * 上传文件
     */
    @PostMapping("/uploadFile")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3")
    Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.uploadFile(file, token.getObsid());
    }
    /**
     * 删除文件时获取与其关联的考核项列表
     */
    @GetMapping("/getAssociateCheckitems")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3", isReadOnly = true)
    Result getAssociateFiles(@RequestParam("fileId") String fileId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.getAssociateCheckitems(fileId, token.getObsid());
    }
    /**
     * 删除文件
     */
    @PostMapping("/deleteFile")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3")
    Result deleteFile(@RequestBody CmCourseCheckitemFile cmCourseCheckitemFile, HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmCourseCheckitemFile.setObsid(token.getObsid());
        return cmAssessmentPlanService.deleteFile(cmCourseCheckitemFile);
    }
    /**
     * 关联文件
     */
    @PostMapping("/associate")
    @AuthRequired(type = "admin", menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3")
    Result associate(@RequestBody Map<String, Object> data, HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.associate(data, token.getObsid());
    }
    /**
     * 取消关联文件
     */
    @PostMapping("/disassociate")
    Result disassociate(@RequestBody Map<String, Object> data) {
        return cmAssessmentPlanService.disassociate(data);
    }
    
}
