package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKeywords;

import java.util.List;

public interface CmKeywordsService {
    Result getKeywords(String ObsID);

    Result createKeywords(CmKeywords cmKeywords);

    Result deleteKeywordsByID(List<String> ids);

    Result updateKeywords(CmKeywords cmKeywords);

    Result getKwaByKeywordsID(String courseid, List<String> ids);
}
