package com.example.smartttevaluation.service;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.CmKeywords;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CmKeywordsService {
    /**
     *获取关键字列表
     */
    Result getKeywords(String obsid);
    /**
     *创建关键字
     */
    Result createKeywords(CmKeywords cmKeywords);
    /**
     *批量删除关键字
     */
    Result deleteKeywordsByID(List<String> ids);
    /**
     *更新关键字
     */
    Result updateKeywords(CmKeywords cmKeywords, String obsId);
    /**
     *通过关键字id获取kwa
     */
    Result getKwaByKeywordsId(List<String> ids);

    List<CmKeywords> importKeywordExcel(MultipartFile file);
}
