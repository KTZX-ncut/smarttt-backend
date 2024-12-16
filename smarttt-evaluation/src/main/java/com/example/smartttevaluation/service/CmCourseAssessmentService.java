package com.example.smartttevaluation.service;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CmCourseAssessmentService {
    Result getAssessmentTable(String courseid);
    Result updateStandardScores(Map<String, Map<String, Integer>> jsonData, String obsid);
    Result updateAssessmentTable(Map<String, Object> data, String obsid);
    Result getFiles(CmCheckitem cmCheckitem);
    Result showExcel(String fileId);
    Result getUploadTemplate(String classroomId);
    Result uploadFile(MultipartFile file, String classroomId);
    Result getAssociateCheckitems(String fileId, String obsid);
    Result deleteFile(CmCourseCheckitemFile cmCourseCheckitemFile);
    Result associate(Map<String, Object> data, String obsId);
    Result disassociate(Map<String, Object> data);
}
