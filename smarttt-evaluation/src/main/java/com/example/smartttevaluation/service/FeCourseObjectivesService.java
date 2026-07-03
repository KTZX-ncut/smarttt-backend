package com.example.smartttevaluation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttevaluation.dto.CourseObjectiveSearchReq;
import com.example.smartttevaluation.pojo.FeCourseObjectives;

import java.util.List;

/**
 * 课程目标表 Service 接口
 */
public interface FeCourseObjectivesService extends IService<FeCourseObjectives> {

    /**
     * 新增课程目标
     * @param courseObjective 课程目标
     * @return 是否成功
     */
    boolean addCourseObjective(FeCourseObjectives courseObjective);

    /**
     * 根据课程ID查询课程目标列表（支持分页）
     * @param courseId 课程ID
     * @param current 当前页
     * @param size 每页大小
     * @return 分页结果
     */
    Page<FeCourseObjectives> getByCourseIdWithPage(String courseId, Long current, Long size);

    /**
     * 根据课程ID查询课程目标列表（不分页）
     * @param courseId 课程ID
     * @return 课程目标列表
     */
    List<FeCourseObjectives> getByCourseId(String courseId);

    /**
     * 批量删除课程目标
     * @param ids 目标ID列表
     * @return 是否成功
     */
    boolean batchDeleteByIds(List<String> ids);

    /**
     * 根据ID更新课程目标
     * @param courseObjective 课程目标对象（必须包含id，其他字段可选）
     * @return 是否成功
     */
    boolean updateCourseObjective(FeCourseObjectives courseObjective);

    /**
     * 搜索课程目标
     * @param searchReq 搜索条件
     * @return 分页结果
     */
    Page<FeCourseObjectives> searchCourseObjectives(CourseObjectiveSearchReq searchReq);

    /**
     * 复制历史课程目标到当前课程，返回 oldId->newId 映射
     */
    java.util.Map<String, String> copyCourseObjectives(String pastCourseId, String currentCourseId);
}
