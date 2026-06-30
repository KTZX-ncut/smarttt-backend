package com.example.smartttevaluation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttevaluation.dto.AssessmentCategorySearchReq;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;

import java.util.List;

/**
 * 考核项类别表 Service 接口
 */
public interface FeAssessmentCategoriesService extends IService<FeAssessmentCategories> {

    /**
     * 添加考核项类别
     * @param assessmentCategory 考核项类别对象
     * @return 是否成功
     */
    boolean addAssessmentCategory(FeAssessmentCategories assessmentCategory);

    /**
     * 根据课程ID查询考核项类别（分页）
     * @param courseId 课程ID
     * @param current 当前页
     * @param size 每页大小
     * @return 分页结果
     */
    Page<FeAssessmentCategories> getByCourseIdWithPage(String courseId, Long current, Long size);

    /**
     * 根据课程ID查询考核项类别（不分页）
     * @param courseId 课程ID
     * @return 考核项类别列表
     */
    List<FeAssessmentCategories> getByCourseId(String courseId);

    /**
     * 批量删除考核项类别
     * @param ids 类别ID列表
     * @return 是否成功
     */
    boolean batchDeleteByIds(List<String> ids);

    /**
     * 根据ID更新考核项类别
     * @param assessmentCategory 考核项类别对象（必须包含id，其他字段可选）
     * @return 是否成功
     */
    boolean updateAssessmentCategory(FeAssessmentCategories assessmentCategory);

    /**
     * 搜索考核项类别
     * @param searchReq 搜索条件
     * @return 分页结果
     */
    Page<FeAssessmentCategories> searchAssessmentCategories(AssessmentCategorySearchReq searchReq);
}
