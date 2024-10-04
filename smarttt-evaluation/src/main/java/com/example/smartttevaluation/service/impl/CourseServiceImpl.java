package com.example.smartttevaluation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttevaluation.mapper.CourseMapper;
import com.example.smartttevaluation.pojo.CmCourse;
import com.example.smartttevaluation.service.CourseService;
import org.springframework.stereotype.Service;

/**
 * @author lunSir
 * @create 2024-09-25 15:10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CmCourse> implements CourseService {
    @Override
    public String getProIdByCourseId(String courseId) {
        return baseMapper.getProIdByCourseId(courseId);
    }
}
