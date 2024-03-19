package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmAbility;

import java.util.List;

public interface CmAbilityService {

//    Result getAllAbilityList();

    Result getAllAbilityList();

    Result createOneAbility(CmAbility cmAbility);

    Result getAbilityTree();

    Result deleteAbilityByIDS(List<String> ids);

    Result upgradeOneAbilityByID(String id);

}
