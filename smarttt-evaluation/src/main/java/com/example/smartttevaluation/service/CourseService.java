package com.example.smartttevaluation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttevaluation.pojo.CmCourse;

/**
 * @author lunSir
 * @create 2024-09-25 15:10
 */
public interface CourseService extends IService<CmCourse>{
    String getProIdByCourseId(String courseId);
}
