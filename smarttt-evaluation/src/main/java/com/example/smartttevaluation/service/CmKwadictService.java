package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKwadict;

import java.util.List;

public interface CmKwadictService {
    /**
     * 获取kwa
     */
    Result getKwadict(String obsid);

    /**
     * 创建kwa
     */
    Result createKwadict(CmKwadict cmKwadict);

    /**
     * 批量删除kwa
     */
    Result deleteKwadictByIds(List<String> ids);

    /**
     * 更新kwa
     */
    Result updateKwadict(CmKwadict cmKwadict);

    /**
     * 通过keywordid和abilityid获取kwa
     */
    Result getKwadictBykeywordidAndabilityid(String keywordid, String abilityid);

    /**
     * 获取关键字字典
     */
    Result getKeywordsDict(String courseid);

    /**
     * 获取能力字典
     */
    Result getAbilityDict(String courseid);

}
