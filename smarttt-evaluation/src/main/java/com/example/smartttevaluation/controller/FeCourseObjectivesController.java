package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.CourseObjectiveSearchReq;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import com.example.smartttevaluation.service.FeCourseObjectivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 课程目标表 Controller
 */
@RestController
@RequestMapping("/fe/course-objectives")
public class FeCourseObjectivesController {

    @Autowired
    private FeCourseObjectivesService feCourseObjectivesService;

    /**
     * 新增课程目标
     * @param courseObjective 课程目标
     * @return 是否成功
     */
    @PostMapping("/add")
    public Result addCourseObjective(@RequestBody FeCourseObjectives courseObjective) {
        try {
            boolean success = feCourseObjectivesService.addCourseObjective(courseObjective);
            return success ? Result.success() : Result.error("新增课程目标失败");
        } catch (RuntimeException e) {
            // 处理字段校验异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

    /**
     * 根据课程ID查询课程目标列表（支持分页）
     * @param courseId 课程ID
     * @param current 当前页，默认为1
     * @param size 每页大小，默认为10，-1表示不分页
     * @return 分页结果
     */
    @GetMapping("/list")
    public Result getByCourseIdWithPage(
            @RequestParam String courseId,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "-1") Long size) {
        Page<FeCourseObjectives> page = feCourseObjectivesService.getByCourseIdWithPage(courseId, current, size);
        return Result.success(page);
    }

    /**
     * 根据课程ID查询课程目标列表（不分页）
     * @param courseId 课程ID
     * @return 课程目标列表
     */
    @GetMapping("/list/all")
    public Result getByCourseId(@RequestParam String courseId) {
        List<FeCourseObjectives> list = feCourseObjectivesService.getByCourseId(courseId);
        return Result.success(list);
    }

    /**
     * 批量删除课程目标
     * @param ids 目标ID列表
     * @return 是否成功
     */
    @DeleteMapping("/batch")
    public Result batchDeleteByIds(@RequestBody List<String> ids) {
        boolean success = feCourseObjectivesService.batchDeleteByIds(ids);
        return success ? Result.success() : Result.error("批量删除课程目标失败");
    }

    /**
     * 根据ID更新课程目标
     * @param courseObjective 课程目标对象（必须包含id，其他字段可选）
     * @return 是否成功
     */
    @PutMapping("/update")
    public Result updateCourseObjective(@RequestBody FeCourseObjectives courseObjective) {
        try {
            boolean success = feCourseObjectivesService.updateCourseObjective(courseObjective);
            return success ? Result.success() : Result.error("更新课程目标失败");
        } catch (RuntimeException e) {
            // 处理字段校验异常
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 处理其他未知异常
            return Result.error("系统异常，请稍后重试");
        }
    }

    /**
     * 搜索课程目标
     * @param searchReq 搜索条件
     * @return 分页结果
     */
    @PostMapping("/search")
    public Result searchCourseObjectives(@RequestBody CourseObjectiveSearchReq searchReq) {
        Page<FeCourseObjectives> page = feCourseObjectivesService.searchCourseObjectives(searchReq);
        return Result.success(page);
    }

    /**
     * 从其他课程复制课程目标到当前课程（覆盖当前课程已有课程目标）
     * @param pastCourseId 来源课程ID
     * @return 复制结果
     */
    @PostMapping("/copy")
    @AuthRequired
    @Transactional(rollbackFor = Exception.class)
    public Result copyCourseObjectives(@RequestParam("pastCourseId") String pastCourseId) {
        Token token = getTokenFromContext();
        String currentCourseId = token.getObsid();
        feCourseObjectivesService.copyCourseObjectives(pastCourseId, currentCourseId);
        return Result.success("课程目标复制成功");
    }

}
