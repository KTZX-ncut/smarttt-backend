package com.example.smartttevaluation.controller;



import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;


/**
 * 关键字管理
 */
@RestController
@RequestMapping("/evaluation/keywords")
public class KeywordsController {

    @Autowired
    private CmKeywordsService cmKeywordsService;
    /**
     * 关键字列表
     */
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09",isReadOnly = true)
    public Result getKeywords(HttpServletRequest request){
        Token token = getTokenFromContext();
        System.out.println(token);
        return cmKeywordsService.getKeywords(token);
    }
    /**
     * 创建关键字
     */
    @PostMapping("/create")
    public Result createKeywords(@RequestBody CmKeywords cmKeywords) {
        return cmKeywordsService.createKeywords(cmKeywords);
    }
    /**
     * 删除关键字
     */
    @PostMapping("/delete")
    public Result deleteKeywordsByID(@RequestBody List<String> ids) {
        return cmKeywordsService.deleteKeywordsByID(ids);
    }
    /**
     * 通过关键字id获取kwa
     */
    @PostMapping("/getKwaByKeywordsID")
    public Result getKwaByKeywordsID(@RequestParam("courseid") String courseid , @RequestBody List<String> ids) {
        return cmKeywordsService.getKwaByKeywordsID(courseid,ids);
    }
    /**
     * 更新关键字
     */
    @PostMapping
    public Result updateKeywords(@RequestBody CmKeywords cmKeywords){
        return cmKeywordsService.updateKeywords(cmKeywords);
    }
}

