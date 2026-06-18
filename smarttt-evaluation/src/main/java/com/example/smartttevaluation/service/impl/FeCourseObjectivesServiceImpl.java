package com.example.smartttevaluation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttevaluation.dto.CourseObjectiveSearchReq;
import com.example.smartttevaluation.mapper.FeCourseObjectivesMapper;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import com.example.smartttevaluation.service.FeCourseObjectivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.hutool.core.util.IdUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程目标表 Service 实现类
 */
@Service
public class FeCourseObjectivesServiceImpl extends ServiceImpl<FeCourseObjectivesMapper, FeCourseObjectives> implements FeCourseObjectivesService {

    @Autowired
    private FeCourseObjectivesMapper feCourseObjectivesMapper;

    @Override
    public boolean addCourseObjective(FeCourseObjectives courseObjective) {
        // 字段校验
        if (courseObjective == null) {
            throw new RuntimeException("课程目标对象不能为空");
        }
        
        // 校验必填字段
        if (courseObjective.getCourseId() == null || courseObjective.getCourseId().trim().isEmpty()) {
            throw new RuntimeException("课程ID不能为空");
        }
        
        if (courseObjective.getObjectiveName() == null || courseObjective.getObjectiveName().trim().isEmpty()) {
            throw new RuntimeException("目标名称不能为空");
        }
        
        if (courseObjective.getWeight() == null) {
            throw new RuntimeException("权重不能为空");
        }
        
        // 校验权重范围（0-1之间）
        if (courseObjective.getWeight().compareTo(BigDecimal.ZERO) < 0 || 
            courseObjective.getWeight().compareTo(BigDecimal.ONE) > 0) {
            throw new RuntimeException("权重必须在0-1之间");
        }
        
        // 设置创建时间和更新时间
        courseObjective.setCreatedAt(LocalDateTime.now());
        courseObjective.setUpdatedAt(LocalDateTime.now());
        
        // 如果没有设置排序，则设置为0
        if (courseObjective.getSortOrder() == null) {
            courseObjective.setSortOrder(0);
        }
        
        return save(courseObjective);
    }

    @Override
    public Page<FeCourseObjectives> getByCourseIdWithPage(String courseId, Long current, Long size) {
        // 字段校验
        if (StrUtil.isBlank(courseId)) {
            throw new RuntimeException("课程ID不能为空");
        }
        
        // 设置默认值
        if (current == null || current < 1) {
            current = 1L;
        }
        if (size == null || size < 1) {
            size = -1L; // -1表示不分页，返回所有数据
        }

        Page<FeCourseObjectives> page = new Page<>(current, size);

        // 如果size为-1，表示不分页，返回所有数据
        if (size == -1) {
            List<FeCourseObjectives> list = feCourseObjectivesMapper.selectByCourseIdWithPage(courseId);
            page.setRecords(list);
            page.setTotal(list.size());
            page.setSize(list.size());
            page.setCurrent(1);
            page.setPages(1);
        } else {
            // 分页查询 - 使用自定义方法
            Long offset = (current - 1) * size; // 计算偏移量
            List<FeCourseObjectives> records = feCourseObjectivesMapper.selectByCourseIdWithPageAndLimit(courseId, current, size, offset);
            Long total = feCourseObjectivesMapper.countByCourseId(courseId);
            
            page.setRecords(records);
            page.setTotal(total);
            page.setCurrent(current);
            page.setSize(size);
            page.setPages((total + size - 1) / size); // 计算总页数
        }
        
        return page;
    }

    @Override
    public List<FeCourseObjectives> getByCourseId(String courseId) {
        return feCourseObjectivesMapper.selectByCourseIdWithPage(courseId);
    }

    @Override
    public boolean batchDeleteByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        
        int result = feCourseObjectivesMapper.batchDeleteByIds(ids);
        return result > 0;
    }

    @Override
    public boolean updateCourseObjective(FeCourseObjectives courseObjective) {
        if (courseObjective == null || courseObjective.getId() == null) {
            return false;
        }

        if (courseObjective.getWeight() != null){
            // 校验权重范围（0-1之间）
            if (courseObjective.getWeight().compareTo(BigDecimal.ZERO) < 0 ||
                    courseObjective.getWeight().compareTo(BigDecimal.ONE) > 0) {
                throw new RuntimeException("权重必须在0-1之间");
            }
        }
        
        // 设置更新时间
        courseObjective.setUpdatedAt(LocalDateTime.now());
        
        int result = feCourseObjectivesMapper.updateById(courseObjective);
        return result > 0;
    }

    @Override
    public Page<FeCourseObjectives> searchCourseObjectives(CourseObjectiveSearchReq searchReq) {
        if (searchReq == null) {
            searchReq = new CourseObjectiveSearchReq();
        }
        
        // 设置默认值
        if (searchReq.getCurrent() == null || searchReq.getCurrent() <= 0) {
            searchReq.setCurrent(1L);
        }
        if (searchReq.getSize() == null) {
            searchReq.setSize(-1L);
        }
        if (searchReq.getSortField() == null || searchReq.getSortField().isEmpty()) {
            searchReq.setSortField("sortOrder");
        }
        if (searchReq.getSortOrder() == null || searchReq.getSortOrder().isEmpty()) {
            searchReq.setSortOrder("asc");
        }
        
        Page<FeCourseObjectives> page = new Page<>(searchReq.getCurrent(), searchReq.getSize());
        
        // 如果size为-1，表示不分页，返回所有数据
        if (searchReq.getSize() == -1) {
            List<FeCourseObjectives> list = feCourseObjectivesMapper.searchCourseObjectives(searchReq, null);
            page.setRecords(list);
            page.setTotal(list.size());
            page.setSize(list.size());
            page.setCurrent(1);
            page.setPages(1);
        } else {
            // 分页查询
            Long offset = (searchReq.getCurrent() - 1) * searchReq.getSize(); // 计算偏移量
            List<FeCourseObjectives> list = feCourseObjectivesMapper.searchCourseObjectives(searchReq, offset);
            Long total = feCourseObjectivesMapper.countSearchCourseObjectives(searchReq);
            
            page.setRecords(list);
            page.setTotal(total);
            page.setCurrent(searchReq.getCurrent());
            page.setSize(searchReq.getSize());
            page.setPages((total + searchReq.getSize() - 1) / searchReq.getSize());
        }

        return page;
    }

    @Override
    public Map<String, String> copyCourseObjectives(String pastCourseId, String currentCourseId) {
        LambdaQueryWrapper<FeCourseObjectives> delWrapper = new LambdaQueryWrapper<>();
        delWrapper.eq(FeCourseObjectives::getCourseId, currentCourseId);
        remove(delWrapper);

        LambdaQueryWrapper<FeCourseObjectives> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FeCourseObjectives::getCourseId, pastCourseId);
        List<FeCourseObjectives> pastList = list(queryWrapper);

        Map<String, String> idMap = new HashMap<>();
        for (FeCourseObjectives obj : pastList) {
            String oldId = obj.getId();
            String newId = IdUtil.fastSimpleUUID();
            idMap.put(oldId, newId);
            obj.setId(newId);
            obj.setCourseId(currentCourseId);
            obj.setCreatedAt(LocalDateTime.now());
            obj.setUpdatedAt(LocalDateTime.now());
            save(obj);
        }
        return idMap;
    }
}
