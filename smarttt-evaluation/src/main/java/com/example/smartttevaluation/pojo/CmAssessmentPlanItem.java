package com.example.smartttevaluation.pojo;

import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmAssessmentPlanItem {
    String id;
    String name;
    int s1;
    int s2;
    int s3;
    int s4;
    int s5;
}
