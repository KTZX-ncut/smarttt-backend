package com.example.smartttcourse.service;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.pojo.CmAbility;

import java.util.List;

public interface CmAbilityService {

//    Result getAllAbilityList();

    Result getAllAbilityList();

    Result createOneAbility(CmAbility cmAbility);

    Result getAbilityTree();

    Result deleteAbilityByIDS(List<String> ids);

    Result upgradeOneAbilityByID(String id);

}
