package com.example.smartttcourse.service.impl;


import com.example.smartttcourse.service.CmCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.mapper.CmCourseMapper;
import com.example.smartttcourse.pojo.CmCourse;

import static com.example.smartttcourse.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmCourseServiceImpl implements CmCourseService {

    @Autowired
    private CmCourseMapper cmCourseMapper;

    @Override
    public Result getCourse() {
        return Result.success(cmCourseMapper.getCourse());
    }

    @Override
    public Result createCourse(CmCourse cmCourse) {
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setCreatetime(LocalDateTime.now().toString());
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

