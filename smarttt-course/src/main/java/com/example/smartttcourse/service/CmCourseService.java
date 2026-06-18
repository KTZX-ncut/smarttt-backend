package com.example.smartttcourse.service;


import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmCourse;

import java.util.List;

public interface CmCourseService {

    Result getCourse(Token token);

    Result createCourse(CmCourse cmCourse);

    Result deleteCourseByID(List<String> ids);

    Result historyCourseByTerm(String termID, String obsid);

    Result copyHistoryCourse(String id);

    Result updateOneCourse(CmCourse cmCourse);

    Result getTeacherCourse(Token token);

    String getTermIdByCourseId(String courseId);

    Integer countByCourseId(String courseIdOrClassroomId);

    Result getPreCourseByCode(String termId, String obsId);

    Result copyInfo(String pastId, String obsId);

    Result copyFormative(String pastId, String obsId);
}

