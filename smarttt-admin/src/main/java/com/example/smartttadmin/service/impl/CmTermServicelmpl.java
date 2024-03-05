package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.mapper.CmTermsMapper;
import com.example.smartttadmin.pojo.CmTerm;
import com.example.smartttadmin.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.smartttadmin.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmTermServicelmpl implements CmTermService {

    @Autowired
    private CmTermsMapper cmTermsMapper;

    @Override
    public Result getTerms() {
        return Result.success(cmTermsMapper.getTerms());
    }

    @Override
    public Result createTerms(CmTerm cmTerm) {
        cmTerm.setId(generateEnhancedID("cm_term"));
        cmTerm.setCreatetime(LocalDateTime.now().toString());
        cmTermsMapper.createTerms(cmTerm);
        return Result.success();
    }

    @Override
    public Result deleteTermsByID(String id) {
        cmTermsMapper.deleteTermsByID(id);
        return Result.success();
    }

    @Override
    public Result getCurrentTerms(boolean iscurrentterm) {
        cmTermsMapper.getCurrentTerms(iscurrentterm);
        return Result.success();
    }
}
