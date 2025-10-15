package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.smartttevaluation.mapper.FeAssessmentCategoriesMapper;
import com.example.smartttevaluation.mapper.FeObjectiveAssessmentCategoryMapper;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
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

}
