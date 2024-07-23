package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCourseAssessment;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.pojo.CmKeywords;

import java.util.List;

public interface CmCourseAssessmentService {
    Result getAssessmentTable(String courseid);
    Result updateStandardScore(CmCourseAssessment cmCourseAssessment);
    Result updatePercent(CmCheckitem cmCourseCheckItem);
}
