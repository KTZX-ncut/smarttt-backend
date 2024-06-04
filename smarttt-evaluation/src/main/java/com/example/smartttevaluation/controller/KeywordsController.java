package com.example.smartttevaluation.controller;


import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Token;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 关键字管理
 */
@RestController
@RequestMapping("/evaluation/keywords")
public class KeywordsController {

    @Autowired
    private CmKeywordsService cmKeywordsService;

    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-c0220993-26e0-4d21-bc25-f612c67170c5",isReadOnly = true)
    public Result getKeywords(HttpServletRequest request){
        Token token = getTokenFromContext();
        return cmKeywordsService.getKeywords(token.getObsid());
    }

    @PostMapping("/create")
    public Result createKeywords(@RequestBody CmKeywords cmKeywords) {
        return cmKeywordsService.createKeywords(cmKeywords);
    }

    @PostMapping("/delete")
    public Result deleteKeywordsByID(@RequestBody List<String> ids) {
        return cmKeywordsService.deleteKeywordsByID(ids);
    }

    @PostMapping("/getKwaByKeywordsID")
    public Result getKwaByKeywordsID(@RequestParam("courseid") String courseid , @RequestBody List<String> ids) {
        return cmKeywordsService.getKwaByKeywordsID(courseid,ids);
    }

    @PostMapping
    public Result updateKeywords(@RequestBody CmKeywords cmKeywords){
        return cmKeywordsService.updateKeywords(cmKeywords);
    }
}

