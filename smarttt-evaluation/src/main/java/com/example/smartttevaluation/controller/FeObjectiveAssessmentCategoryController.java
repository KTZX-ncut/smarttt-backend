package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.FeObjectiveAssessmentCategory;
import com.example.smartttevaluation.service.FeObjectiveAssessmentCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/objective-category")
@RequiredArgsConstructor
@Slf4j
public class FeObjectiveAssessmentCategoryController {

    private final FeObjectiveAssessmentCategoryService service;

    /**
     * 批量保存或更新目标-类别分数
     */
    @PostMapping("/batch-save")
    public Result batchSave(@RequestBody List<FeObjectiveAssessmentCategory> list) {
        if (list == null || list.isEmpty()) {
            return Result.error(-400, "请求体不能为空");
        }
        try {
            Map<String, Object> msg = service.batchSave(list);
            return Result.success(msg);
        } catch (Exception e) {
            log.error("批量保存失败：{}", e.getMessage(), e);
            return Result.error(-500, e.getMessage());
        }
    }
}
