package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysmangt/collegemangt")
public class CollageMangtController {
    @Autowired
    private SmObsService smObsService;
    @GetMapping
    public Result getCollegeList(){
        return smObsService.getAllCollageList();
    }

    /**
     * 新建一个学院，先调用新建机构方法
     * @param smObs 学院
     * @return ...
     * 后续需要把每个字段都补全（修改）
     */
    @PostMapping
    public Result createNewCollege(@RequestBody SmObs smObs){
        smObs.setObsdeep(2);
        smObs.setPid("237675254");
        return smObsService.createOneObs(smObs);
    }

    /**
     * 删除学院
     * @param ids id列表
     * @return Result
     */
    @PostMapping("/delete")
    public Result deleteCollegeByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
}
