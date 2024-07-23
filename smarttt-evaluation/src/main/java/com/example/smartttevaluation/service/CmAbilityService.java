package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.pojo.CmAbility;

import java.util.List;

public interface CmAbilityService {

    /**
     * 创建能力，用来实现同级新增&下级新增
     */
    Result createOneAbility(CmAbility cmAbility);
    /**
     * 能力树，用来实现能力列表
     */
    Result getAbilityTree(Token token);
    /**
     * 更新能力
     */
    Result upgradeOneAbilityByID(String id);
    /**
     * 删除能力
     */
    Result deleteAbilityByIDs(List<String> ids, String proid);
}
