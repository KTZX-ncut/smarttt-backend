package com.example.smartttevaluation.controller;


import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
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
    @AuthRequired(type = "admin", menu = "531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09", isReadOnly = true)
    public Result getKeywords(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return cmKeywordsService.getKeywords(token.getObsid());
    }

    /**
     * 创建关键字
     */
    @PostMapping("/create")
    @AuthRequired(type = "admin", menu = "531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09")
    public Result createKeywords(@RequestBody CmKeywords cmKeywords, HttpServletRequest request) {
        Token token = getTokenFromContext();
        cmKeywords.setCourseid(token.getObsid());
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
    @PostMapping("/getKwaByKeywordsId")
    public Result getKwaNameByKeywordsId(@RequestBody List<String> ids) {
        return cmKeywordsService.getKwaByKeywordsId(ids);
    }

    /**
     * 更新关键字
     */
    @PostMapping
    public Result updateKeywords(@RequestBody CmKeywords cmKeywords) {
        return cmKeywordsService.updateKeywords(cmKeywords);
    }

    @PostMapping("/import")
    @AuthRequired(type = "admin", menu = "531500340-86816d21-ec0c-4dc6-ad1d-8edea9716d09")
    public Result importKeywords(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Token token = getTokenFromContext();
        List<CmKeywords> cmKeywordsList = cmKeywordsService.importKeywordExcel(file);
        for (CmKeywords cmKeywords : cmKeywordsList) {
            cmKeywords.setCourseid(token.getObsid());
            cmKeywordsService.createKeywords(cmKeywords);
        }
        return Result.success();

    }
}

