package com.example. smartttcourse.controller;

import com.example.smartttcourse.Utils.AuthRequired;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.pojo.CmCourse;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.service.CmCourseService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttcourse.Utils.AuthorizationAspect.getTokenFromContext;


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
    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c",isReadOnly = true)
    public Result getCourse(HttpServletRequest request) {
        Token token = getTokenFromContext();
        String ObsID = token.getObsid();
        return cmCourseService.getCourse(ObsID);
    }

    /**
     * 获取全部学期
     * @return
     */
    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c",isReadOnly = true)
    @GetMapping("/allterm")
    public Result getAllTerm(){

        return null;
    }
    @AuthRequired(type = "admin",menu = "531500340-0ee32ded-100b-4505-95c4-65d5e9b3d93c")
    @GetMapping("/create")
    public Result createCourse(@RequestBody CmCourse cmCourse,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmCourse.setProfessionId(token.getObsid());
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
