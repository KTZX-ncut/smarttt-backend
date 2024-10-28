package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface CmCourseAssessmentService {
    Result getAssessmentTable(String courseid);
    Result updateStandardScores(Map<String, Map<String, Integer>> jsonData, String obsid);
    Result updateAssessmentTable(Map<String, Object> data, String obsid);
    Result getFiles(CmCheckitem cmCheckitem);
    Result uploadFile(CmCourseCheckitemFile cmCourseCheckitemFile) throws IOException;
    Result getAssociateCheckitems(String fileId, String obsid);
    Result deleteFile(CmCourseCheckitemFile cmCourseCheckitemFile);
    Result associate(Map<String, Object> data);
    Result disassociate(Map<String, Object> data);
}
