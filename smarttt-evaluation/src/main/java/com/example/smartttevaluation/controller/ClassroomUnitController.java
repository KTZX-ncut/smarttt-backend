package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmClassroomUnit;
import com.example.smartttevaluation.pojo.CmClassroomUnitKwa;
import com.example.smartttevaluation.service.CmClassroomUnitService;
import com.example.smartttevaluation.service.CmGetabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation/classroomUnit")

public class ClassroomUnitController {
    @Autowired
    private CmClassroomUnitService cmClassroomUnitService;
    @GetMapping
    public Result getClassroomUnitList(@RequestParam("courseid") String courseid) {
        return cmClassroomUnitService.getClassroomUnitList(courseid);
    }
    @PostMapping("/insertChapter")
    public Result insertChapter(@RequestBody  CmClassroomUnit cmClassroomUnit) {
        return cmClassroomUnitService.insertChapter(cmClassroomUnit);
    }

    @PostMapping("/insertSection")
    public Result insertSection(@RequestBody  CmClassroomUnit cmClassroomUnit) {
        return cmClassroomUnitService.insertSection(cmClassroomUnit);
    }

    @PostMapping("/insertClassroomUnitKwa")
    public Result insertClassroomUnitKwa(@RequestBody CmClassroomUnitKwa cmClassroomUnitkwa) {
        return cmClassroomUnitService.insertClassroomUnitKwa(cmClassroomUnitkwa);
    }

    @PostMapping("/deleteClassroomUnitKwa")
    public Result deleteClassroomUnitKwa(@RequestParam("unitid") String unitid,@RequestBody List<String> kwaids) {
        return cmClassroomUnitService.deleteClassroomUnitKwa(unitid,kwaids);
    }

    @PostMapping("/deleteSection")
    public Result deleteSection(@RequestBody List<String> ids) {
        return cmClassroomUnitService.deleteSection(ids);
    }
    @PostMapping("/deleteChapter")
    public Result deleteChapter(@RequestBody String id) {
        return cmClassroomUnitService.deleteChapter(id);
    }
}
