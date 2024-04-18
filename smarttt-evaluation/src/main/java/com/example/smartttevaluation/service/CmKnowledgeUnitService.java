package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;

import java.util.List;

public interface CmKnowledgeUnitService {

    Result getKnowledgeUnitList(String courseid);
    Result insertChapter(CmKnowledgeUnit cmKnowledgeUnit);
    Result insertSection(CmKnowledgeUnit cmKnowledgeUnit);
    Result insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);
    Result deleteKnowledgeUnitKwa(String unitid,List<String> kwaids);
    Result deleteKnowledgeUnit(List<String> unitids);
    Result updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit);
    Result updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);
}
