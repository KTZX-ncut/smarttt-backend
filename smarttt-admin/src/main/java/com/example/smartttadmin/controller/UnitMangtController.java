package com.example.smartttadmin.controller;


import com.example.smartttadmin.dto.CreateUnitsReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 教学单位管理
 */
@Controller
@RestController
@RequestMapping("/sysmangt/units")
public class UnitMangtController {
    @Autowired
    private SmObsService smObsService;
    @GetMapping
    Result getObsList(){
        return smObsService.getObsTree();
    }
    @PostMapping("/create")
    Result createByTeachingSecretary(@RequestBody CreateUnitsReq createUnitsReq){
        SmObs smObs = createUnitsReq.getSmObs();
        //同级新增
        if(Objects.equals(createUnitsReq.getType(), "1")){
            smObs.setPid(createUnitsReq.getPid());
            smObs.setObsdeep(createUnitsReq.getObsdeep());
        }
        //下级新增
        else {
            smObs.setPid(createUnitsReq.getId());
            smObs.setObsdeep(createUnitsReq.getObsdeep()+1);
        }
        return smObsService.createOneObs(smObs);
    }

    /**
     * 删除一个组织机构
     * 需要修改为列表
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public Result deleteObsByIDs(@RequestBody List<String> ids){
        return smObsService.deleteObssByIDS(ids);
    }
    @GetMapping("/upgrade")
    public Result upgradeOneObs(@RequestParam(name = "id")String id){
        return smObsService.upgradeOneObsByID(id);
    }
}

