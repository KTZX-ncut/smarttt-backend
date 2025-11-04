package com.example.smartttevaluation.service;


import com.example.smartttevaluation.dto.ExternalAssessmentExcel;

import java.util.List;

public interface FeExternalAssessmentService {
    void importExternalAssessment(List<ExternalAssessmentExcel> list,
                                         List<String> externalAssessmentNameList,
                                         String classroomId,
                                         String externalLabelId);
}
