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

    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-c0220993-26e0-4d21-bc25-f612c67170c5",isReadOnly = true)
    public Result getCoursetarget(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCoursetargetService.getCoursetarget(token.getObsid());
    }

    @PostMapping("/create")
    public Result createCoursetarget(@RequestBody CmCoursetarget cmCoursetarget) {
        return cmCoursetargetService.createCoursetarget(cmCoursetarget);
    }

    @PostMapping("/delete")
    public Result deleteCoursetargetByID(@RequestBody List<String> ids) {
        return cmCoursetargetService.deleteCoursetargetByID(ids);
    }

    @PostMapping("/deleteCoursetarget")
    public Result deleteCoursetargetByIDs(@RequestBody List<String> ids, @Param("courseid") String courseid, @Param("unitid") String unitid) {
        return cmCoursetargetService.deleteCoursetargetByIDs(ids, courseid, unitid);
    }

    //插入unit
    @PostMapping("/insertCoursetargetUnit")
    public Result insertCoursetargetUnit(@Param("courseid") String courseid, @RequestBody CmCoursetargetUnit cmCoursetargetUnit) {
        return cmCoursetargetService.insertCoursetargetUnit(courseid, cmCoursetargetUnit);
    }

    //批量删除unit
    @PostMapping("/deleteCoursetargetUnit")
    public Result deleteCoursetargetUnit(@RequestParam("unitid") String unitid, @RequestParam("targetid") String targetid) {
        return cmCoursetargetService.deleteCoursetargetUnit(unitid, targetid);
    }

    //更新unit
    @PostMapping("/updateCoursetargetUnit")
    public Result updateCoursetargetUnit(@RequestBody CmCoursetargetUnit cmCoursetargetUnit) {
        return cmCoursetargetService.updateCoursetargetUnit(cmCoursetargetUnit);
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
