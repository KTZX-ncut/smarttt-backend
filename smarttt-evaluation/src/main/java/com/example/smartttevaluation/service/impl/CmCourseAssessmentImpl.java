package com.example.smartttevaluation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.smartttevaluation.dto.CmCourseAssessmentTable;
import com.example.smartttevaluation.dto.CmCourseAssessmentTableItem;
import com.example.smartttevaluation.pojo.*;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmCourseAssessmentMapper;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CmCourseAssessmentImpl implements CmCourseAssessmentService {
    @Autowired
    private CmCourseAssessmentMapper cmCourseAssessmentMapper;
    @Override
    public Result getAssessmentTable(String courseid){
        CmCourseAssessmentTable cmCourseAssessmentTable=new CmCourseAssessmentTable(new ArrayList<>(),new ArrayList<>(),new JSONObject());
        //处理表头部分
        List<CmCheckitem> cmCourseCheckItems= cmCourseAssessmentMapper.getCourseCheckItem(courseid);
        //创建通过id的映射
        Map<String,CmCheckitem> t_map = new HashMap<>();
        for(CmCheckitem t_cmCourseCheckItem:cmCourseCheckItems){
            t_map.put(t_cmCourseCheckItem.getId(),t_cmCourseCheckItem);
            t_cmCourseCheckItem.setChildrens(new ArrayList<>());
        }
        for(CmCheckitem t_cmCourseCkeckItem:cmCourseCheckItems){
            if(Objects.equals(t_cmCourseCkeckItem.getPid(), "0")){
                //根节点
                //添加期末/平时到表头
                cmCourseAssessmentTable.getHead().add(t_cmCourseCkeckItem);
                //将对应比例添加至表
                cmCourseAssessmentTable.getPercent().put(t_cmCourseCkeckItem.getId(),t_cmCourseCkeckItem.getPercent());

            }else{
                //非根节点,找到父节点连接
                String t_pid=t_cmCourseCkeckItem.getPid();
                CmCheckitem p_cmCourseCheckItem=t_map.get(t_pid);

                p_cmCourseCheckItem.getChildrens().add(t_cmCourseCkeckItem);
            }

        }

        //获取所有target
        List<CmCoursetarget> cmCourseTargets= cmCourseAssessmentMapper.getCourseTarget(courseid);

        //创建targerId到JSON的映射
        Map<String,JSONObject> targetMap=new HashMap<>();
        for(CmCoursetarget t_cmCourseTarget:cmCourseTargets){
            //创建JSON对象
            JSONObject t_json=new JSONObject();
            //添加属性
            t_json.put("id",t_cmCourseTarget.getId());
            t_json.put("name",t_cmCourseTarget.getName());
            //将此JSON对象添加至映射，使用targetId访问
            targetMap.put(t_cmCourseTarget.getId(),t_json);
            //将此JSON添加至items列表
            cmCourseAssessmentTable.getItems().add(t_json);

        }
        //获取所有的CourseAssement
        List<CmCourseAssessment> cmCourseAssessment= cmCourseAssessmentMapper.selectCourseAssessment(courseid);
        //将CourseAssessment中的standardScore填入到相应的位置
        for(CmCourseAssessment t_cmCourseAssessment:cmCourseAssessment){
            //获取对应的JSON对象
           JSONObject t_json=targetMap.get(t_cmCourseAssessment.getCoursetargetId());
            //若存在此JSON对象则添加JSON属性{"targetId","standardScore"}
            if(t_json!=null){
                t_json.put(t_cmCourseAssessment.getCheckitemId(),t_cmCourseAssessment.getStandardScore());
            }

        }

        return Result.success(cmCourseAssessmentTable);
    }
    //通过行列更改
    public Result updateStandardScore(CmCourseAssessment cmCourseAssessment){
        if(cmCourseAssessment.getStandardScore()==0){
            //standardScore改为0，删除对应条存储
            cmCourseAssessmentMapper.deleteAssessment(cmCourseAssessment);
        }else{
            if(cmCourseAssessmentMapper.selectAssessmentCount(cmCourseAssessment)==0){
                //此项没有数值,需新建
                cmCourseAssessment.setId(CommonFunctions.generateEnhancedID("cm_course_assessment"));
                cmCourseAssessmentMapper.insertAssessment(cmCourseAssessment);
            }else{
                cmCourseAssessmentMapper.updateAssessment(cmCourseAssessment);
            }
        }


        return Result.success();
    }

    public Result updatePercent(CmCheckitem cmCourseCheckItem){

        cmCourseAssessmentMapper.updatePercent(cmCourseCheckItem);
        return Result.success();
    }

}
