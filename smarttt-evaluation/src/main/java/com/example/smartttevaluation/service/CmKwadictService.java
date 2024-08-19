package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKwadict;

import java.util.List;

public interface CmKwadictService {
    /**
     *获取kwa
     */
    Result getKwadict(String token);
    /**
     *创建kwa
     */
    Result createKwadict(CmKwadict cmKwadict);
    /**
     *批量删除kwa
     */
    Result deleteKwadictByID(List<String> ids);
    /**
     *更新kwa
     */
    Result updateKwadict(CmKwadict cmKwadict);
    /**
     *通过KeywordId获取关键字
     */
    boolean getKeywordsByKeywordId(String keywordid);
    /**
     *通过AbilityId获取能力
     */
    boolean getAbilityByAbilityId(String abilityid);
    /**
     *通过keywordid和abilityid获取kwa
     */
    Result getKwadictBykeywordidAndabilityid(String keywordid, String abilityid);
    /**
     *获取关键字字典
     */
    Result getKeywordsDict(String courseid);
    /**
     *获取能力字典
     */
    Result getAbilityDict(String courseid);

}
