package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.CollegeResponse;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.SmObsTree;
import com.example.smartttadmin.mapper.SmObsMapper;
import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.smartttadmin.pojo.EnhancedUniqueID.generateEnhancedID;

@Service
public class SmObsServiceImpl implements SmObsService {
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private StUsersMapper stUsersMapper;
    @Override
    public Result getAllCollageList() {
        List<CollegeResponse> collegeResponseList= smObsMapper.getAllCollegeList();
        for( CollegeResponse collegeResponse : collegeResponseList){
            collegeResponse.setStUsersList(stUsersMapper.getStUsersByobsid(collegeResponse.getId()));
        }
        return Result.success(collegeResponseList);
    }

    @Override
    public Result createOneObs(SmObs smObs) {
        smObs.setId(generateEnhancedID("sm_obs"));
        smObs.setCreatetime(LocalDateTime.now().toString());
        smObsMapper.createOneNewCollege(smObs);
        return Result.success();
    }

    @Override
    public Result deleteObsByID(String id) {
        smObsMapper.deleteCollegeByID(id);
        return Result.success();
    }

    @Override
    public Result getObsTree() {
        List<SmObsTree> allObsTree = smObsMapper.getAllSmObsTree();
        Map<String, List<SmObsTree>> obsMap = allObsTree.stream()
                .collect(Collectors.groupingBy(SmObsTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingInt(SmObsTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));
        List<SmObsTree> rootObs =  obsMap.get("0"); // 根菜单的pid通常为null
        // 递归构建菜单树
        buildObsTree(rootObs,  obsMap);
        return Result.success(rootObs);
    }

    @Override
    public Result getPersonnelRosterByObsIDAndCatelog(String obsid, String catelog) {
        List<SmObs> AllObs = smObsMapper.getAllSmObsList();
        Map<String, List<SmObs>> obsMap = AllObs.stream()
                .collect(Collectors.groupingBy(SmObs::getPid));
        List<SmObs> rootObs = obsMap.get(obsid);
        List<String> obsChildrenList = new ArrayList<>();
        obsChildrenList.add(obsid);
        if(rootObs!=null)obsChildrenList.addAll(getObsChildren(rootObs,obsMap));
        List<PersonnelRoster> personnelRosterList = new ArrayList<>();
        if(Objects.equals(catelog, "1")){
            for(String obs : obsChildrenList){
                List<PersonnelRoster> studentPRList = smObsMapper.getStudentPRByObsIDAndCatelog(obs,catelog);
                if(studentPRList!=null) personnelRosterList.addAll(studentPRList);
            }
        }
        else{
            for(String obs : obsChildrenList){
                List<PersonnelRoster> teacherPRList = smObsMapper.getTeacherPRByObsIDAndCatelog(obs,catelog);
                if(teacherPRList!=null)personnelRosterList.addAll(teacherPRList);
            }
        }
        return Result.success(personnelRosterList);
    }

    /**
     *
     * @param parentSmObs
     * @param ObsMap
     */
    private void buildObsTree(List<SmObsTree> parentSmObs, Map<String, List<SmObsTree>>  ObsMap) {
        for (SmObsTree parentObs : parentSmObs) {
            //找出pid为父菜单的孩子
            List<SmObsTree> childObs =  ObsMap.get(parentObs.getId());
            if (childObs != null) {
                parentObs.setChildren(childObs);
                buildObsTree(childObs, ObsMap);
            }
        }
    }

    /**
     * 递归输出某机构的所有children
     * @param parentSmObs
     * @param ObsMap
     * @return
     */
    public static List<String> getObsChildren(List<SmObs> parentSmObs, Map<String, List<SmObs>>  ObsMap){
        List<String> obsChildren = parentSmObs.stream()
                        .map(SmObs::getId)
                        .collect(Collectors.toList());
        for(SmObs parentObs :parentSmObs){
            List<SmObs> childObs =  ObsMap.get(parentObs.getId());
            if (childObs != null) {
                obsChildren.addAll(getObsChildren(childObs, ObsMap));
            }
        }
        return obsChildren;
    }
}
