package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.mapper.AttainmentEvaluationMapper;
import com.example.smartttevaluation.mapper.CmCoursetargetMapper;
import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.service.CmCoursetargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmCoursetargetServiceImpl implements CmCoursetargetService {

    @Autowired
    private CmCoursetargetMapper cmCoursetargetMapper;
    @Autowired
    private AttainmentEvaluationMapper attainmentEvaluationMapper;
    /**
     *获取课程目标
     */
    @Override
    public Result getCoursetarget(String obsId) {
        // 判断是不是任课教师调用接口
        if(attainmentEvaluationMapper.getCourseByCourseId(obsId) == null) {
            obsId = attainmentEvaluationMapper.getCourseByClassroomId(obsId).getId();
        }

        List<CmCoursetarget> targets = cmCoursetargetMapper.getCoursetarget(obsId);
        for(CmCoursetarget target :targets) {
            List<CmKwadict> kwas = cmCoursetargetMapper.getKwas(target.getId(), obsId);
            target.setKwas(kwas);
        }
        return Result.success(targets);
    }
    /**
     *创建课程目标
     */
    @Override
    public Result createCoursetarget(CmCoursetarget cmCoursetarget) {
        cmCoursetarget.setId(generateEnhancedID("cm_course_target"));
        cmCoursetargetMapper.createCoursetarget(cmCoursetarget, LocalDateTime.now());
        return Result.success();
    }
    /**
     *更新课程目标
     */
    @Override
    public Result updateCoursetarget(CmCoursetarget cmCoursetarget) {
        cmCoursetargetMapper.updateCoursetargetByID(cmCoursetarget);
        return Result.success();
    }
    /**
     *批量删除课程目标
     */
    public Result deleteCoursetargetByIds(List<String> ids){
        cmCoursetargetMapper.deleteCoursetargetByIds(ids);
        cmCoursetargetMapper.deleteKwasByTargetIds(ids);
        return Result.success();
    }

    /**
     * 根据课程目标id和kwaid删除课程目标的kwa
     */
    public Result deleteKwasByTargetIdAndKwaId(CmCoursetarget cmCoursetarget) {
        cmCoursetargetMapper.deleteKwasByTargetIdAndKwaId(cmCoursetarget);
        return Result.success();
    }

    /**
     * 根据课程目标id和kwaid新增课程目标的kwa
     */
    public Result createKwasByTargetIdAndKwaId(CmCoursetarget cmCoursetarget) {
        cmCoursetargetMapper.createKwasByTargetIdAndKwaId(cmCoursetarget);
        return Result.success();
    }
}
