package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKwadict;

import java.util.List;

public interface CmKwadictService {
    Result getKwadict();

    Result createKwadict(CmKwadict cmKwadict);

    Result deleteKwadictByID(List<String> ids);

    Result updateKwadict(CmKwadict cmKwadict);

    boolean getKeywordsByKeywordId(String keywordid);

    boolean getAbilityByAbilityId(String abilityid);

    Result getKwadictBykeywordidAndabilityid(String keywordid, String abilityid);

    Result getKeywordsDict(String courseid);

    Result getAbilityDict(String courseid);

}
