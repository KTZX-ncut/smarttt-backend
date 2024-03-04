package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.TermsResponse;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.mapper.SmTermsMapper;
import com.example.smartttadmin.pojo.SmTerms;
import com.example.smartttadmin.service.SmTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.smartttadmin.pojo.EnhancedUniqueID.generateEnhancedID;

@Service
public class SmTermsServicelmpl implements SmTermsService {

    @Autowired
    private SmTermsMapper smTermsMapper;

    @Override
    public Result getTerms() {
        List<TermsResponse>  termsResponseList = smTermsMapper.getTerms();
        for( TermsResponse termsResponse : termsResponseList)
            termsResponse.setTermName(smTermsMapper.getTerms(TermsResponse.getId()));
        return Result.success(termsResponseList);
    }

    @Override
    public Result createTerms(SmTerms smTerms) {
        smTerms.setId(generateEnhancedID("sm_terms"));
        smTerms.setCreatetime(LocalDateTime.now().toString());
        smTermsMapper.createTerms(smTerms);
        return Result.success();
    }

    @Override
    public Result deleteTermsByID(String id) {
        smTermsMapper.deleteTermsByID(id);
        return Result.success();
    }

    @Override
    public Result getCurrentTerms(boolean isActive) {
        smTermsMapper.getCurrentTerms(isActive);
        return Result.success();
    }
}
