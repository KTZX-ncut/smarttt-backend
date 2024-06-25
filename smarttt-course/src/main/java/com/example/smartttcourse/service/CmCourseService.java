package com.example.smartttcourse.service;


import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmCourse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CmCourseService {

    Result getCourse(Token token);

    Result createCourse(CmCourse cmCourse);

    Result deleteCourseByID(List<String> ids);

    Result historyCourseByTerm(String termID, String obsid);

    Result copyHistoryCourse(String id);

    Result updateOneCourse(CmCourse cmCourse);

    Result uploadfile(MultipartFile file, String uploadDir) throws IOException;

    Result downloadfile(String fileName, String uploadDir);

    Result getInstructionalProgram(String obsid);


    Result updateTeachingProgram(String string, String obsid);
}

