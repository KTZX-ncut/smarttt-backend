package com.example. smartttcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.service.CmCourseService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coursemangt/course")
public class CourseController {
    @Autowired
    private CmCourseService cmCourseService;

    /**
     *专业负责人课程管理信息
     * @return Result
     */
    @GetMapping
    public Result getCourse() {
        return cmCourseService.getCourse();
    }

    @GetMapping("/create")
    public Result createCourse(@RequestBody CmCourse cmCourse) {
        return cmCourseService.createCourse(cmCourse);
    }

    @GetMapping("/delete")
    public Result deleteCourseByID(@RequestParam(name = "id")String id) {
        return cmCourseService.deleteCourseByID(id);
    }

    @GetMapping("/history")
    public Result historyCourseByTerm(@RequestParam(name = "term")String term) {
        return cmCourseService.historyCourseByTerm(term);
    }

}
