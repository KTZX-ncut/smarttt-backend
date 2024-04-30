package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.service.CmCoursetargetService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程目标管理
 */
@RestController
@RequestMapping("/evaluation/coursetarget")
public class CoursetargetController {

    @Autowired
    private CmCoursetargetService cmCoursetargetService;
    @GetMapping
    public Result getCoursetarget() {
        return cmCoursetargetService.getCoursetarget();
    }

    @PostMapping("/create")
    public Result createCoursetarget(@RequestBody CmCoursetarget cmCoursetarget) {
        return cmCoursetargetService.createCoursetarget(cmCoursetarget);
    }

    @PostMapping("/delete")
    public Result deleteCoursetargetByID(@RequestBody List<String> ids) {
        return cmCoursetargetService.deleteCoursetargetByID(ids);
    }

    @PostMapping
    public Result updateCoursetarget(@RequestBody CmCoursetarget cmCoursetarget){
        return cmCoursetargetService.updateCoursetarget(cmCoursetarget);
    }

    @PostMapping("/insertunit")
    public Result insertunit(@Param("courseid") String courseid, @Param("ids") List<String> ids){
        return cmCoursetargetService.insertunit(courseid, ids);
    }
}
