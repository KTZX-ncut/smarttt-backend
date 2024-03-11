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

    /**
     * 人员列表（学生/教师）
     * @param obsid
     * @param catelog
     * @return
     */
    @GetMapping("/person")
    public Result getPersonnelRoster(@RequestParam(name = "obsid")String obsid,@RequestParam(name = "catelog")String catelog){
        return smObsService.getPersonnelRosterByObsIDAndCatelog(obsid,catelog);
    }

    /**
     * 新建教师/学生
     * @param personnelRoster
     * @return
     * 暂定不用修改，因为批量导入文件是后端来处理
     */
    @PostMapping("/create")
    public Result createPersonnelRoster(@RequestBody PersonnelRoster personnelRoster){
        return smObsService.createOnePersonnelRoster(personnelRoster);
    }

}
