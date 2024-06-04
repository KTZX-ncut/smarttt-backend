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
    public Result getKeywords(String ObsID) {
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

    //通过关键字id查询相关kwa
    @Override
    public Result getKwaByKeywordsID(String courseid, List<String> ids) {
        //查询课程id是否存在
        if (cmKeywordsMapper.getNumOfCourseById(courseid) == 0) {
            return Result.error(404, "课程id不存在");
        }
        return Result.success(cmKeywordsMapper.getKwaByKeywordsId(courseid,ids));
    }
}
