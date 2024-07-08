package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmKwadictMapper;
import com.example.smartttevaluation.pojo.CmGetability;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.service.CmKwadictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmKwadictServiceImpl implements CmKwadictService {
    @Autowired
    private CmKwadictMapper cmKwadictMapper;
    /**
     *获取kwa
     */
    @Override
    public Result getKwadict(String ObsID) {

        return Result.success(cmKwadictMapper.getKwadict());
    }
    /**
     *创建kwa
     */
    @Override
    public Result createKwadict(CmKwadict cmKwadict) {
        cmKwadict.setId(generateEnhancedID("cm_kwadict"));
        cmKwadictMapper.createKwadict(cmKwadict);
        return Result.success();
    }
    /**
     *删除kwa
     */
    @Override
    public Result deleteKwadictByID(List<String> ids) {
        cmKwadictMapper.deleteKwadictByIDs(ids);
        return Result.success();
    }
    /**
     *更新kwa
     */
    @Override
    public Result updateKwadict(CmKwadict cmKwadict) {
        cmKwadictMapper.updateKwadictByID(cmKwadict);
        return Result.success();
    }
    /**
     *通过KeywordId获取关键字
     */
    @Override
    public boolean getKeywordsByKeywordId(String keywordid) {
        CmKeywords cmkeywords = cmKwadictMapper.getKeywordsByKeywordId(keywordid);
        if (cmkeywords != null) {
            // 找到了匹配的记录
            return true;
        } else {
            // 没有找到匹配的记录
            return false;
        }
    }
    /**
     *通过AbilityId获取能力
     */
    @Override
    public boolean getAbilityByAbilityId(String abilityid) {
        CmGetability ability = cmKwadictMapper.getAbilityByAbilityId(abilityid);
        if (ability != null) {
            // 找到了匹配的记录
            return true;
        } else {
            // 没有找到匹配的记录
            return false;
        }
    }
    /**
     *通过keywordid和abilityid获取kwa
     */
    @Override
    public Result getKwadictBykeywordidAndabilityid(String keywordid, String abilityid) {
        return Result.success(cmKwadictMapper.getKwadictBykeywordidAndabilityid(keywordid, abilityid));
    }
    /**
     *获取关键字字典
     */
    @Override
    public Result getKeywordsDict(String courseid) {
        return Result.success(cmKwadictMapper.getKeywordsDict(courseid));
    }
    /**
     *获取能力字典
     */
    @Override
    public Result getAbilityDict(String courseid) {
        return Result.success(cmKwadictMapper.getAbilityDict(courseid));
    }

}
