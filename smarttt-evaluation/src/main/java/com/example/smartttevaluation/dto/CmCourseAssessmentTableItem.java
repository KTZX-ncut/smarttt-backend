package com.example.smartttevaluation.dto;

import com.example.smartttevaluation.pojo.CmCourseAssessment;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCourseAssessmentTableItem {
    String name;
    List<CmCourseAssessment> cmCourseAssessments;
}
