package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;

import java.util.List;

public interface CmAbilityService {

    Result createOneAbility(CmAbility cmAbility);

    Result getAbilityTree();


    Result upgradeOneAbilityByID(String id);

    Result deleteAbilityByIDs(List<String> ids);
}
