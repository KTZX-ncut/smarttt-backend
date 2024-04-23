package com.example.smartttcourse.service;


import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmCourse;

import java.util.List;

public interface CmCourseService {

    Result getCourse(Token token);

    Result createCourse(CmCourse cmCourse);

    Result deleteCourseByID(List<String> ids);

    Result historyCourseByTerm(String termID, String obsid);

    Result copyHistoryCourse(String id);
}

