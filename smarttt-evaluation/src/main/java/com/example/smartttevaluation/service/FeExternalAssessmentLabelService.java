package com.example.smartttevaluation.service;

import com.example.smartttevaluation.pojo.FeExternalAssessmentTaskLabel;

import java.util.List;

public interface FeExternalAssessmentLabelService {
    void addLabel(FeExternalAssessmentTaskLabel label);
    List<FeExternalAssessmentTaskLabel> getLabelsByClassroom(String classroomId);
    void updateLabel(FeExternalAssessmentTaskLabel label);
    void deleteLabel(String id);
}
