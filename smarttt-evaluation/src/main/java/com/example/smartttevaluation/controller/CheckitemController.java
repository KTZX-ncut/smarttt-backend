package com.example.smartttevaluation.controller;



import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.CreateCheckitemReq;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmCheckitem;
import com.example.smartttevaluation.service.CmCheckitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;


@RestController
@RequestMapping("/evaluation/checkitem")
public class CheckitemController {

    @Autowired
    private CmCheckitemService cmCheckitemService;
    /**
     *考核项设计
     */
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16",isReadOnly = true)
    public Result getCheckitemList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCheckitemService.getCheckitemList(token);
    }
    /**
     *新增一个考核项
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16")
    Result createByCheckitemSecretary(@RequestBody CreateCheckitemReq createCheckitemReq,HttpServletRequest request){
        Token token = getTokenFromContext();
        String courseid = token.getObsid();
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
    /**
     *删除考核项列表
     */
    @PostMapping("/delete")
    @AuthRequired(type = "admin",menu = "531500340-4d66eeda-f440-4994-8998-bc2fcaba3c16")
    public Result deleteCheckitemByIDs(@RequestParam List<String> ids, HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCheckitemService.deleteCheckitemByIDs(ids, token.getObsid());
    }
    /**
     *更新一个考核项
     */
    @PostMapping("/upgrade")
    public Result upgradeOneCheckitem(@RequestParam(name = "id")String id){
        return cmCheckitemService.upgradeOneCheckitemByID(id);
    }

    @PostMapping("/setTaskTrue")
    public Result changeCheckitemTaskTrue(@RequestParam String id){
        return cmCheckitemService.changeCheckitemTaskTrue(id);
    }

    @PostMapping("/setTaskFalse")
    public Result changeCheckitemTaskFalse(@RequestParam List<String> ids){
        return cmCheckitemService.changeCheckitemTaskFalse(ids);
    }

    @PostMapping("/changeName")
    public Result changeCheckitemName(@RequestParam String id,@RequestParam String name){
        return cmCheckitemService.changeCheckitemName(id, name);
    }

    @PostMapping("/changeRemark")
    public Result changeCheckitemRemark(@RequestParam String id,@RequestParam String remark){
        return cmCheckitemService.changeCheckitemRemark(id, remark);
    }
}
