package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAssessmentPlanItem;
import com.example.smartttevaluation.pojo.CmAssessmentPlanProportion;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.service.CmAssessmentPlanService;
import com.example.smartttevaluation.service.CmKnowledgeUnitService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation/assessmentPlan")



public class AssessmentPlanController {
    @Autowired
    private CmAssessmentPlanService cmAssessmentPlanService;
    /**
     *考核方案表格
     */
    @GetMapping("/getAssessmentPlanTable")
    Result getAssessmentPlanTable(@RequestParam("courseid") String courseid){
        return cmAssessmentPlanService.getAssessmentPlanTable(courseid);
    }
    /**
     *更新考核项
     */
    @PostMapping("/updateAssessmentPlanItem")
    Result updateAssessmentPlanItem(@RequestBody CmAssessmentPlanItem cmAssessmentPlanItem){
        return cmAssessmentPlanService.updateItem(cmAssessmentPlanItem);
    }
    /**
     *更新考核方案比例
     */
    @PostMapping("/updateAssessmentPlanProportion")
    Result updateAssessmentPlanProportion(@RequestBody CmAssessmentPlanProportion cmAssessmentPlanProportion){
        return cmAssessmentPlanService.updateProportion(cmAssessmentPlanProportion);
    }

}
