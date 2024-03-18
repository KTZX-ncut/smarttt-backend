package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysmangt/department")
public class DepartmentMangtController {
    @Autowired
    private SmObsService smObsService;
    @Autowired
    private StUsersService stUsersService;
    @PostMapping("")
    public Result getDepartmentList(@RequestBody LoginHomeReq loginHomeReq){

        return smObsService.getAllObsList(loginHomeReq);
    }

    /**
     * 写死，挂在信息学院，需要修改为token获取
     * @param smObs
     * @return
     */
    @PostMapping("/create")
    public Result createOneDepartment(@RequestBody SmObs smObs){
        smObs.setObsdeep(3);
        smObs.setPid("328468276");
        return smObsService.createOneObs(smObs);
    }
    @PostMapping("/delete")
    public Result deleteDepartments(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
    @GetMapping("/resp")
    public Result getDepartmentRP(@RequestParam(name = "id")String id){
        return stUsersService.getObsRP(id);
    }
}
