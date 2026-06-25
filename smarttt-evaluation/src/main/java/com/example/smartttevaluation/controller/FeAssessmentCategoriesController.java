package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.AssessmentCategorySearchReq;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.service.FeAssessmentCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 考核项类别表 Controller
 */
@RestController
@RequestMapping("/fe/assessment-categories")
public class FeAssessmentCategoriesController {

    @Autowired
    private FeAssessmentCategoriesService feAssessmentCategoriesService;

    /**
     * 添加考核项类别
     * @param assessmentCategory 考核项类别对象
     * @return 是否成功
     */
    @PostMapping("/add")
    public Result addAssessmentCategory(@RequestBody FeAssessmentCategories assessmentCategory) {
        try {
            boolean success = feAssessmentCategoriesService.addAssessmentCategory(assessmentCategory);
            return success ? Result.success() : Result.error("新增考核项类别失败");
        } catch (RuntimeException e) {
            // 处理字段校验异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

    /**
     * 根据课程ID查询考核项类别（分页）
     * @param courseId 课程ID
     * @param current 当前页
     * @param size 每页大小
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result getByCourseIdWithPage(
            @RequestParam String courseId,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "-1") Long size) {
        Page<FeAssessmentCategories> page = feAssessmentCategoriesService.getByCourseIdWithPage(courseId, current, size);
        return Result.success(page);
    }

    /**
     * 根据课程ID查询考核项类别（不分页）
     * @param courseId 课程ID
     * @return 考核项类别列表
     */
    @GetMapping("/list/all")
    public Result getByCourseId(@RequestParam String courseId) {
        List<FeAssessmentCategories> list = feAssessmentCategoriesService.getByCourseId(courseId);
        return Result.success(list);
    }

    /**
     * 批量删除考核项类别
     * @param ids 类别ID列表
     * @return 是否成功
     */
    @DeleteMapping("/batch")
    public Result batchDeleteByIds(@RequestBody List<String> ids) {
        boolean success = feAssessmentCategoriesService.batchDeleteByIds(ids);
        return success ? Result.success() : Result.error("批量删除考核项类别失败");
    }

    /**
     * 根据ID更新考核项类别
     * @param assessmentCategory 考核项类别对象（必须包含id，其他字段可选）
     * @return 是否成功
     */
    @PutMapping("/update")
    public Result updateAssessmentCategory(@RequestBody FeAssessmentCategories assessmentCategory) {
        try {
            boolean success = feAssessmentCategoriesService.updateAssessmentCategory(assessmentCategory);
            return success ? Result.success() : Result.error("更新考核项类别失败");
        } catch (RuntimeException e) {
            // 处理字段校验异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

    /**
     * 搜索考核项类别
     * @param searchReq 搜索条件
     * @return 分页结果
     */
    @PostMapping("/search")
    public Result searchAssessmentCategories(@RequestBody AssessmentCategorySearchReq searchReq) {
        Page<FeAssessmentCategories> page = feAssessmentCategoriesService.searchAssessmentCategories(searchReq);
        return Result.success(page);
    }

    /**
     * 从其他课程复制考核项设计（考核项类别）到当前课程（覆盖当前课程已有考核项类别）
     * @param pastCourseId 来源课程ID
     * @return 复制结果
     */
    @PostMapping("/copy")
    @AuthRequired
    @Transactional(rollbackFor = Exception.class)
    public Result copyAssessmentCategories(@RequestParam("pastCourseId") String pastCourseId) {
        Token token = getTokenFromContext();
        String currentCourseId = token.getObsid();
        feAssessmentCategoriesService.copyCategories(pastCourseId, currentCourseId);
        return Result.success("考核项设计复制成功");
    }

}
