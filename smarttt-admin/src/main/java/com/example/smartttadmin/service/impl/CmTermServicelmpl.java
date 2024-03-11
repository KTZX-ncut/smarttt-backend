package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.mapper.CmTermMapper;
import com.example.smartttadmin.pojo.CmTerm;
import com.example.smartttadmin.service.CmTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.smartttadmin.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmTermServicelmpl implements CmTermService {

    @Autowired
    private CmTermMapper cmTermMapper;

    @Override
    public Result getTerms() {
        return Result.success(cmTermMapper.getTerms());
    }

    @Override
    public Result createTerms(CmTerm cmTerm) {
        cmTerm.setId(generateEnhancedID("cm_term"));
        cmTerm.setCreatetime(LocalDateTime.now().toString());
        cmTermMapper.createTerms(cmTerm);
        return Result.success();
    }

    @Override
    public Result deleteTermsByID(List<String> ids) {
        cmTermMapper.deleteTermsByIDs(ids);
        return Result.success();
    }

    @Override
    public Result getCurrentTerms(boolean iscurrentterm) {
        cmTermMapper.getCurrentTerms(iscurrentterm);
        return Result.success();
    }
}
