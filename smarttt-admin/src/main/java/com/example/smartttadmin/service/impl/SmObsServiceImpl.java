package com.example.smartttadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.mapper.SmObsMapper;
import com.example.smartttadmin.mapper.StLevelMapper;
import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.pojo.CmClass;
import com.example.smartttadmin.pojo.CmProfession;
import com.example.smartttadmin.pojo.SmObs;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.service.SmObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.smartttadmin.Utils.CommonFunctions.*;

@Service
public class SmObsServiceImpl extends ServiceImpl<SmObsMapper,SmObs> implements SmObsService {
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private StUsersMapper stUsersMapper;
    @Autowired
    private StLevelMapper stLevelMapper;

    @Override
    public Result getAllCollageList() {
        List<ObsResponse> obsResponseList = smObsMapper.getAllCollegeList();
        for( ObsResponse obsResponse : obsResponseList){
            obsResponse.setResponsiblePersonList(stUsersMapper.getRPByObsID(obsResponse.getId()));
        }
        return Result.success(obsResponseList);
    }

    @Override
    public Result createOneObs(SmObs smObs) {
        if(smObs.getId() == null|| smObs.getId().isEmpty())smObs.setId(generateEnhancedID("sm_obs"));
        //获取兄弟的最大值,然后+1就是orderno
        long orderNoMax = smObsMapper.getSmObsListByPid(smObs.getPid()).stream().mapToLong(Long::valueOf).max().orElse(0);
        smObs.setOrderno(orderNoMax+1);
        smObs.setCreatetime(LocalDateTime.now().toString());
        List<SmObs> smObsList = smObsMapper.getAllSmObsList();
        smObsList.add(smObs);
        List<TreeStructure> treeStructureList = smObsList.stream()
                .map(smObs_ex -> new TreeStructure(smObs_ex.getId(), smObs_ex.getPid(), smObs_ex.getOrderno()))
                .collect(Collectors.toList());
        smObs.setLevelcode(generateLevelCode(generateTreeStructureList(treeStructureList,smObs.getId())));
        smObsMapper.createOneNewObs(smObs);
        return Result.success();
    }

    @Override
    public Result deleteOneObsByID(String id) {
        List<SmObs> smObsList = smObsMapper.getSmObsByID(id);
        if(smObsList == null)return Result.error("删除教学单位出错");
        //把它的兄弟机构比它orderno大的orderno-1
        smObsMapper.updateBrotherObsOrderNo(id);
        smObsMapper.deleteObsByID(id);
        return Result.success();
    }

    @Override
    public Result deleteObssByIDS(List<String> ids) {
        List<SmObs> smObsList = smObsMapper.getSmObsByIDs(ids);
        if(smObsList.size()<ids.size())return Result.error(404,"批量删除教学单位出错");
        for(String id:ids){
            smObsMapper.updateBrotherObsOrderNo(id);
            //  删除pid为当前节点的id的所有节点
            smObsMapper.deleteObsByPid(id);
            // 删除当前节点
            smObsMapper.deleteObsByID(id);
        }
        return Result.success();
    }

