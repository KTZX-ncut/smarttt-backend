package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Termslist;
import com.example.smartttadmin.mapper.StTermsMapper;
import com.example.smartttadmin.service.StTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StTermsServicelmpl implements StTermsService {
    @Autowired
    private StTermsMapper stTermsMapper;

    @Override
    public Result getTermsList(){
        List<Termslist> termsList = stTermsMapper.getTerms();
        return Result.success(termsList);
    }

    @Override
    public Result createTerms() {
        boolean isSuccess = stTermsMapper.insertTerms();
        return Result.success("1");
    }

    @Override
    public Result deleteTerms() {
        boolean isSuccess = stTermsMapper.deleteTerms();
        return Result.success("1");
    }

    @Override
    public Result Termslist(Termslist updateTermsReq) {
        boolean isSuccess = stTermsMapper.updateTerms(updateTermsReq);
        return Result.success("1");
    }

    @Override
    public Result getCurrentTerms() {
        Termslist currentTerms = stTermsMapper.getCurrentTerms();
        return currentTerms != null ? Result.success(currentTerms) : Result.error("0");
    }
}
