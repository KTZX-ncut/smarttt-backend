package com.example.smartttevaluation.service.impl;

import com.example.smartttevaluation.dto.CmGetabilityTree;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.CmGetabilityMapper;
import com.example.smartttevaluation.pojo.CmAbility;
import com.example.smartttevaluation.pojo.CmGetability;
import com.example.smartttevaluation.service.CmGetabilityService;
import com.example.smartttevaluation.service.CmKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CmGetabilityServiceImpl implements CmGetabilityService {

    @Autowired
    private CmGetabilityMapper cmGetabilityMapper;

    //查询能力树
    @Override
    public Result getGetability(String courseid) {
        //查询课程id是否存在
        if (cmGetabilityMapper.getNumOfCourseById(courseid) == 0) {
            return Result.error(404, "课程id不存在");
        }
        return Result.success(getGetabilityList(courseid));
    }


    public List<CmGetabilityTree> getGetabilityList(String courseid){
        List<CmGetability> cmGetabilitys=cmGetabilityMapper.getGetability(courseid);
        Map<String,CmGetabilityTree> map_id_tree=new HashMap<>();
        //创建根节点
        CmGetabilityTree t_roottree=new CmGetabilityTree("0","root","","","","",courseid, new ArrayList<>());
        map_id_tree.put("0",t_roottree);
        for(CmGetability cmGetability : cmGetabilitys){
            //如果该id节点已经存在在树中则跳过
            if(map_id_tree.containsKey(cmGetability.getId())==true){
                continue;
            }
            //创建id树
            CmGetabilityTree t_idtree=new CmGetabilityTree(cmGetability.getId(),cmGetability.getName(),cmGetability.getDatavalue(),
                    cmGetability.getImportantlevel(), cmGetability.getLevelcode(),cmGetability.getRemark(),  courseid,new ArrayList<>());
            //获取能力id
            String t_id=cmGetability.getId();
            //获取能力id的pid
            String t_pid=cmGetabilityMapper.getPidById(t_id);
            //标记节点存在
            map_id_tree.put(t_id,t_idtree);
            //pid节点不存在时创建对应pid树
            while(map_id_tree.containsKey(t_pid)==false){
                //获取pid的能力信息
                CmAbility pid_cmability=cmGetabilityMapper.getAbilityById(t_pid);
                //创建pid树
                CmGetabilityTree t_pidtree=new CmGetabilityTree(t_pid, pid_cmability.getName(), pid_cmability.getDatavalue(),
                        pid_cmability.getImportantlevel(), pid_cmability.getLevelcode(),pid_cmability.getRemark(),courseid,new ArrayList<>());
                //将pid树加入map中
                map_id_tree.put(t_pid,t_pidtree);
                //将id树加入至pid子树列表
                t_pidtree.getChildren().add(t_idtree);
                //把当前要处理的能力id转为pid
                t_id=t_pid;
                //即将要把pidtree当作idtree处理
                t_idtree=t_pidtree;
                //获取新的pid
                t_pid=cmGetabilityMapper.getPidById(t_id);
            }
            //将id树加入至pid树子树列表
            map_id_tree.get(t_pid).getChildren().add(t_idtree);
        }
        return t_roottree.getChildren();
    }
    /*Y*/
    /*@Override
    public Result getAbility() {
        return Result.success(cmGetabilityMapper.getAbility());
    }*/

    //插入一条能力
    @Override
    public Result insertGetability(String courseid,List<String> ids) {
        //查询课程id是否存在
        if (cmGetabilityMapper.getNumOfCourseById(courseid) == 0) {
            return Result.error(404, "课程id不存在");
        }
        List<String> success_ids=new ArrayList<>();
        //查询能力id是否存在
        long cnt_non=0,cnt_exsit=0,cnt_success=0;
        for(String id:ids){
            //查询能力id是否在能力字典中
            if (cmGetabilityMapper.getNumOfAbilityById(id) == 0) {
                cnt_non++;
                continue;
            }
            //查询对应courseid中的能力id是否已存在
            if(cmGetabilityMapper.getNumOfAbilityIdAndCourseById(id,courseid)!=0){
                cnt_exsit++;
                continue;
            }
            //统计可插入的id列表
            cnt_success++;
            success_ids.add(id);
        }
        //统计失败信息
        String str_error="失败数量:"+(cnt_non+cnt_exsit)+",其中能力id不存在:"+cnt_non+",能力已存在:"+cnt_exsit;
        //没有可插入的数据
        if(success_ids.isEmpty()){
            return Result.error(404, str_error);
        }
        //插入数据
        cmGetabilityMapper.insertGetability(courseid,success_ids);
        String str="成功插入数量:"+cnt_success+","+str_error;
        return Result.success(str);
    }

    //通过id删除能力
    @Override
    public Result deleteGetabilityByID(String courseid, List<String> ids) {
        //查询课程id是否存在
        if (cmGetabilityMapper.getNumOfCourseById(courseid) == 0) {
            return Result.error(404, "课程id不存在");
        }
        cmGetabilityMapper.deleteGetabilityByIDs(courseid, ids);
        return Result.success();
    }
/*
    //更新能力信息
    @Override
    public Result updateGetability(CmGetability cmGetability) {
        //查询是否有要更改信息
        if(cmGetabilityMapper.getNumOfAbilityIdAndCourseById(cmGetability.getId(),cmGetability.getCourseid())==0){
            return Result.error(404,"未找到要更新的信息");
        }
        cmGetabilityMapper.updateGetabilityByID(cmGetability);
        return Result.success();
    }*/

}
