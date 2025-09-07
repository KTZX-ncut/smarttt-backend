package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.AssessmentItemDto;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.FeAssessmentItems;
import com.example.smartttevaluation.service.FeAssessmentItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 考核项表 Controller
 */
@RestController
@RequestMapping("/fe/assessment-items")
public class FeAssessmentItemsController {

    @Autowired
    private FeAssessmentItemsService feAssessmentItemsService;

    /**
     * 根据课堂ID查询实验和试卷信息
     * @param classroomId 课堂ID
     * @return 包含试卷和实验信息的Map
     */
    @GetMapping("/config/list")
    public Result getAssessmentItemsByClassroomId(@RequestParam String classroomId) {
        Map<String, List<AssessmentItemDto>> result = feAssessmentItemsService.getAssessmentItemsByClassroomId(classroomId);
        return Result.success(result);
    }

    /**
     * 批量添加考核项
     * @param assessmentItems 考核项列表
     * @return 是否成功
     */
    @PostMapping("/batch/add")
    public Result batchAddAssessmentItems(@RequestBody List<FeAssessmentItems> assessmentItems) {
        try {
            boolean success = feAssessmentItemsService.batchAddAssessmentItems(assessmentItems);
            return success ? Result.success() : Result.error("批量添加考核项失败");
        } catch (RuntimeException e) {
            // 处理唯一约束冲突等业务异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

    /**
     * 批量删除考核项
     * @param ids 考核项ID列表
     * @return 是否成功
     */
    @DeleteMapping("/batch/delete")
    public Result batchDeleteByIds(@RequestBody List<String> ids) {
        try {
            boolean success = feAssessmentItemsService.batchDeleteByIds(ids);
            return success ? Result.success() : Result.error("批量删除考核项失败");
        } catch (RuntimeException e) {
            // 处理业务异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

    /**
     * 分页查询考核项
     * @param current 当前页码，默认为1
     * @param size 每页大小，默认为-1（不分页）
     * @param categoryId 类别ID（可选）
     * @param courseId 课程ID（可选）
     * @param classroomId 课堂ID（可选）
     * @param objectiveId 目标ID（可选）
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result getAssessmentItemsWithPage(@RequestParam(defaultValue = "1") Long current, 
                                           @RequestParam(defaultValue = "-1") Long size,
                                           @RequestParam(required = false) String categoryId,
                                           @RequestParam(required = false) String courseId,
                                           @RequestParam(required = false) String classroomId,
                                           @RequestParam(required = false) String objectiveId) {
        try {
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<FeAssessmentItems> page = 
                feAssessmentItemsService.getAssessmentItemsWithPage(current, size, categoryId, courseId, classroomId, objectiveId);
            return Result.success(page);
        } catch (RuntimeException e) {
            // 处理业务异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

}
