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

    /**
     * 整套复制考核方案：把源课程的 课程目标 + 考核类别 + 目标×类别分数矩阵 复制到当前课程
     * @return 各表复制条数统计
     */
    Map<String, Object> copyAssessmentPlan(String pastCourseId, String currentCourseId);
}
