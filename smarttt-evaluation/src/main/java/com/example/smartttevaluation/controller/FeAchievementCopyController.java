package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.FeObjectiveAssessmentCategoryMapper;
import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
import com.example.smartttevaluation.service.FeAssessmentCategoriesService;
import com.example.smartttevaluation.service.FeCourseObjectivesService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/fe/achievement")
@RequiredArgsConstructor
public class FeAchievementCopyController {

    private final FeCourseObjectivesService feCourseObjectivesService;
    private final FeAssessmentCategoriesService feAssessmentCategoriesService;
    private final FeObjectiveAssessmentCategoryMapper feObjectiveAssessmentCategoryMapper;

    /**
     * 复制达成性评价建模：课程目标 → 考核项类别 → 目标-类别关联
     */
    @PostMapping("/copy")
    @AuthRequired
    @Transactional(rollbackFor = Exception.class)
    public Result copyAchievementModel(@RequestParam("pastCourseId") String pastCourseId, HttpServletRequest request) {
        Token token = getTokenFromContext();
        String currentCourseId = token.getObsid();

        // 1. 复制课程目标，获取 oldId->newId 映射
        Map<String, String> objectiveIdMap = feCourseObjectivesService.copyCourseObjectives(pastCourseId, currentCourseId);

        // 2. 复制考核项类别，获取 oldId->newId 映射
        Map<String, String> categoryIdMap = feAssessmentCategoriesService.copyCategories(pastCourseId, currentCourseId);

        // 3. 复制目标-类别关联（fe_objective_assessment_category）
        LambdaQueryWrapper<FeObjectiveAssessmentCategory> queryWrapper = new LambdaQueryWrapper<>();
        // fe_objective_assessment_category 通过 objective_id 关联 fe_course_objectives
        // 先查出 past course 所有关联记录（objectiveId 在 pastCourseId 的目标中）
        if (!objectiveIdMap.isEmpty()) {
            queryWrapper.in(FeObjectiveAssessmentCategory::getObjectiveId, objectiveIdMap.keySet());
            List<FeObjectiveAssessmentCategory> pastLinks = feObjectiveAssessmentCategoryMapper.selectList(queryWrapper);

            // 删除当前课程已有的关联
            if (!categoryIdMap.isEmpty()) {
                LambdaQueryWrapper<FeObjectiveAssessmentCategory> delWrapper = new LambdaQueryWrapper<>();
                delWrapper.in(FeObjectiveAssessmentCategory::getCategoryId, categoryIdMap.values());
                feObjectiveAssessmentCategoryMapper.delete(delWrapper);
            }

            for (FeObjectiveAssessmentCategory link : pastLinks) {
                String newObjectiveId = objectiveIdMap.get(link.getObjectiveId());
                String newCategoryId = categoryIdMap.get(link.getCategoryId());
                if (newObjectiveId == null || newCategoryId == null) continue;
                FeObjectiveAssessmentCategory newLink = new FeObjectiveAssessmentCategory();
                newLink.setObjectiveId(newObjectiveId);
                newLink.setCategoryId(newCategoryId);
                newLink.setScore(link.getScore());
                feObjectiveAssessmentCategoryMapper.upsert(newLink);
            }
        }

        return Result.success("达成性评价建模复制成功");
    }
}
