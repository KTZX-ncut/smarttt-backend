package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;

import java.util.List;

public interface CmCoursetargetService {
    /**
     *获取课程目标
     */
    Result getCoursetarget(String ObsID);
    /**
     *创建课程目标
     */
    Result createCoursetarget(CmCoursetarget cmCoursetarget);
    /**
     *批量删除课程目标
     */
    Result deleteCoursetargetByIDs(List<String> ids, String courseid, String unitid);
    /**
     *更新课程目标
     */
    Result updateCoursetarget(CmCoursetarget cmCoursetarget);
    /**
     *插入unit
     */
    Result insertunit(String courseid, List<String> ids);
    /**
     *插入课程目标unit
     */
    Result insertCoursetargetUnit(String courseid, CmCoursetargetUnit cmCoursetargetUnit);
    /**
     *删除课程目标unit
     */
    Result deleteCoursetargetUnit(String unitid, String targetid);
    /**
     *更新课程目标unit
     */
    Result updateCoursetargetUnit(CmCoursetargetUnit cmCoursetargetUnit);
    /**
     *删除课程目标unit
     */
    Result deleteCoursetargetByID(List<String> ids);
}
