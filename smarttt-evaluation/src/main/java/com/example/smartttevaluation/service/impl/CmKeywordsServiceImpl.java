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
    /**
     *获取关键字
     */
    @Override
    public Result getKeywords(String obsId) {
        return Result.success(cmKeywordsMapper.getKeywords(obsId));
    }
    /**
     *创建关键字
     */
    @Override
    public Result createKeywords(CmKeywords cmKeywords) {
        cmKeywords.setId(generateEnhancedID("cm_keywords"));
        cmKeywordsMapper.createKeywords(cmKeywords);
        return Result.success();
    }
    /**
     *批量删除关键字
     */
    @Override
    public Result deleteKeywordsByID(List<String> ids) {
        cmKeywordsMapper.deleteKeywordsByIDs(ids);
        return Result.success();
    }
    /**
     *更新关键字
     */
    @Override
    public Result updateKeywords(CmKeywords cmKeywords) {
        cmKeywordsMapper.updateKeywordsByID(cmKeywords);
        return Result.success();
    }
    /**
     *通过关键字id查询相关kwa
     */
    @Override
    public Result getKwaByKeywordsID(String courseid, List<String> ids) {
        //查询课程id是否存在
        if (cmKeywordsMapper.getNumOfCourseById(courseid) == 0) {
            return Result.error(404, "课程id不存在");
        }
        return Result.success(cmKeywordsMapper.getKwaByKeywordsId(courseid,ids));
    }
}
