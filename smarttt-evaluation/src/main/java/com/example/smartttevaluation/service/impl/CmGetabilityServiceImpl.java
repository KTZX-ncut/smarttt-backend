package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmGetabilityMapper;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.service.CmGetabilityService;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CmGetabilityServiceImpl implements CmGetabilityService {

    @Autowired
    private CmGetabilityMapper cmGetabilityMapper;

    @Override
    public Result getGetability() {
        return Result.success(cmGetabilityMapper.getGetability());
    }


    @Override
    public Result deleteGetabilityByID(List<String> ids) {
        cmGetabilityMapper.deleteGetabilityByIDs(ids);
        return Result.success();
    }

    @Override
    public Result updateGetability(CmAbility cmAbility) {
        cmGetabilityMapper.updateGetabilityByID(cmAbility);
        return Result.success();
    }

}
