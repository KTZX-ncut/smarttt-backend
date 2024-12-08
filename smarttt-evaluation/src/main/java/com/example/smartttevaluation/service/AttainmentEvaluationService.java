package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;

public interface AttainmentEvaluationService {
    Result checkRole(String obsId);
    Result getClassroomByCourseId(String obsId);
    Result getClassroomByClassroomId(String obsId);
    Result getCourseByClassroomId(String obsId);
    Result teacherGetAssessmentTable(String obsId);
}
