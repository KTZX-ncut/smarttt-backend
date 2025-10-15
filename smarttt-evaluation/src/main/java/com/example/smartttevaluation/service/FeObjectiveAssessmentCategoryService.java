package com.example.smartttevaluation.service;

import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
import java.util.List;
import java.util.Map;

public interface FeObjectiveAssessmentCategoryService {

    Map<String, Object> batchSave(List<FeObjectiveAssessmentCategory> list);

    /**
     * ✅ 根据条件查询分数
     */
    List<FeObjectiveAssessmentCategory> listByCondition(String categoryId, String objectiveId);
}
