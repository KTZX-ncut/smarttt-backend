package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.dto.CmClassroomUnitTree;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmClassroomUnitMapper;
import com.example.smartttevaluation.pojo.CmClassroomUnit;
import com.example.smartttevaluation.pojo.CmClassroomUnitKwa;
import com.example.smartttevaluation.pojo.CommonFunctions;
import com.example.smartttevaluation.service.CmClassroomUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CmClassroomUnitImpl implements CmClassroomUnitService {
    @Autowired
    private CmClassroomUnitMapper cmClassroomUnitMapper;
    @Override
    public Result getClassroomUnitList(String courseid){
        List<CmClassroomUnit> t_Chapters=cmClassroomUnitMapper.getChapter(courseid);
        List<CmClassroomUnitTree> cmClassroomUnitChapters=new ArrayList<>();

        //把一级目录加入至根节点
        for(CmClassroomUnit t_Chapter:t_Chapters){
            //创建一级目录对象
            CmClassroomUnitTree t_cmClassroomUnitChapter=new CmClassroomUnitTree(t_Chapter.getId(),t_Chapter.getName(),t_Chapter.getType(),new ArrayList<>(),new ArrayList<>());
            //将一级目录对象加入根列表
            cmClassroomUnitChapters.add(t_cmClassroomUnitChapter);
            String t_ChapterId=t_Chapter.getId();
            //创建章的kwa映射关系
            Map<String,CmClassroomUnitKwa> map_kwaid_to_kwa=new HashMap<>();
            List<CmClassroomUnitTree> t_cmClassroomUnitSections=t_cmClassroomUnitChapter.getChildren();
            //获取二级目录
            List<CmClassroomUnit> t_Sections=cmClassroomUnitMapper.getSection(t_ChapterId);
            //遍历二级（节）目录
            for(CmClassroomUnit t_Section:t_Sections){
                //获取当前节的kwa的列表
                List<CmClassroomUnitKwa> t_cmClassroomkwas=cmClassroomUnitMapper.getClassroomUnitKwa(t_Section.getId());
                //创建该二级目录对象
                CmClassroomUnitTree t_cmClassroomUnitSection=new CmClassroomUnitTree(t_Section.getId(),t_Section.getName(),t_Section.getType(),t_cmClassroomkwas,new ArrayList<>());
                //将该对象添加到相应一级目录的节列表
                t_cmClassroomUnitSections.add(t_cmClassroomUnitSection);
                for(CmClassroomUnitKwa t_kwa:t_cmClassroomkwas){
                    //当前kwaid是否存在章中
                    if(map_kwaid_to_kwa.containsKey(t_kwa.getKwaid())==false){
                        //不存在，新增
                        CmClassroomUnitKwa t_chapterkwa=new CmClassroomUnitKwa(t_ChapterId,t_kwa.getKwaid(),t_kwa.getName(),t_kwa.getStatue());
                        //存到章kwa列表
                        t_cmClassroomUnitChapter.getKwas().add(t_chapterkwa);
                        //创建映射关系
                        map_kwaid_to_kwa.put(t_kwa.getKwaid(),t_chapterkwa);
                    }else{
                        //存在，处理statue
                        //获取章的statue
                        int t_statue=map_kwaid_to_kwa.get(t_kwa.getKwaid()).getStatue();
                        //都为完成时才算完成
                        t_statue=t_statue&t_kwa.getStatue();
                        //更新statue
                        map_kwaid_to_kwa.get(t_kwa.getKwaid()).setStatue(t_statue);
                    }

                }
            }
        }


        return Result.success(cmClassroomUnitChapters);
    }

    public Result insertChapter(CmClassroomUnit cmClassroomUnit){
        long t_ordernum= cmClassroomUnitMapper.getMaxChapterOrdernum(cmClassroomUnit.getCourseid())+1;
        cmClassroomUnitMapper.insertChapter(CommonFunctions.generateEnhancedID("cm_classroom_unit"),cmClassroomUnit.getName(),cmClassroomUnit.getType(),t_ordernum,cmClassroomUnit.getCourseid());
        return Result.success();
    }

    public Result insertSection(CmClassroomUnit cmClassroomUnit){
        long t_ordernum= cmClassroomUnitMapper.getMaxSectionOrdernum(cmClassroomUnit.getPid())+1;
        cmClassroomUnitMapper.insertSection(CommonFunctions.generateEnhancedID("cm_classroom_unit"),cmClassroomUnit.getPid(),cmClassroomUnit.getName(),cmClassroomUnit.getType(),t_ordernum,cmClassroomUnit.getCourseid());
        return Result.success();
    }

    public Result insertClassroomUnitKwa(CmClassroomUnitKwa cmClassroomUnitKwa){
        cmClassroomUnitMapper.insertClassroomUnitKwa(cmClassroomUnitKwa.getUnitid(),cmClassroomUnitKwa.getKwaid(),cmClassroomUnitKwa.getStatue());
        return Result.success();
    }
    public Result deleteClassroomUnitKwa(String unitid,List<String> kwaids){
        cmClassroomUnitMapper.deleteClassroomUnitkwa(unitid,kwaids);
        return Result.success();
    }
    public Result deleteSection(List<String> ids){
        cmClassroomUnitMapper.deleteAllUnitkwaByUnitid(ids);
        cmClassroomUnitMapper.deleteSection(ids);
        return Result.success();
    }
    public Result deleteChapter(String ChapterUnitid){
        cmClassroomUnitMapper.deleteAllUnitkwaByChapterUnitid(ChapterUnitid);
        cmClassroomUnitMapper.deleteAllSectionByChapterUnitid(ChapterUnitid);
        return Result.success();
    }
}
