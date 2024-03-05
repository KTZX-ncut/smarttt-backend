package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysmangt/personnelmangt")
public class PersonnelMangtController {
    @Autowired
    private SmObsService smObsService;
    @GetMapping
    public Result getSmObsTree(){
        return smObsService.getObsTree();
    }
    @GetMapping("/person")
    public Result getPersonnelRoster(@RequestParam(name = "obsid")String obsid,@RequestParam(name = "catelog")String catelog){
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,catelog);
    }
    @PostMapping("/create")
    public Result createPersonnelRoster(@RequestBody PersonnelRoster personnelRoster){
        return smObsService.createOnePersonnelRoster(personnelRoster);
    }

}
