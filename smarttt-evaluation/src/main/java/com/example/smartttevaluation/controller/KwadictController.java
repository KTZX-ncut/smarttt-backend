package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.service.CmKwadictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;


/**
 * KWA管理/基本教学目标
 */
@RestController
@RequestMapping("/evaluation/kwadict")
public class KwadictController {
    @Autowired
    private CmKwadictService cmKwadictService;
    /**
     *获取kwa字典
     */
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-c0220993-26e0-4d21-bc25-f612c67170c5",isReadOnly = true)
    public Result getKwadict(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmKwadictService.getKwadict(token.getObsid());
    }
    /**
     *获取kwa
     */
    @GetMapping("/kwa")
    public Result getKwa(@RequestParam(name = "keywordid")String keywordid, @RequestParam(name = "abilityid")String abilityid){
        //通过关键字id和能力id获取kwa
        return cmKwadictService.getKwadictBykeywordidAndabilityid(keywordid,abilityid);
    }
    /**
     *获取关键字字典
     */
    @GetMapping("/getKeyworddict")
    public Result getKeywordDict(@RequestParam(name = "courseid") String courseid) {
        // 通过courseid获取关键字字典
        return cmKwadictService.getKeywordsDict(courseid);
    }
    /**
     *获取能力字典
     */
    @GetMapping("/getabilitydict")
    public Result getAbilityDict(@RequestParam(name = "courseid") String courseid) {
        // 通过courseid获取能力字典
        return cmKwadictService.getAbilityDict(courseid);
    }
    /**
     *通过keywordid查询关键字
     */
    @PostMapping("/getKeyword")
    public boolean getKeywordInfo(@RequestParam(name = "keywordid") String keywordid) {
        // 通过keywordid查询关键字
        return cmKwadictService.getKeywordsByKeywordId(keywordid);
    }
    /**
     *通过abilityid查询能力
     */
    @PostMapping("/getability")
    public boolean getAbilityInfo(@RequestParam(name = "abilityid") String abilityid) {
        // 通过abilityid查询能力
        return cmKwadictService.getAbilityByAbilityId(abilityid);
    }
    /**
     *新增kwa
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin",menu = "531500340-c0220993-26e0-4d21-bc25-f612c67170c5")
    public Result createKwadict(@RequestBody CmKwadict cmKwadict,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmKwadict.setCourseid(token.getObsid());
        return cmKwadictService.createKwadict(cmKwadict);
    }
    /**
     *删除kwa
     */
    @PostMapping("/delete")
    public Result deleteKwadictByID(@RequestBody List<String> ids) {
        return cmKwadictService.deleteKwadictByID(ids);
    }
    /**
     *更新kwa
     */
    @PostMapping
    public Result updateKwadict(@RequestBody CmKwadict cmKwadict){
        return cmKwadictService.updateKwadict(cmKwadict);
    }
}
