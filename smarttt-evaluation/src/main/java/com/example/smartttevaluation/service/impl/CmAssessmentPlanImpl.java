package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.dto.CmAssessmentPlanTable;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmAssessmentPlanMapper;
import com.example.smartttevaluation.service.CmAssessmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmAssessmentPlanImpl implements CmAssessmentPlanService {
    @Autowired
    private CmAssessmentPlanMapper cmAssessmentPlanMapper;
    /**
     *获取考核方案表格
     */
    @Override
    public Result getAssessmentPlanTable(String courseid){
        //如果没有，则添加空记录，此处为课程创建后首次查询
        if(cmAssessmentPlanMapper.getProportiontNum(courseid)==0){
            cmAssessmentPlanMapper.insertVoidProportion(courseid);
        }
        //获取课程目标对应的值
        List<CmAssessmentPlanItem> cmAssessmentPlanItems=cmAssessmentPlanMapper.selectAssessmentPlanItems(courseid);
        //获取比例
        CmAssessmentPlanProportion cmAssessmentPlanProportion=cmAssessmentPlanMapper.selectAssessmentPlanProportion(courseid);
        //组装成表，返回
        CmAssessmentPlanTable cmAssessmentPlanTable=new CmAssessmentPlanTable(cmAssessmentPlanItems,cmAssessmentPlanProportion);
        return Result.success(cmAssessmentPlanTable);
    }
    /**
     *更新考核项
     */
    public Result updateItem(CmAssessmentPlanItem cmAssessmentPlanItem){

        cmAssessmentPlanMapper.updateItem(cmAssessmentPlanItem);
        return Result.success();
    }
    /**
     *更新比例
     */
    public Result updateProportion(CmAssessmentPlanProportion cmAssessmentPlanProportion){

        cmAssessmentPlanMapper.updateProportion(cmAssessmentPlanProportion);
        return Result.success();
    }

}
