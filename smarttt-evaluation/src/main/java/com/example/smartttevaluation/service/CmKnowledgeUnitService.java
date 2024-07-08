package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;

import java.util.List;

public interface CmKnowledgeUnitService {

    /**
     *获取知识单元列表
     */
    Result getKnowledgeUnitList(String courseid);
    /**
     *插入章
     */
    Result insertChapter(CmKnowledgeUnit cmKnowledgeUnit);
    /**
     *插入节
     */
    Result insertSection(CmKnowledgeUnit cmKnowledgeUnit);
    /**
     *插入知识单元kwa
     */
    Result insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);
    /**
     *删除知识单元kwa
     */
    Result deleteKnowledgeUnitKwa(String unitid,List<String> kwaids);
    /**
     *删除知识单元
     */
    Result deleteKnowledgeUnit(String courseid,List<String> unitids);
    /**
     *更新知识单元
     */
    Result updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit);
    /**
     *更新知识单元kwa
     */
    Result updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa);
    /**
     *更新知识单元顺序号
     */
    Result updateKnowledgeUnitOrdernum(CmKnowledgeUnit cmKnowledgeUnit,long preOrdernum);
}
