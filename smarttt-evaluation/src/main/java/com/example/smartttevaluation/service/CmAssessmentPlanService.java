package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAssessmentPlanItem;
import com.example.smartttevaluation.pojo.CmAssessmentPlanProportion;
import com.example.smartttevaluation.pojo.CmKeywords;

import java.util.List;

public interface CmAssessmentPlanService {
    /**
     *获取考核方案表格
     */
    Result getAssessmentPlanTable(String courseid);
    /**
     *更新考核项
     */
    Result updateItem(CmAssessmentPlanItem cmAssessmentPlanItem);
    /**
     *更新比例
     */
    Result updateProportion(CmAssessmentPlanProportion cmAssessmentPlanProportion);
}
