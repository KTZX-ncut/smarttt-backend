package com.example.smartttevaluation.controller;


import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;

import com.example.smartttevaluation.service.CmCoursetargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;


/**
 * 课程目标管理
 */
@RestController
@RequestMapping("/evaluation/coursetarget")
public class CoursetargetController {

    @Autowired
    private CmCoursetargetService cmCoursetargetService;
    /**
     *课程目标列表
     */
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-17a53f15-4a36-4450-abf5-387825a2434a",isReadOnly = true)
    public Result getCoursetarget(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCoursetargetService.getCoursetarget(token.getObsid());
    }
    /**
     *创建课程目标
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-17a53f15-4a36-4450-abf5-387825a2434a")
    public Result createCoursetarget(@RequestBody CmCoursetarget cmCoursetarget,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmCoursetarget.setCourseid(token.getObsid());
        return cmCoursetargetService.createCoursetarget(cmCoursetarget);
    }
    /**
     * 批量删除课程目标
     */
    @PostMapping("/delete")
    @AuthRequired(type = "admin",menu = "531500340-17a53f15-4a36-4450-abf5-387825a2434a")
    public Result deleteCoursetargetByID(@RequestBody List<String> ids, HttpServletRequest request) {
        return cmCoursetargetService.deleteCoursetargetByIds(ids);
    }

    /**
     *更新课程目标
     */
    @PostMapping
    public Result updateCoursetarget(@RequestBody CmCoursetarget cmCoursetarget){
        return cmCoursetargetService.updateCoursetarget(cmCoursetarget);
    }

    /**
     * 根据课程目标id和kwaid新增课程目标的kwa
     */
    @PostMapping("/createKwas")
    @AuthRequired(type = "admin",menu = "531500340-17a53f15-4a36-4450-abf5-387825a2434a")
    public Result createKwas(@RequestBody CmCoursetarget cmCoursetarget, HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmCoursetarget.setCourseid(token.getObsid());
        return cmCoursetargetService.createKwasByTargetIdAndKwaId(cmCoursetarget);
    }

    /**
     * 根据课程目标id和kwaid删除课程目标的kwa
     */
    @PostMapping("/deleteKwas")
    @AuthRequired(type = "admin",menu = "531500340-17a53f15-4a36-4450-abf5-387825a2434a")
    public Result deleteKwas(@RequestBody CmCoursetarget cmCoursetarget, HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmCoursetarget.setCourseid(token.getObsid());
        return cmCoursetargetService.deleteKwasByTargetIdAndKwaId(cmCoursetarget);
    }
}
