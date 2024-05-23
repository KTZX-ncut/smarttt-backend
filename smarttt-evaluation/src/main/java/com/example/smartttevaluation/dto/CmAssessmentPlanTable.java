package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.CmAssessmentPlanItem;
import com.example.smartttevaluation.pojo.CmAssessmentPlanProportion;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmAssessmentPlanTable {
    List<CmAssessmentPlanItem> items;
    CmAssessmentPlanProportion proportion;
}