    @Override
    public Result getObsTree() {
        List<SmObsTree> allObsTree = smObsMapper.getAllSmObsTree();
        return Result.success(buildObsTreeByPid(allObsTree,"0"));
    }
    private List<SmObsTree> buildObsTreeByPid(List<SmObsTree> allObsTree,String pid){
        Map<String, List<SmObsTree>> obsMap = allObsTree.stream()
                .collect(Collectors.groupingBy(SmObsTree::getPid,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingLong(SmObsTree::getOrderno))
                                        .collect(Collectors.toList())
                        )
                ));
        List<SmObsTree> rootObs =  obsMap.get(pid); // 根菜单的pid通常为0
        // 递归构建菜单树
        buildObsTree(rootObs,  obsMap);
        return rootObs;
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

    @Override
    public Result createOnePersonnelRoster(PersonnelRoster personnelRoster) {
        int loginNameLen = personnelRoster.getLoginname().length();
        if(loginNameLen<3 || loginNameLen>15)
            return Result.error("用户名长度在3-15个字符之间");
        List<String> obsIDList = smObsMapper.getObsIDByObsName(personnelRoster.getObsname());
        if(obsIDList.isEmpty())
            return Result.error("所属院系/班级输入错误");
        List<String> stUsersList  = stUsersMapper.getStUsersByloginName(personnelRoster.getLoginname());
        if(!stUsersList.isEmpty())return Result.error("用户名重复");
        String usersId = generateEnhancedID("st_users");
        personnelRoster.setId(usersId);
        personnelRoster.setCreatetime(LocalDateTime.now().toString());
        stUsersMapper.createOneStUsersByPersonnelRoster(personnelRoster);
        if(Objects.equals(personnelRoster.getCatelog(), "1")){
            stUsersMapper.createOneSmStudent(generateEnhancedID("sm_student"),
                    obsIDList.get(0),usersId,LocalDateTime.now().toString(),personnelRoster.getPersonnelno());
        }
        else {
            stUsersMapper.createOneSmTeacher(generateEnhancedID("sm_teacher"),
                    obsIDList.get(0),usersId,LocalDateTime.now().toString(),personnelRoster.getPersonnelno());
        }
        return Result.success();
    }

    /**
     * 教秘-升级
     * @param id
     * @return
     */
    @Override
    public Result upgradeOneObsByID(String id) {
        smObsMapper.updateBrotherObsOrderNo(id);
        long orderNoMax = smObsMapper.getSmObsListByPid(smObsMapper.getPidByID(id)).stream().mapToLong(Long::valueOf).max().orElse(0);
        smObsMapper.upgradeOneObsByID(id,orderNoMax);
        return Result.success();
    }

    @Override
    public Result getAllObsList(String ObsID) {
        //先获取操作范围obsid,然后查找下一层的儿子
        List<ObsResponse> obsResponseList = smObsMapper.getSmObsByPid(ObsID);
        for( ObsResponse obsResponse : obsResponseList){
            obsResponse.setResponsiblePersonList(stUsersMapper.getRPByObsID(obsResponse.getId()));
        }
        return Result.success(obsResponseList);
    }

    @Override
    public Result getAllProfessionList(String ObsID) {
        List<ObsResponse> obsResponseList = smObsMapper.getSmObsByPid(ObsID);
        List<String> ids = obsResponseList.stream()
                .map(ObsResponse::getId)
                .collect(Collectors.toList());
        List<ProfessionResponse> professionResponseList = smObsMapper.getProfessionByIDs(ids);
        for(ProfessionResponse professionResponse:professionResponseList){
            professionResponse.setResponsiblePersonList(stUsersMapper.getRPByObsID(professionResponse.getId()));
        }
        return Result.success(professionResponseList);
    }

    @Override
    public Result createOneProfession(CmProfession cmProfession) {
        cmProfession.setId(generateEnhancedID("cm_profession"));
        cmProfession.setCreatetime(LocalDateTime.now().toString());
        smObsMapper.createOneProfession(cmProfession);
        return Result.success();
    }

    @Override
    public Result getClassList(String ObsID) {
        List<ClassResponse> classResponseList = smObsMapper.getProfessionByPid(ObsID);
        for(ClassResponse classResponse:classResponseList){
            classResponse.setCmClassList(smObsMapper.getClassByProfessionID(classResponse.getId()));
        }
        return Result.success(classResponseList);
    }

    @Override
    public Result createOneClass(CmClass cmClass) {
        cmClass.setId(generateEnhancedID("cm_profession"));
        smObsMapper.createOneClass(cmClass);
        return Result.success();
    }

    @Override
    public Result updateClass(CmClass cmClass) {
        smObsMapper.updateClass(cmClass);
        SmObs smObs = new SmObs(cmClass);
        smObsMapper.updateObs(smObs);
        return Result.success();
    }

    @Override
    public Result updateOneObsByID(SmObs smObs) {
        smObsMapper.updateObs(smObs);
        long levelID = smObsMapper.checkProfession(smObs.getObsdeep());
//        System.out.println(levelID+"?????????");
        if(levelID == 104){
            CmProfession cmProfession= new CmProfession(smObs);
            smObsMapper.updateProfession(cmProfession);
        }
        else if(levelID == 105) {
            CmClass cmClass = new CmClass(smObs);
            smObsMapper.updateClass(cmClass);
        }
        return Result.success();
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
    public String upToTeacherObs(Token token) {
        long teacherLevel = stLevelMapper.getTeacherLevel();
        String obsID = token.getObsid();
        if(token.getObsdeep() > teacherLevel){
            long k =token.getObsdeep()-teacherLevel;
            for(int i=0;i<k;i++){
                obsID = smObsMapper.getPidByID(obsID);
            }
        }
        return obsID;
    }

    @Override
    public Result updateOneProfession(CmProfession cmProfession) {
        SmObs smObs = new SmObs(cmProfession);
        try{
            smObsMapper.updateObs(smObs);
            smObsMapper.updateProfession(cmProfession);
        }catch (NullPointerException e){
            return Result.error("更新失败");
        }
        return Result.success();
    }

    @Override
    public String getSchoolObs() {
        return smObsMapper.getSchoolObs().getId();
    }

    @Override
    public Result checkSmObs(SmObs smObs) {
        long levelID = smObsMapper.checkProfession(smObs.getObsdeep());
//        System.out.println(levelID+"?????????");
        if(levelID == 104){
            CmProfession cmProfession = new CmProfession(smObs);
            cmProfession.setId(generateEnhancedID("cm_profession"));
            cmProfession.setCreatetime(LocalDateTime.now().toString());
            smObsMapper.createOneProfession(cmProfession);
        }
        else if(levelID == 105){
            CmClass cmClass = new CmClass(smObs);
//            System.out.println(cmClass);
            cmClass.setId(generateEnhancedID("cm_class"));
            smObsMapper.createOneClass(cmClass);
        }
        return Result.success();
    }

    @Override
    public List<String> getObsIdByObsName(String obsname) {
        return smObsMapper.getObsIDByObsName(obsname);
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


    /**
     *
     * @param parentSmObs
     * @param obsMap
     */
    private void buildObsTree(List<SmObsTree> parentSmObs, Map<String, List<SmObsTree>>  obsMap) {
        for (SmObsTree parentObs : parentSmObs) {
            //找出pid为父菜单的孩子
            List<SmObsTree> childObs =  obsMap.get(parentObs.getId());
            if (childObs != null) {
                parentObs.setChildren(childObs);
                buildObsTree(childObs, obsMap);
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
