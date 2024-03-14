package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysmangt/departmentmangt")
public class DepartmentMangtController {
    @Autowired
    private SmObsService smObsService;
    @PostMapping("")
    public Result getDepartmentList(@RequestBody LoginHomeReq loginHomeReq){

        return smObsService.getAllDepartmentList(loginHomeReq);
    }

    /**
     * 写死 需要修改为token获取
     * @param smObs
     * @return
     */
    @PostMapping("/create")
    public Result createOneDepartment(@RequestBody SmObs smObs){
        smObs.setObsdeep(3);
        smObs.setPid("328468276");
        return smObsService.createOneObs(smObs);
    }
}
