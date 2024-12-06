package com.example.smartttevaluation.service;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;

import java.util.List;

public interface CmCoursetargetService {
    /**
     * 获取课程目标
     */
    Result getCoursetarget(String obsId);

    /**
     * 创建课程目标
     */
    Result createCoursetarget(CmCoursetarget cmCoursetarget);

    /**
     * 更新课程目标
     */
    Result updateCoursetarget(CmCoursetarget cmCoursetarget);

    /**
     * 批量删除课程目标
     */
    Result deleteCoursetargetByIds(List<String> ids);

    /**
     * 根据课程目标id和kwaid新增课程目标的kwa
     */
    Result createKwasByTargetIdAndKwaId(CmCoursetarget cmCoursetarget);

    /**
     * 根据课程目标id和kwaid删除课程目标的kwa
     */
    Result deleteKwasByTargetIdAndKwaId(CmCoursetarget cmCoursetarget);
}
