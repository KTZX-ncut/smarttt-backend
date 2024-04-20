package com.example.smartttevaluation.service.impl;
import com.example.smartttevaluation.dto.CmKnowledgeUnitTree;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmKnowledgeUnitMapper;
import com.example.smartttevaluation.pojo.CmKnowledgeUnit;
import com.example.smartttevaluation.pojo.CmKnowledgeUnitKwa;
import com.example.smartttevaluation.pojo.CommonFunctions;
import com.example.smartttevaluation.service.CmKnowledgeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CmKnowledgeUnitImpl implements CmKnowledgeUnitService {
    @Autowired
    private CmKnowledgeUnitMapper cmKnowledgeUnitMapper;
    @Override
    public Result getKnowledgeUnitList(String courseid){
        if(cmKnowledgeUnitMapper.getCourseCountByid(courseid)==0){
            return Result.error(404,"课程id不存在");
        }
        List<CmKnowledgeUnit> t_Chapters=cmKnowledgeUnitMapper.getChapter(courseid);
        List<CmKnowledgeUnitTree> cmKnowledgeUnitChapters=new ArrayList<>();

        //把一级目录加入至根节点
        for(CmKnowledgeUnit t_Chapter:t_Chapters){
            //创建一级目录对象
            CmKnowledgeUnitTree t_cmKnowledgeUnitChapter=new CmKnowledgeUnitTree(t_Chapter.getId(),t_Chapter.getName(),t_Chapter.getType(),new ArrayList<>(),new ArrayList<>());
            //将一级目录对象加入根列表
            cmKnowledgeUnitChapters.add(t_cmKnowledgeUnitChapter);
            String t_ChapterId=t_Chapter.getId();
            //创建章的kwa映射关系
            Map<String,CmKnowledgeUnitKwa> map_kwaid_to_kwa=new HashMap<>();
            List<CmKnowledgeUnitTree> t_cmKnowledgeUnitSections=t_cmKnowledgeUnitChapter.getChildren();
            //获取二级目录
            List<CmKnowledgeUnit> t_Sections=cmKnowledgeUnitMapper.getSection(t_ChapterId);
            //遍历二级（节）目录
            for(CmKnowledgeUnit t_Section:t_Sections){
                //获取当前节的kwa的列表
                List<CmKnowledgeUnitKwa> t_cmKnowledgekwas=cmKnowledgeUnitMapper.getKnowledgeUnitKwa(t_Section.getId());
                //创建该二级目录对象
                CmKnowledgeUnitTree t_cmKnowledgeUnitSection=new CmKnowledgeUnitTree(t_Section.getId(),t_Section.getName(),t_Section.getType(),t_cmKnowledgekwas,new ArrayList<>());
                //将该对象添加到相应一级目录的节列表
                t_cmKnowledgeUnitSections.add(t_cmKnowledgeUnitSection);
                for(CmKnowledgeUnitKwa t_kwa:t_cmKnowledgekwas){
                    //当前kwaid是否存在章中
                    if(map_kwaid_to_kwa.containsKey(t_kwa.getKwaid())==false){
                        //不存在，新增
                        CmKnowledgeUnitKwa t_chapterkwa=new CmKnowledgeUnitKwa(t_ChapterId,t_kwa.getKwaid(),t_kwa.getName(),t_kwa.getStatus());
                        //存到章kwa列表
                        t_cmKnowledgeUnitChapter.getKwas().add(t_chapterkwa);
                        //创建映射关系
                        map_kwaid_to_kwa.put(t_kwa.getKwaid(),t_chapterkwa);
                    }else{
                        //存在，处理statue
                        //获取章的statue
                        int t_statue=map_kwaid_to_kwa.get(t_kwa.getKwaid()).getStatus();
                        //都为完成时才算完成
                        t_statue=t_statue&t_kwa.getStatus();
                        //更新statue
                        map_kwaid_to_kwa.get(t_kwa.getKwaid()).setStatus(t_statue);
                    }

                }
            }
        }


        return Result.success(cmKnowledgeUnitChapters);
    }

    public Result insertChapter(CmKnowledgeUnit cmKnowledgeUnit){
        if(cmKnowledgeUnitMapper.getCourseCountByid(cmKnowledgeUnit.getCourseid())==0){
            return Result.error(404,"课程id不存在");
        }
        cmKnowledgeUnit.setId(CommonFunctions.generateEnhancedID("cm_knowledge_unit"));
        cmKnowledgeUnitMapper.insertChapter(cmKnowledgeUnit);
        return Result.success();
    }

    public Result insertSection(CmKnowledgeUnit cmKnowledgeUnit){
        if(cmKnowledgeUnitMapper.getCourseCountByid(cmKnowledgeUnit.getCourseid())==0){
            return Result.error(404,"课程id不存在");
        }
        if(cmKnowledgeUnitMapper.getUnitCountByUnitId(cmKnowledgeUnit.getPid())==0){
            return Result.error(404,"父节点id不存在");
        }
        cmKnowledgeUnit.setId(CommonFunctions.generateEnhancedID("cm_knowledge_unit"));
        cmKnowledgeUnitMapper.insertSection(cmKnowledgeUnit);
        return Result.success();
    }

    public Result insertKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa){
        if(cmKnowledgeUnitMapper.getUnitKwaCount(cmKnowledgeUnitKwa)!=0){
            return Result.error("该能力已存在");
        }
        cmKnowledgeUnitMapper.insertKnowledgeUnitKwa(cmKnowledgeUnitKwa);
        return Result.success();
    }
    public Result deleteKnowledgeUnitKwa(String unitid,List<String> kwaids){
        if(cmKnowledgeUnitMapper.getUnitCountByUnitId(unitid)==0){
            return Result.error("单元id不存在");
        }
        cmKnowledgeUnitMapper.deleteKnowledgeUnitKwa(unitid,kwaids);
        return Result.success();
    }
    public Result deleteKnowledgeUnit(List<String> unitids){
        //获取关联的unitids
        List<String> all_unitids=cmKnowledgeUnitMapper.selectAllUnitidByUnitids(unitids);
        //如果为空则返回错误
        if(all_unitids.isEmpty()){
            return Result.error(404,"没有可删除项");
        }
        //删除所有unitids的kwa
        cmKnowledgeUnitMapper.deleteKnowledgeUnitKwaByUnitids(all_unitids);
        //删除unit
        cmKnowledgeUnitMapper.deleteKnowledgeUnitByUnitids(all_unitids);
        return Result.success();
    }
    public Result updateKnowledgeUnit(CmKnowledgeUnit cmKnowledgeUnit){
        if(cmKnowledgeUnitMapper.getUnitCountByUnitId(cmKnowledgeUnit.getId())==0){
            return Result.error("此单元不存在");
        }
        cmKnowledgeUnitMapper.updateKnowledgeUnit(cmKnowledgeUnit);
        return Result.success();
    }
    public Result updateKnowledgeUnitKwa(CmKnowledgeUnitKwa cmKnowledgeUnitKwa){
        if(cmKnowledgeUnitMapper.getUnitKwaCount(cmKnowledgeUnitKwa)==0){
            return Result.error("此kwa不存在");
        }
        cmKnowledgeUnitMapper.updateKnowledgeUnitKwa(cmKnowledgeUnitKwa);
        return Result.success();
    }
}
