package com.example.smartttcourse.service.impl;


import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.service.CmCourseService;
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

    @Override
    public Result getCourse(String obsID) {
        List<SimpleCourse> simpleCourseList = cmCourseMapper.getCourse(obsID);
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
        cmCourseMapper.createCourse(cmCourse);
        return Result.success();
    }

    @Override
    public Result deleteCourseByID(String id) {
        cmCourseMapper.deleteCourseByID(id);
        return Result.success();
    }

    @Override
    public Result historyCourseByTerm(String term) {
        return Result.success(cmCourseMapper.historyCourseByTerm(term));
    }

}

