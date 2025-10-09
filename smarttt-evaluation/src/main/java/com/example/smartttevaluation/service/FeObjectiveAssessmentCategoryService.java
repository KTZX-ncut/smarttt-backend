package com.example.smartttevaluation.service;

import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
import java.util.List;
import java.util.Map;

public interface FeObjectiveAssessmentCategoryService {
    Map<String, Object> batchSave(List<FeObjectiveAssessmentCategory> list);
}
