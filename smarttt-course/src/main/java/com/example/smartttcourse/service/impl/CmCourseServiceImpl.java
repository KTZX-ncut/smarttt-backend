package com.example.smartttcourse.service.impl;


import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.service.CmCourseService;
import com.example.smartttcourse.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.mapper.CmCourseMapper;

import java.util.List;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@Service
public class CmCourseServiceImpl implements CmCourseService {

    @Autowired
    private CmCourseMapper cmCourseMapper;
    @Autowired
    private CmTermMapper cmTermMapper;

    @Override
    public Result getCourse(Token token) {
        List<SimpleCourse> simpleCourseList = cmCourseMapper.getCourse(token);
        try{
            for(SimpleCourse simpleCourse:simpleCourseList){
                simpleCourse.setTermname(cmCourseMapper.getTermName(simpleCourse.getSchooltermId()));
            }
        }catch (NullPointerException e){
            return Result.error("获取失败");
        }
        return Result.success(simpleCourseList);
    }

    @Override
    public Result createCourse(CmCourse cmCourse) {
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setProfessionName(cmCourseMapper.getObsName(cmCourse.getProfessionId()));
        cmCourseMapper.createCourse(cmCourse);
        return Result.success();
    }

    @Override
    public Result deleteCourseByID(List<String> ids) {
        cmCourseMapper.deleteCourseByID(ids);
        return Result.success();
    }

    @Override
    public Result historyCourseByTerm(String termID, String obsid) {
        return Result.success(cmCourseMapper.historyCourseByTerm(termID,obsid));
    }

    @Override
    public Result copyHistoryCourse(String id) {
        CmCourse cmCourse = cmCourseMapper.getCopyCourse(id);
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setSchooltermId(cmTermMapper.getCurrentTerm());
        cmCourseMapper.createCourse(cmCourse);
        return Result.success();
    }

}

