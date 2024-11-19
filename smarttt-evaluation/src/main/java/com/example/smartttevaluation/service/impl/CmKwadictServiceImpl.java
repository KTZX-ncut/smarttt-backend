package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmCoursetargetMapper;
import com.example.smartttevaluation.mapper.CmKnowledgeUnitMapper;
import com.example.smartttevaluation.mapper.CmKwadictMapper;
import com.example.smartttevaluation.mapper.CmLinesMapper;
import com.example.smartttevaluation.pojo.CmGetability;
import com.example.smartttevaluation.pojo.CmKeywords;
import com.example.smartttevaluation.pojo.CmKwadict;
import com.example.smartttevaluation.service.CmKwadictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmKwadictServiceImpl implements CmKwadictService {
    @Autowired
    private CmKwadictMapper cmKwadictMapper;
    @Autowired
    private CmKnowledgeUnitMapper cmKnowledgeUnitMapper;
    @Autowired
    private CmCoursetargetMapper cmCoursetargetMapper;
    @Autowired
    private CmLinesMapper cmLinesMapper;

    /**
     * 获取kwa
     */
    @Override
    public Result getKwadict(String obsid) {
        List<CmKwadict> kwas = cmKwadictMapper.getKwadict(obsid);
        return Result.success(kwas);
    }

    /**
     * 创建kwa
     */
    @Override
    public Result createKwadict(CmKwadict cmKwadict) {
        cmKwadict.setId(generateEnhancedID("cm_kwadict"));
        cmKwadict.setName(cmKwadict.getKeywordname() + "-" + cmKwadict.getAbilityname());
        cmKwadictMapper.createKwadict(cmKwadict, LocalDateTime.now());
        return Result.success();
    }

    /**
     * 删除kwa
     */
    @Override
    public Result deleteKwadictByIds(List<String> ids) {
        cmKwadictMapper.deleteKwadictByIds(ids);
        // 删除kwa时同时把知识单元和课程目标以及知识能力图谱的连线中与这个kwa关联的记录删除
        cmKnowledgeUnitMapper.deleteKnowledgeUnitKwaByKwaIds(ids);
        cmCoursetargetMapper.deleteKwasByKwaIds(ids);
        cmLinesMapper.deleteLinesByKwaIds(ids);
        return Result.success();
    }

    /**
     * 更新kwa
     */
    @Override
    public Result updateKwadict(CmKwadict cmKwadict) {
        cmKwadict.setName(cmKwadict.getKeywordname() + "-" + cmKwadict.getAbilityname());
        List<CmKwadict> kwas = new ArrayList<>();
        kwas.add(cmKwadict);

        kwas.forEach(kwa -> {
            cmKwadictMapper.updateKwadictByID(kwa);
        });
        return Result.success();
    }

    /**
     * 用于更新关键字或能力时更新对应kwa的名称
     */
    public Result updateKwaName(List<CmKwadict> kwas) {

        return Result.success();
    }

    /**
     * 通过keywordid和abilityid获取kwa
     */
    @Override
    public Result getKwadictBykeywordidAndabilityid(String keywordid, String abilityid) {
        return Result.success(cmKwadictMapper.getKwadictBykeywordidAndabilityid(keywordid, abilityid));
    }

    /**
     * 获取关键字字典
     */
    @Override
    public Result getKeywordsDict(String courseid) {
        return Result.success(cmKwadictMapper.getKeywordsDict(courseid));
    }

    /**
     * 获取能力字典
     */
    @Override
    public Result getAbilityDict(String courseid) {
        return Result.success(cmKwadictMapper.getAbilityDict(courseid));
    }

}
