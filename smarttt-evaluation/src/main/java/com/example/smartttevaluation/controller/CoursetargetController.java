package com.example.smartttevaluation.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Token;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.service.CmCoursetargetService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

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
    @AuthRequired(type = "admin",menu = "531500340-c0220993-26e0-4d21-bc25-f612c67170c5",isReadOnly = true)
    public Result getCoursetarget(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCoursetargetService.getCoursetarget(token.getObsid());
    }
    /**
     *创建课程目标
     */
    @PostMapping("/create")
    public Result createCoursetarget(@RequestBody CmCoursetarget cmCoursetarget) {
        return cmCoursetargetService.createCoursetarget(cmCoursetarget);
    }
    /**
     *删除一个课程目标
     */
    @PostMapping("/delete")
    public Result deleteCoursetargetByID(@RequestBody List<String> ids) {
        return cmCoursetargetService.deleteCoursetargetByID(ids);
    }
    /**
     *批量删除课程目标
     */
    @PostMapping("/deleteCoursetarget")
    public Result deleteCoursetargetByIDs(@RequestBody List<String> ids, @Param("courseid") String courseid, @Param("unitid") String unitid) {
        return cmCoursetargetService.deleteCoursetargetByIDs(ids, courseid, unitid);
    }
    /**
     *插入课程目标的unit
     */
    @PostMapping("/insertCoursetargetUnit")
    public Result insertCoursetargetUnit(@Param("courseid") String courseid, @RequestBody CmCoursetargetUnit cmCoursetargetUnit) {
        return cmCoursetargetService.insertCoursetargetUnit(courseid, cmCoursetargetUnit);
    }
    /**
     *批量删除课程目标的unit
     */
    @PostMapping("/deleteCoursetargetUnit")
    public Result deleteCoursetargetUnit(@RequestParam("unitid") String unitid, @RequestParam("targetid") String targetid) {
        return cmCoursetargetService.deleteCoursetargetUnit(unitid, targetid);
    }
    /**
     *更新课程目标的unit
     */
    @PostMapping("/updateCoursetargetUnit")
    public Result updateCoursetargetUnit(@RequestBody CmCoursetargetUnit cmCoursetargetUnit) {
        return cmCoursetargetService.updateCoursetargetUnit(cmCoursetargetUnit);
    }
    /**
     *更新课程目标
     */
    @PostMapping
    public Result updateCoursetarget(@RequestBody CmCoursetarget cmCoursetarget){
        return cmCoursetargetService.updateCoursetarget(cmCoursetarget);
    }
    /**
     *新增unit
     */
    @PostMapping("/insertunit")
    public Result insertunit(@Param("courseid") String courseid, @Param("ids") List<String> ids){
        return cmCoursetargetService.insertunit(courseid, ids);
    }
}
