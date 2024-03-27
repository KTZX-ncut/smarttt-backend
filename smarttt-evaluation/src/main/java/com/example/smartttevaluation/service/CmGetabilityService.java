package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;

import java.util.List;

public interface CmGetabilityService {

    Result getGetability();

    Result deleteGetabilityByID(List<String> ids);

    Result updateGetability(CmAbility cmAbility);


}
