package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmKeywordsMapper;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmKeywordsServiceImpl implements CmKeywordsService {

    @Autowired
    private CmKeywordsMapper cmKeywordsMapper;

    @Override
    public Result getKeywords() {
        return Result.success(cmKeywordsMapper.getKeywords());
    }

    @Override
    public Result createKeywords(CmKeywords cmKeywords) {
        cmKeywords.setId(generateEnhancedID("cm_keywords"));
        cmKeywordsMapper.createKeywords(cmKeywords);
        return Result.success();
    }

    @Override
    public Result deleteKeywordsByID(List<String> ids) {
        cmKeywordsMapper.deleteKeywordsByIDs(ids);
        return Result.success();
    }

    @Override
    public Result updateKeywords(CmKeywords cmKeywords) {
        cmKeywordsMapper.updateKeywordsByID(cmKeywords);
        return Result.success();
    }

}
