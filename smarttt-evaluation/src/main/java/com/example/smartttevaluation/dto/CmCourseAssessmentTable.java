package com.example.smartttevaluation.dto;

import com.alibaba.fastjson.JSONObject;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CmCourseAssessmentTable {
    List<CmCheckitem> head;
    List<JSONObject> items;
    JSONObject percent;
}
