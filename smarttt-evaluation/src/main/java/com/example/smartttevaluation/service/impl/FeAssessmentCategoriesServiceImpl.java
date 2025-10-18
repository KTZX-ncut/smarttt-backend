package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttevaluation.dto.AssessmentCategorySearchReq;
import com.example.smartttevaluation.mapper.FeAssessmentCategoriesMapper;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.service.FeAssessmentCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 考核项类别表 Service 实现类
 */
@Service
public class FeAssessmentCategoriesServiceImpl extends ServiceImpl<FeAssessmentCategoriesMapper, FeAssessmentCategories> implements FeAssessmentCategoriesService {

    @Autowired
    private FeAssessmentCategoriesMapper feAssessmentCategoriesMapper;

    @Override
    public boolean addAssessmentCategory(FeAssessmentCategories assessmentCategory) {
        // 字段校验
        if (assessmentCategory == null) {
            throw new RuntimeException("考核项类别对象不能为空");
        }
        
        // 校验必填字段
        if (assessmentCategory.getCourseId() == null || assessmentCategory.getCourseId().trim().isEmpty()) {
            throw new RuntimeException("课程ID不能为空");
        }
        
        if (assessmentCategory.getCategoryName() == null || assessmentCategory.getCategoryName().trim().isEmpty()) {
            throw new RuntimeException("类别名称不能为空");
        }
        
        // 校验分数（必填字段）
        if (assessmentCategory.getScore() == null) {
            throw new RuntimeException("类别分数不能为空");
        }
        
        // 校验分数范围
        if (assessmentCategory.getScore() < 0 || assessmentCategory.getScore() > 100) {
            throw new RuntimeException("类别分数必须在0-100之间");
        }

        // 校验分数（必填字段）
        if (assessmentCategory.getPercent() == null) {
            throw new RuntimeException("百分占比不能为空");
        }

        // 校验分数范围
        if (assessmentCategory.getPercent() < 0 || assessmentCategory.getPercent() > 1) {
            throw new RuntimeException("百分占比必须在0-1之间");
        }
        
        // 设置创建时间和更新时间
        LocalDateTime now = LocalDateTime.now();
        assessmentCategory.setCreatedAt(now);
        assessmentCategory.setUpdatedAt(now);
        
        return save(assessmentCategory);
    }

    @Override
    public Page<FeAssessmentCategories> getByCourseIdWithPage(String courseId, Long current, Long size) {
        if (courseId == null || courseId.isEmpty()) {
            return new Page<>();
        }
        
        // 设置默认值
        if (current == null || current <= 0) {
            current = 1L;
        }
        if (size == null) {
            size = -1L;
        }
        
        Page<FeAssessmentCategories> page = new Page<>(current, size);
        
        // 如果size为-1，表示不分页，返回所有数据
        if (size == -1) {
            List<FeAssessmentCategories> list = feAssessmentCategoriesMapper.selectByCourseIdWithPage(courseId);
            page.setRecords(list);
            page.setTotal(list.size());
            page.setSize(list.size());
            page.setCurrent(1);
            page.setPages(1);
        } else {
            // 分页查询 - 使用数据库层面分页
            Long offset = (current - 1) * size; // 计算偏移量
            List<FeAssessmentCategories> records = feAssessmentCategoriesMapper.selectByCourseIdWithPageAndLimit(courseId, current, size, offset);
            Long total = feAssessmentCategoriesMapper.countByCourseId(courseId);
            
            page.setRecords(records);
            page.setTotal(total);
            page.setCurrent(current);
            page.setSize(size);
            page.setPages((total + size - 1) / size); // 计算总页数
        }
        
        return page;
    }

    @Override
    public List<FeAssessmentCategories> getByCourseId(String courseId) {
        if (courseId == null || courseId.isEmpty()) {
            return new ArrayList<>();
        }
        return feAssessmentCategoriesMapper.selectByCourseIdWithPage(courseId);
    }

    @Override
    public boolean batchDeleteByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        
        int result = feAssessmentCategoriesMapper.batchDeleteByIds(ids);
        return result > 0;
    }

    @Override
    public boolean updateAssessmentCategory(FeAssessmentCategories assessmentCategory) {
        if (assessmentCategory == null || assessmentCategory.getId() == null) {
            return false;
        }
        if(assessmentCategory.getScore() != null){
            if (assessmentCategory.getScore() < 0 || assessmentCategory.getScore() > 100) {
                throw new RuntimeException("类别分数必须在0-100之间");
            }
        }
        if(assessmentCategory.getPercent() != null){
            if (assessmentCategory.getPercent() < 0 || assessmentCategory.getPercent() > 1) {
                throw new RuntimeException("百分占比必须在0-1之间");
            }
        }
        
        // 设置更新时间
        assessmentCategory.setUpdatedAt(LocalDateTime.now());
        
        int result = feAssessmentCategoriesMapper.updateById(assessmentCategory);
        return result > 0;
    }

    @Override
    public Page<FeAssessmentCategories> searchAssessmentCategories(AssessmentCategorySearchReq searchReq) {
        if (searchReq == null) {
            searchReq = new AssessmentCategorySearchReq();
        }
        
        // 设置默认值
        if (searchReq.getCurrent() == null || searchReq.getCurrent() <= 0) {
            searchReq.setCurrent(1L);
        }
        if (searchReq.getSize() == null) {
            searchReq.setSize(-1L);
        }
        if (searchReq.getSortField() == null || searchReq.getSortField().isEmpty()) {
            searchReq.setSortField("createdAt");
        }
        if (searchReq.getSortOrder() == null || searchReq.getSortOrder().isEmpty()) {
            searchReq.setSortOrder("asc");
        }
        
        Page<FeAssessmentCategories> page = new Page<>(searchReq.getCurrent(), searchReq.getSize());
        
        // 如果size为-1，表示不分页，返回所有数据
        if (searchReq.getSize() == -1) {
            List<FeAssessmentCategories> list = feAssessmentCategoriesMapper.searchAssessmentCategories(searchReq, null);
            page.setRecords(list);
            page.setTotal(list.size());
            page.setSize(list.size());
            page.setCurrent(1);
            page.setPages(1);
        } else {
            // 分页查询
            Long offset = (searchReq.getCurrent() - 1) * searchReq.getSize(); // 计算偏移量
            List<FeAssessmentCategories> list = feAssessmentCategoriesMapper.searchAssessmentCategories(searchReq, offset);
            Long total = feAssessmentCategoriesMapper.countSearchAssessmentCategories(searchReq);
            
            page.setRecords(list);
            page.setTotal(total);
            page.setCurrent(searchReq.getCurrent());
            page.setSize(searchReq.getSize());
            page.setPages((total + searchReq.getSize() - 1) / searchReq.getSize());
        }
        
        return page;
    }
}
