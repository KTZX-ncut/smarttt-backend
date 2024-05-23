package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAssessmentPlanItem;
import com.example.smartttevaluation.pojo.CmAssessmentPlanProportion;
import com.example.smartttevaluation.pojo.CmKeywords;

import java.util.List;

public interface CmAssessmentPlanService {
    Result getAssessmentPlanTable(String courseid);
    Result updateItem(CmAssessmentPlanItem cmAssessmentPlanItem);
    Result updateProportion(CmAssessmentPlanProportion cmAssessmentPlanProportion);
}
