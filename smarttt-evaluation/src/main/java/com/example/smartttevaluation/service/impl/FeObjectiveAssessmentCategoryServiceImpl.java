package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.smartttevaluation.mapper.FeAssessmentCategoriesMapper;
import com.example.smartttevaluation.mapper.FeObjectiveAssessmentCategoryMapper;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
import com.example.smartttevaluation.service.FeAssessmentCategoriesService;
import com.example.smartttevaluation.service.FeCourseObjectivesService;
import com.example.smartttevaluation.service.FeObjectiveAssessmentCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeObjectiveAssessmentCategoryServiceImpl implements FeObjectiveAssessmentCategoryService {

    private final FeObjectiveAssessmentCategoryMapper mapper;
    private final FeAssessmentCategoriesMapper categoryMapper;
    private final FeCourseObjectivesService feCourseObjectivesService;
    private final FeAssessmentCategoriesService feAssessmentCategoriesService;

    /**
     * 批量保存：支持多类别数据一次性导入，每个类别单独事务与校验
     *
     * @param list 前端传入的目标-类别-分数组
     * @return Map<categoryId, 校验结果>
     */

    @Override
    public Map<String, Object> batchSave(List<FeObjectiveAssessmentCategory> list) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("保存失败：数据为空");
        }

        Map<String, List<FeObjectiveAssessmentCategory>> groupByCategory =
                list.stream().collect(Collectors.groupingBy(FeObjectiveAssessmentCategory::getCategoryId));

        Map<String, Object> resultMap = new LinkedHashMap<>();

        // ✅ 获取代理对象（注意类型改成当前类）
        FeObjectiveAssessmentCategoryServiceImpl self =
                (FeObjectiveAssessmentCategoryServiceImpl) AopContext.currentProxy();

        groupByCategory.forEach((categoryId, items) -> {
            try {
                self.saveOneCategoryBatch(items);  // ✅ 事务生效
                resultMap.put(categoryId, "✅ 保存成功");
            } catch (Exception e) {
                log.error("❌ 类别 {} 保存失败：{}", categoryId, e.getMessage());
                resultMap.put(categoryId, "❌ 保存失败：" + e.getMessage());
            }
        });

        return resultMap;
    }



    /**
     * 保存单个类别的数据（独立事务 + 严格校验）
     */
    @Transactional
    public void saveOneCategoryBatch(List<FeObjectiveAssessmentCategory> list) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("单个类别数据为空");
        }

        String categoryId = list.get(0).getCategoryId();
        int successCount = 0;

        for (FeObjectiveAssessmentCategory item : list) {
            try {
                if (item == null
                        || item.getObjectiveId() == null
                        || item.getScore() == null
                        || item.getScore() < 0) {
                    continue;
                }

                // ✅ 直接调用 Mapper 的 upsert()
                mapper.upsert(item);
                successCount++;

            } catch (Exception e) {
                log.error("❌ 保存失败：categoryId={}, objectiveId={}, err={}",
                        item.getCategoryId(), item.getObjectiveId(), e.getMessage());
            }
        }

        // ✅ 校验分数
        validateCategoryTotalScores(Collections.singleton(categoryId));

        log.info("✅ 类别 {} 保存完成：成功 {} 条", categoryId, successCount);
    }

    /**
     * 校验指定类别分数之和是否等于预设分数
     */
    private void validateCategoryTotalScores(Set<String> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) return;

        log.info("🔍 开始校验类别分数：{}", categoryIds);

        List<FeAssessmentCategories> categories =
                categoryMapper.selectBatchIds(categoryIds);

        // 汇总分数：categoryId → sum(score)
        Map<String, Integer> scoreSumMap = mapper.selectList(
                new QueryWrapper<FeObjectiveAssessmentCategory>()
                        .in("category_id", categoryIds)
        ).stream().collect(Collectors.groupingBy(
                FeObjectiveAssessmentCategory::getCategoryId,
                Collectors.summingInt(FeObjectiveAssessmentCategory::getScore)
        ));

        for (FeAssessmentCategories category : categories) {
            Integer sum = scoreSumMap.getOrDefault(category.getId(), 0);
            Integer defined = category.getScore();
            if (!Objects.equals(sum, defined)) {
                String err = String.format(
                        "类别【%s】的目标分数总和(%d) ≠ 预设总分(%d)",
                        category.getCategoryName(), sum, defined
                );
                log.error(err);
                throw new RuntimeException(err); // ❗ 抛异常回滚
            }
        }

        log.info("✅ 类别校验通过：{}", categoryIds);
    }
    @Override
    public List<FeObjectiveAssessmentCategory> listByCondition(String categoryId, String objectiveId) {
        QueryWrapper<FeObjectiveAssessmentCategory> wrapper = new QueryWrapper<>();
        if (categoryId != null && !categoryId.trim().isEmpty()) {
            wrapper.eq("category_id", categoryId);
        }
        if (objectiveId != null && !objectiveId.trim().isEmpty()) {
            wrapper.eq("objective_id", objectiveId);
        }
        return mapper.selectList(wrapper);
    }

    /**
     * 整套复制考核方案：课程目标(fe_course_objectives) + 考核类别(fe_assessment_categories)
     * + 目标×类别分数矩阵(fe_objective_assessment_category)。
     * 复制前覆盖当前课程这三部分已有数据；矩阵表无 course_id，靠 objectiveId 集合定位，
     * 并用 旧->新 的 目标ID / 类别ID 映射重建外键。
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> copyAssessmentPlan(String pastCourseId, String currentCourseId) {
        if (pastCourseId == null || pastCourseId.isEmpty()) {
            throw new RuntimeException("源课程ID不能为空");
        }
        if (Objects.equals(pastCourseId, currentCourseId)) {
            throw new RuntimeException("不能复制当前课程自身");
        }

        // 源课程没有考核方案数据则直接提示，不清空当前课程
        List<FeCourseObjectives> pastObjectives = feCourseObjectivesService.getByCourseId(pastCourseId);
        if (pastObjectives == null || pastObjectives.isEmpty()) {
            throw new RuntimeException("所选历史课程没有可复制的考核方案数据");
        }

        // 1) 先清理当前课程的矩阵（该表无 course_id，只能按当前课程的 objectiveId 集合删）
        List<FeCourseObjectives> currentObjectives = feCourseObjectivesService.getByCourseId(currentCourseId);
        List<String> currentObjIds = currentObjectives.stream()
                .map(FeCourseObjectives::getId).collect(Collectors.toList());
        if (!currentObjIds.isEmpty()) {
            mapper.delete(new QueryWrapper<FeObjectiveAssessmentCategory>().in("objective_id", currentObjIds));
        }

        // 2) 复制课程目标、考核类别（各自内部覆盖当前课程已有数据，并返回 旧ID->新ID 映射）
        Map<String, String> objIdMap = feCourseObjectivesService.copyCourseObjectives(pastCourseId, currentCourseId);
        Map<String, String> catIdMap = feAssessmentCategoriesService.copyCategories(pastCourseId, currentCourseId);

        // 3) 复制矩阵：取源课程矩阵（按源目标ID集合），用两张映射把外键改写成新ID
        int matrixCopied = 0;
        if (!objIdMap.isEmpty()) {
            List<FeObjectiveAssessmentCategory> pastMatrix = mapper.selectList(
                    new QueryWrapper<FeObjectiveAssessmentCategory>().in("objective_id", objIdMap.keySet()));
            for (FeObjectiveAssessmentCategory m : pastMatrix) {
                String newObjId = objIdMap.get(m.getObjectiveId());
                String newCatId = catIdMap.get(m.getCategoryId());
                if (newObjId == null || newCatId == null) {
                    continue; // 映射缺失（脏数据/跨课程引用）跳过
                }
                FeObjectiveAssessmentCategory row = new FeObjectiveAssessmentCategory();
                row.setObjectiveId(newObjId);
                row.setCategoryId(newCatId);
                row.setScore(m.getScore());
                mapper.upsert(row);
                matrixCopied++;
            }
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("objectives", objIdMap.size());
        result.put("categories", catIdMap.size());
        result.put("matrix", matrixCopied);
        return result;
    }

}
