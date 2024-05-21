package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.dto.CreateCheckitemReq;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.service.CmCheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/coursemangt/checkitem")
public class CheckitemController {

    @Autowired
    private CmCheckitemService cmCheckitemService;

    @GetMapping
    Result getCheckitemList(@RequestParam("courseid") String courseid){
        return cmCheckitemService.getCheckitemTree(courseid);
    }

    @PostMapping("/create")
    Result createByCheckitemSecretary(@RequestBody CreateCheckitemReq createCheckitemReq, @RequestParam("courseid") String courseid){
        CmCheckitem cmCheckitem = createCheckitemReq.getCmCheckitem();
        //同级新增
        if(Objects.equals(createCheckitemReq.getType(), "1")){
            cmCheckitem.setPid(createCheckitemReq.getPid());
            cmCheckitem.setCheckitemdeep(createCheckitemReq.getCheckitemdeep());
        }
        //下级新增
        else {
            cmCheckitem.setPid(createCheckitemReq.getId());
            cmCheckitem.setCheckitemdeep(createCheckitemReq.getCheckitemdeep()+1);
        }
        return cmCheckitemService.createOneCheckitem(cmCheckitem, courseid);
    }

    @PostMapping("/delete")
    public Result deleteCheckitemByIDs(@RequestBody List<String> ids){
        return cmCheckitemService.deleteCheckitemByIDs(ids);
    }

    @GetMapping("/upgrade")
    public Result upgradeOneCheckitem(@RequestParam(name = "id")String id){
        return cmCheckitemService.upgradeOneCheckitemByID(id);
    }
}
