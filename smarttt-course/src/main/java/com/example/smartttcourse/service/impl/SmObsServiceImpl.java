package com.example.smartttcourse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttcourse.dto.ObsRPTree;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.mapper.SmObsMapper;
import com.example.smartttcourse.mapper.StUsersMapper;
import com.example.smartttcourse.pojo.SmObs;
import com.example.smartttcourse.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SmObsServiceImpl extends ServiceImpl<SmObsMapper, SmObs> implements SmObsService {
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private StUsersMapper stUsersMapper;

    @Override
    public String getSchoolObs() {
        return smObsMapper.getSchoolObs().getId();
    }

    @Override
    public Result getObsRPList(String obsid) {
        List<ObsRPTree> allObsTree = smObsMapper.getRPTree();
        Map<String, List<ObsRPTree>> obsMap = allObsTree.stream()
                .collect(Collectors.groupingBy(ObsRPTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingLong(ObsRPTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));

        List<ObsRPTree> rootObs =  obsMap.get(obsid);
        // 递归构建菜单树
        try{
            buildObsRPTree(rootObs,  obsMap);
        }catch (NullPointerException e){
            return Result.error("未找到结果");
        }

        return Result.success(rootObs);
    }

    @Override
    public Result getObsRPStudentList(String obsID) {
        return null;
    }

    private void buildObsRPTree(List<ObsRPTree> parentSmObs, Map<String, List<ObsRPTree>> obsMap) {
        for (ObsRPTree parentObs : parentSmObs) {
            parentObs.setResponsiblePerson(stUsersMapper.getAllTeacherByObsID(parentObs.getId()));
            //找出pid为父菜单的孩子
            List<ObsRPTree> childObs =  obsMap.get(parentObs.getId());
            if (childObs != null) {
                parentObs.setChildren(childObs);
                buildObsRPTree(childObs, obsMap);
            }
        }
    }
}
