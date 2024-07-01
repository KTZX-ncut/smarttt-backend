package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKeywords;

import java.util.List;

public interface CmKeywordsService {
    /**
     *获取关键字列表
     */
    Result getKeywords(String ObsID);
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
    Result updateKeywords(CmKeywords cmKeywords);
    /**
     *通过关键字id获取kwa
     */
    Result getKwaByKeywordsID(String courseid, List<String> ids);
}
