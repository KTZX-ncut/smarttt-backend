package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmCourseAssessment;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 *  考核项方案
 */
@RestController
@RequestMapping("/evaluation/assessmentPlan")
public class CourseAssessmentController {
    @Autowired
    private CmCourseAssessmentService cmAssessmentPlanService;
    @GetMapping("/getAssessmentTable")
    @AuthRequired(type = "admin",menu = "531500340-dc11e4e7-6e9f-4975-a6b7-5f97ba1c46d3",isReadOnly = true)
    Result getAssessmentPlanTable(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmAssessmentPlanService.getAssessmentTable(token.getObsid());
    }

    @PostMapping("/updateStandardScore")
    Result updateStandardScore(@RequestBody CmCourseAssessment cmCourseAssessment){
        return cmAssessmentPlanService.updateStandardScore(cmCourseAssessment);
    }

    @PostMapping("/updatePercent")
    Result updatePercent(@RequestBody CmCheckitem cmCourseCheckItem){
        return cmAssessmentPlanService.updatePercent(cmCourseCheckItem);
    }

}
