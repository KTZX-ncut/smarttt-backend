package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.mapper.CmCoursetargetMapper;
import com.example.smartttevaluation.pojo.CmCoursetarget;
import com.example.smartttevaluation.pojo.CmCoursetargetUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.service.CmCoursetargetService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartttevaluation.pojo.CommonFunctions.generateEnhancedID;

@Service
public class CmCoursetargetServiceImpl implements CmCoursetargetService {

    @Autowired
    private CmCoursetargetMapper cmCoursetargetMapper;
    /**
     *获取课程目标
     */
    @Override
    public Result getCoursetarget(Token token) {
        return Result.success(cmCoursetargetMapper.getCoursetarget());
    }
    /**
     *创建课程目标
     */
    @Override
    public Result createCoursetarget(CmCoursetarget cmCoursetarget) {
        cmCoursetarget.setId(generateEnhancedID("cm_coursetarget"));
        cmCoursetargetMapper.createCoursetarget(cmCoursetarget);
        return Result.success();
    }
    /**
     *批量删除课程目标
     */
    @Override
    public Result deleteCoursetargetByID(List<String> ids) {
        cmCoursetargetMapper.deleteCoursetargetByIDs(ids);
        return Result.success();
    }
    /**
     *更新课程目标
     */
    @Override
    public Result updateCoursetarget(CmCoursetarget cmCoursetarget) {
        cmCoursetargetMapper.updateCoursetargetByID(cmCoursetarget);
        return Result.success();
    }
    /**
     *添加unit
     */
    public Result insertCoursetargetUnit(@Param("courseid") String courseid, CmCoursetargetUnit cmCoursetargetUnit){
        if(cmCoursetargetMapper.getUnitCount(cmCoursetargetUnit)!=0){
            return Result.error("该基本教学目标已存在");
        }
        cmCoursetargetMapper.insertCoursetargetUnit(courseid, cmCoursetargetUnit);
        return Result.success();
    }
    /**
     *删除Unit
     */
    public Result deleteCoursetargetUnit(String unitid, String targetid){
        if(cmCoursetargetMapper.getUnitCountByUnitId(unitid,targetid)==0){
            return Result.error("单元id不存在");
        }
        cmCoursetargetMapper.deleteCoursetargetUnit(unitid);
        return Result.success();
    }

    /**
     *批量删除课程目标
     */
    public Result deleteCoursetargetByIDs(List<String> ids, String courseid, String unitid){
        //获取ids
        List<String> all_ids=cmCoursetargetMapper.selectAllidByids(ids);
        //如果为空则返回错误
        if(all_ids.isEmpty()){
            return Result.error(404,"没有可删除项");
        }
        //删除所有unit
        cmCoursetargetMapper.deleteCoursetargetUnit(unitid);
        //删除
        cmCoursetargetMapper.deleteCoursetargetByIDs(all_ids);
        return Result.success();
    }

    //更新知识单元
//    public Result updateCoursetargetUnit(CmCoursetargetUnit cmCoursetargetUnit){
//        if(cmCoursetargetMapper.getUnitCountByUnitId(cmCoursetargetUnit.getUnitid())==0){
//            return Result.error("此基本教学目标单元不存在");
//        }
//        cmCoursetargetMapper.updateCoursetargetUnit(cmCoursetargetUnit);
//        return Result.success();
//    }
    /**
     *更新Unit
     */
    public Result updateCoursetargetUnit(CmCoursetargetUnit cmCoursetargetUnit){
        if(cmCoursetargetMapper.getUnitCount(cmCoursetargetUnit)==0){
            return Result.error("此unit不存在");
        }
        cmCoursetargetMapper.updateCoursetargetUnit(cmCoursetargetUnit);
        return Result.success();
    }
    /**
     *插入unit
     */
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
            //查询对应courseid中的课程目标id是否已存在
            if(cmCoursetargetMapper.getNumOfTargetIdAndCourseById(id,courseid)!=0){
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
