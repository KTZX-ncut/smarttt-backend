package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StLevel;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysmangt/schoolmangt")
public class SchoolMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StLevelService stLevelService;
    @GetMapping("")
    public Result getSchoolInformation(){
        return stLevelService.getSchoolInfor();
    }
    @PostMapping("")
    public Result updateSchoolInfor(@RequestBody SmObs smObs){
        SmObs dealSmObs = new SmObs(smObs);
        return smObsService.updateOneObsByID(dealSmObs);
    }
    @PostMapping("/update")
    public Result updateSchoolLevel(@RequestBody StLevel stLevel){
        return stLevelService.updateLevel(stLevel);
    }

}
