package com.example.smartttevaluation.service;

import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;

import java.util.List;

public interface CmKnowledgeUnitService {

    Result getKnowledgeUnitList(String courseid);
    Result insertChapter(CmKnowledgeUnit cmKnowledgeUnit);
    Result insertSection(CmKnowledgeUnit cmKnowledgeUnit);
    Result insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);
    Result deleteKnowledgeUnitKwa(String unitid,List<String> kwaids);
    Result deleteKnowledgeUnit(String courseid,List<String> unitids);
    Result updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit);
    Result updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);
    Result updateKnowledgeUnitOrdernum(CmKnowledgeUnit cmKnowledgeUnit,long preOrdernum);
}
