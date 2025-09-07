package com.example.smartttevaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttevaluation.dto.AssessmentItemDto;
import com.example.smartttevaluation.pojo.FeAssessmentItems;

import java.util.List;
import java.util.Map;

/**
 * 考核项表 Service 接口
 */
public interface FeAssessmentItemsService extends IService<FeAssessmentItems> {

    /**
     * 根据课堂ID查询考核项信息
     * @param classroomId 课堂ID
     * @return 包含试卷和实验信息的Map
     */
    Map<String, List<AssessmentItemDto>> getAssessmentItemsByClassroomId(String classroomId);

    /**
     * 批量添加考核项
     * @param assessmentItems 考核项列表
     * @return 是否成功
     */
    boolean batchAddAssessmentItems(List<FeAssessmentItems> assessmentItems);

    /**
     * 批量删除考核项
     * @param ids 考核项ID列表
     * @return 是否成功
     */
    boolean batchDeleteByIds(List<String> ids);

    /**
     * 分页查询考核项
     * @param current 当前页码
     * @param size 每页大小
     * @param categoryId 类别ID
     * @param courseId 课程ID
     * @param classroomId 课堂ID
     * @param objectiveId 目标ID
     * @return 分页结果
     */
    com.baomidou.mybatisplus.extension.plugins.pagination.Page<FeAssessmentItems> getAssessmentItemsWithPage(Long current, Long size, 
                                                                                                            String categoryId, String courseId, 
                                                                                                            String classroomId, String objectiveId);
}
