package com.example.smartttevaluation.controller;

import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.service.CmKnowledgeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 知识单元
 */
@RestController
@RequestMapping("/evaluation/knowledgeUnit")

public class KnowledgeUnitController {
    @Autowired
    private CmKnowledgeUnitService cmKnowledgeUnitService;
    //获取知识单元树
    @GetMapping("/getKnowledgeUnitTree")
    @AuthRequired(type = "admin",menu = "531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1",isReadOnly = true)
    public Result getKnowledgeUnitList(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmKnowledgeUnitService.getKnowledgeUnitList(token.getObsid());
    }
    //插入一级目录
    @PostMapping("/insertChapter")
    @AuthRequired(type = "admin",menu = "531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1")
    public Result insertChapter(@RequestBody  CmKnowledgeUnit cmKnowledgeUnit,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmKnowledgeUnit.setCourseid(token.getObsid());
        return cmKnowledgeUnitService.insertChapter(cmKnowledgeUnit);
    }
    //插入二级目录
    @PostMapping("/insertSection")
    @AuthRequired(type = "admin",menu = "531500340-b64043df-c9f1-43a2-b96b-a6134b2953e1")
    public Result insertSection(@RequestBody  CmKnowledgeUnit cmKnowledgeUnit,HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmKnowledgeUnit.setCourseid(token.getObsid());
        return cmKnowledgeUnitService.insertSection(cmKnowledgeUnit);
    }
    //插入知识单元kwa
    @PostMapping("/insertKnowledgeUnitKwa")
    public Result insertKnowledgeUnitKwa(@RequestBody CmKnowledgeUnitKwa cmKnowledgeUnitKwa) {
        return cmKnowledgeUnitService.insertKnowledgeUnitKwa(cmKnowledgeUnitKwa);
    }
//批量删除kwa
    @PostMapping("/deleteKnowledgeUnitKwa")
    public Result deleteKnowledgeUnitKwa(@RequestParam("unitid") String unitid,@RequestBody List<String> kwaids) {
        return cmKnowledgeUnitService.deleteKnowledgeUnitKwa(unitid,kwaids);
    }
    //删除知识单元
    @PostMapping("/deleteKnowledgeUnit")
    public Result deleteKnowledgeUnit(@RequestParam("courseid") String courseid,@RequestBody List<String> unitids) {
        return cmKnowledgeUnitService.deleteKnowledgeUnit(courseid,unitids);
    }
//更新知识单元
    @PostMapping("/updateKnowledgeUnit")
    public Result updateKnowledgeUnit(@RequestBody CmKnowledgeUnit cmKnowledgeUnit) {
        return cmKnowledgeUnitService.updateKnowledgeUnit(cmKnowledgeUnit);
    }
    //更新知识单元kwa
    @PostMapping("/updateKnowledgeUnitKwa")
    public Result updateKnowledgeUnit(@RequestBody CmKnowledgeUnitKwa cmKnowledgeUnitKwa) {
        return cmKnowledgeUnitService.updateKnowledgeUnitKwa(cmKnowledgeUnitKwa);
    }
    /*@GetMapping("/test")
    public Result flashKnowledgeUnitOrdernum() {
        return cmKnowledgeUnitService.flashKnowledgeUnitOrdernum();
    }*/
    @PostMapping("/updateKnowledgeUnitOrdernum")
    public Result updateKnowledgeUnitOrdernum(@RequestParam("preOrdernum") long preOrdernum,@RequestBody CmKnowledgeUnit cmKnowledgeUnit) {
        return cmKnowledgeUnitService.updateKnowledgeUnitOrdernum(cmKnowledgeUnit,preOrdernum);
    }

}
