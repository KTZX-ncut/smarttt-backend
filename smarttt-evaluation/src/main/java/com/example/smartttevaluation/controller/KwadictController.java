package com.example.smartttevaluation.controller;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.service.CmKwadictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * KWA管理
 */
@RestController
@RequestMapping("/evaluation/kwadict")
public class KwadictController {
    @Autowired
    private CmKwadictService cmKwadictService;
    @GetMapping
    public Result getKwadict() {
        //获取整个表格信息
        return cmKwadictService.getKwadict();
    }

    @GetMapping("/kwa")
    public Result getKwa(@RequestParam(name = "keywordid")String keywordid, @RequestParam(name = "abilityid")String abilityid){
        //通过关键字id和能力id获取kwa
        return cmKwadictService.getKwadictBykeywordidAndabilityid(keywordid,abilityid);
    }

    @GetMapping("/getKeyworddict")
    public Result getKeywordDict(@RequestParam(name = "courseid") String courseid) {
        // 通过courseid获取关键字字典
        return cmKwadictService.getKeywordsDict(courseid);
    }

    @GetMapping("/getabilitydict")
    public Result getAbilityDict(@RequestParam(name = "courseid") String courseid) {
        // 通过courseid获取能力字典
        return cmKwadictService.getAbilityDict(courseid);
    }

    @PostMapping("/getKeyword")
    public boolean getKeywordInfo(@RequestParam(name = "keywordid") String keywordid) {
        // 通过keywordid查询关键字
        return cmKwadictService.getKeywordsByKeywordId(keywordid);
    }

    @PostMapping("/getability")
    public boolean getAbilityInfo(@RequestParam(name = "abilityid") String abilityid) {
        // 通过abilityid查询能力
        return cmKwadictService.getAbilityByAbilityId(abilityid);
    }

    @PostMapping("/create")
    public Result createKwadict(@RequestBody CmKwadict cmKwadict) {
        return cmKwadictService.createKwadict(cmKwadict);
    }

    @PostMapping("/delete")
    public Result deleteKwadictByID(@RequestBody List<String> ids) {
        return cmKwadictService.deleteKwadictByID(ids);
    }

    @PostMapping
    public Result updateKwadict(@RequestBody CmKwadict cmKwadict){
        return cmKwadictService.updateKwadict(cmKwadict);
    }
}
