package com.example.smartttcourse.service.impl;


import com.example.smartttcourse.Utils.CommonFunctions;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.pojo.CmTerm;
import com.example.smartttcourse.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CmTermServicelmpl implements CmTermService {

    @Autowired
    private CmTermMapper cmTermMapper;

    @Override
    public Result getHistoryTerm() {
        return Result.success(cmTermMapper.getHistoryTerms());
    }

    @Override
    public Result createTerms(CmTerm cmTerm) {
        cmTerm.setId(CommonFunctions.generateEnhancedID("cm_term"));
        cmTerm.setCreatetime(LocalDateTime.now().toString());
        cmTerm.setIscurrentterm("0");
        cmTermMapper.createTerms(cmTerm);
        return Result.success();
    }

    @Override
    public Result deleteTermsByID(List<String> ids) {
        cmTermMapper.deleteTermsByIDs(ids);
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
    public Result setCurrentTerms(String id) {
        cmTermMapper.setCurrentTerms(id);
        cmTermMapper.setOtherTerms(id);
        return Result.success();
    }

}
