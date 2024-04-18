package com.example.smartttevaluation.controller;


import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关键字管理
 */
@RestController
@RequestMapping("/evaluation/keywords")
public class KeywordsController {

    @Autowired
    private CmKeywordsService cmKeywordsService;
    @GetMapping
    public Result getKeywords() {
        return cmKeywordsService.getKeywords();
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

