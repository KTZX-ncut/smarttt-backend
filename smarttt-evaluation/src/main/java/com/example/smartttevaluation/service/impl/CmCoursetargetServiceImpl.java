package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmCoursetargetMapper;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.service.CmCoursetargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmCoursetargetServiceImpl implements CmCoursetargetService {

    @Autowired
    private CmCoursetargetMapper cmCoursetargetMapper;

    @Override
    public Result getCoursetarget(String courseid) {
        return Result.success(cmCoursetargetMapper.getCoursetarget());
    }

    @Override
    public Result createCoursetarget(CmCoursetarget cmCoursetarget) {
        cmCoursetarget.setId(generateEnhancedID("cm_coursetarget"));
        cmCoursetargetMapper.createCoursetarget(cmCoursetarget);
        return Result.success();
    }

    @Override
    public Result deleteCoursetargetByID(List<String> ids) {
        cmCoursetargetMapper.deleteCoursetargetByIDs(ids);
        return Result.success();
    }

    @Override
    public Result updateCoursetarget(CmCoursetarget cmCoursetarget) {
        cmCoursetargetMapper.updateCoursetargetByID(cmCoursetarget);
        return Result.success();
    }


    @Override
    public Result insertunit(String courseid, List<String> ids) {
        //查询课程id是否存在
        if (cmCoursetargetMapper.getNumOfCourseById(courseid) == 0) {
            return Result.error(404, "课程id不存在");
        }
        List<String> success_ids=new ArrayList<>();
        //查询基本教学单元id是否存在
        long cnt_non=0,cnt_exsit=0,cnt_success=0;
        for(String id:ids){
            //查询基本教学单元id是否在基本教学单元字典中
            if (cmCoursetargetMapper.getNumOfUnitById(id) == 0) {
                cnt_non++;
                continue;
            }
            //查询对应courseid中的基本教学目标id是否已存在
            if(cmCoursetargetMapper.getNumOfUnitIdAndCourseById(id,courseid)!=0){
                cnt_exsit++;
                continue;
            }
            //统计可插入的id列表
            cnt_success++;
            success_ids.add(id);
        }
        //统计失败信息
        String str_error="失败数量:"+(cnt_non+cnt_exsit)+",其中基本教学目标id不存在:"+cnt_non+",基本教学目标已存在:"+cnt_exsit;
        //没有可插入的数据
        if(success_ids.isEmpty()){
            return Result.error(404, str_error);
        }
        //插入数据
        cmCoursetargetMapper.insertunit(courseid,success_ids);
        String str="成功插入数量:"+cnt_success+","+str_error;
        return Result.success(str);
    }

}
