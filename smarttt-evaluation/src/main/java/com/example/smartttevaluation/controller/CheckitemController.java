package com.example.smartttevaluation.controller;



import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.CreateCheckitemReq;
import com.example.smartttevaluation.dto.Result;
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
     *考核项列表
     */
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-c0220993-26e0-4d21-bc25-f612c67170c5",isReadOnly = true)
    public Result getCheckitemList(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmCheckitemService.getCheckitemList(token.getObsid());
    }
    /**
     *新增一个考核项
     */
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
    /**
     *删除一个考核项
     */
    @PostMapping("/delete")
    public Result deleteCheckitemByIDs(@RequestBody List<String> ids){
        return cmCheckitemService.deleteCheckitemByIDs(ids);
    }
    /**
     *更新一个考核项
     */
    @GetMapping("/upgrade")
    public Result upgradeOneCheckitem(@RequestParam(name = "id")String id){
        return cmCheckitemService.upgradeOneCheckitemByID(id);
    }
}
