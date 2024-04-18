package com.example.smartttcourse.service;


import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.pojo.CmCourse;

public interface CmCourseService {

    Result getCourse(String obsID);

    Result createCourse(CmCourse cmCourse);

    Result deleteCourseByID(String id);

    Result historyCourseByTerm(String term);
}

