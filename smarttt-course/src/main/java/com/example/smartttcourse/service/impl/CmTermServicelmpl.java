package com.example.smartttcourse.service.impl;


import com.example.smartttcourse.Utils.CommonFunctions;
import com.example.smartttcourse.Utils.TermTableContextHolder;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.pojo.CmTerm;
import com.example.smartttcourse.service.CmTermService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CmTermServicelmpl implements CmTermService {

    @Autowired
    private CmTermMapper cmTermMapper;
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Result getHistoryTerm(String termid) {
        return Result.success(cmTermMapper.getHistoryTerms(termid));
    }

    @Override
    @Transactional
    public Result createTerms(CmTerm cmTerm) {
        cmTerm.setId(CommonFunctions.generateEnhancedID("cm_term"));
        cmTerm.setCreatetime(LocalDateTime.now().toString());
        cmTerm.setIscurrentterm("0");
        cmTermMapper.createTerms(cmTerm);
        try{
            String orderNo = cmTermMapper.getTermNo(cmTerm.getId());
            TermTableContextHolder.setSuffix(orderNo);
            cmTermMapper.createNewinfo();
        } finally {
            TermTableContextHolder.clear();
        }

        return Result.success();
    }

    @Override
    public Result deleteTermsByID(List<String> ids) {
        cmTermMapper.deleteTermsByIDs(ids);
        cmTermMapper.deleteObsTermByIDs(ids);
        return Result.success();
    }

    @Override
    public Result updateTermByID(CmTerm cmTerm) {
        cmTermMapper.updateTermByID(cmTerm);
        return Result.success();
    }

    @Override
    public Result getTerms() {
        return Result.success(cmTermMapper.getTerms());
    }

    @Override
    public Result getCurrentTerm() {
        return Result.success(cmTermMapper.getCurrentTerm());
    }

    @Override
    public String getCurrentTermNo() {
        return cmTermMapper.getCurrentTermNo();
    }

    @Override
    public Result setCurrentTerms(String id) {
        cmTermMapper.setCurrentTerms(id);
        cmTermMapper.setOtherTerms(id);
//        redisTemplate.opsForValue().set("current_term",id);
//        redisTemplate.expire("current_term",6, TimeUnit.MINUTES);
        return Result.success();
    }

}
