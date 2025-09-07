package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttevaluation.dto.AssessmentItemDto;
import com.example.smartttevaluation.mapper.FeAssessmentItemsMapper;
import com.example.smartttevaluation.pojo.FeAssessmentItems;
import com.example.smartttevaluation.service.FeAssessmentItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考核项表 Service 实现类
 */
@Service
public class FeAssessmentItemsServiceImpl extends ServiceImpl<FeAssessmentItemsMapper, FeAssessmentItems> implements FeAssessmentItemsService {

    @Autowired
    private FeAssessmentItemsMapper feAssessmentItemsMapper;

    @Override
    public Map<String, List<AssessmentItemDto>> getAssessmentItemsByClassroomId(String classroomId) {
        if (classroomId == null || classroomId.isEmpty()) {
            Map<String, List<AssessmentItemDto>> result = new HashMap<>();
            result.put("testPaper", new ArrayList<>());
            result.put("practice", new ArrayList<>());
            return result;
        }

        // 查询试卷信息（作业和考试）
        List<AssessmentItemDto> testPapers = feAssessmentItemsMapper.selectTestPapersByClassroomId(classroomId);
        
        // 查询实验信息
        List<AssessmentItemDto> practices = feAssessmentItemsMapper.selectPracticesByClassroomId(classroomId);

        // 封装返回结果
        Map<String, List<AssessmentItemDto>> result = new HashMap<>();
        result.put("testPaper", testPapers);
        result.put("practice", practices);
        
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchAddAssessmentItems(List<FeAssessmentItems> assessmentItems) {
        if (CollectionUtils.isEmpty(assessmentItems)) {
            return false;
        }

        // 验证必填字段
        for (FeAssessmentItems item : assessmentItems) {
            if (item.getCategoryId() == null || item.getCategoryId().isEmpty() ||
                item.getCourseId() == null || item.getCourseId().isEmpty() ||
                item.getClassroomId() == null || item.getClassroomId().isEmpty() ||
                item.getItemName() == null || item.getItemName().isEmpty() ||
                item.getItemType() == null || item.getItemType().isEmpty() ||
                item.getObjectiveId() == null || item.getObjectiveId().isEmpty() ||
                item.getTypeId() == null || item.getTypeId().isEmpty()) {
                throw new RuntimeException("必填字段不能为空");
            }
        }

        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        for (FeAssessmentItems item : assessmentItems) {
            item.setCreatedAt(now);
            item.setUpdatedAt(now);
        }

        try {
            return saveBatch(assessmentItems);
        } catch (DuplicateKeyException e) {
            // 处理唯一约束冲突，事务会自动回滚
            throw new RuntimeException("考核项已存在，请检查类别ID、课程ID、课堂ID和类型ID的组合是否重复", e);
        } catch (Exception e) {
            // 处理其他异常，事务会自动回滚
            throw new RuntimeException("批量添加考核项失败: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean batchDeleteByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        
        try {
            int result = feAssessmentItemsMapper.batchDeleteByIds(ids);
            return result > 0;
        } catch (Exception e) {
            throw new RuntimeException("批量删除考核项失败: " + e.getMessage(), e);
        }
    }

    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<FeAssessmentItems> getAssessmentItemsWithPage(Long current, Long size, 
                                                                                                                    String categoryId, String courseId, 
                                                                                                                    String classroomId, String objectiveId) {
        // 设置默认值
        if (current == null || current < 1) {
            current = 1L;
        }
        if (size == null || size < 1) {
            size = -1L; // -1表示不分页，返回所有数据
        }

        // 创建分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<FeAssessmentItems> page = 
            new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current, size);

        try {
            // 计算偏移量
            Long offset = null;
            if (current > 1 && size > 0) {
                offset = size * (current - 1);
            }
            
            // 查询数据
            List<FeAssessmentItems> records = feAssessmentItemsMapper.selectWithPage(current, size, offset, categoryId, courseId, classroomId, objectiveId);
            page.setRecords(records);

            // 如果size为-1（不分页），设置总数为实际记录数
            if (size == -1) {
                page.setTotal(records.size());
                page.setSize(records.size());
                page.setCurrent(1);
            } else {
                // 分页情况下查询总数
                Long total = feAssessmentItemsMapper.countAll(categoryId, courseId, classroomId, objectiveId);
                page.setTotal(total);
            }

            return page;
        } catch (Exception e) {
            throw new RuntimeException("查询考核项失败: " + e.getMessage(), e);
        }
    }
}
