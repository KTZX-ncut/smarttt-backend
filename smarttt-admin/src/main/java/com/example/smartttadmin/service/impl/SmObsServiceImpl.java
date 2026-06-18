package com.example.smartttadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.smartttadmin.dto.*;
import com.example.smartttadmin.mapper.*;
import com.example.smartttadmin.pojo.*;
import com.example.smartttadmin.service.SmObsService;
import com.example.smartttadmin.service.StUsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.smartttadmin.Utils.CommonFunctions.*;
import static com.example.smartttadmin.Utils.JacksonJsonUtil.listToJsonArray;

@Service
public class SmObsServiceImpl extends ServiceImpl<SmObsMapper,SmObs> implements SmObsService {
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private StUsersMapper stUsersMapper;
    @Autowired
    private StLevelMapper stLevelMapper;
    @Autowired
    private StRolesMapper stRolesMapper;
    @Autowired
    private CmCourseMapper cmCourseMapper;

    @Autowired
    private StUsersService stUsersService;
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Result getAllCollageList() {
        List<ObsResponse> obsResponseList = smObsMapper.getAllCollegeList();
        for( ObsResponse obsResponse : obsResponseList){
            obsResponse.setResponsiblePersonList(stUsersMapper.getRPByObsID(obsResponse.getId()));
        }
        return Result.success(obsResponseList);
    }

    @Transactional
    @Override
    public Result createOneObs(SmObs smObs) {
        // 防止新建的机构深度不合法
        long obsdeep = smObs.getObsdeep();
        long maxDeep = smObsMapper.checkMaxObsdeep();
        if(obsdeep > maxDeep) return Result.error("超出最大层级\"" + maxDeep + "\"");

        if(smObs.getId() == null|| smObs.getId().isEmpty())smObs.setId(generateEnhancedID("sm_obs"));
        //获取兄弟的最大值,然后+1就是orderno
        long orderNoMax = smObsMapper.getSmObsListByPid(smObs.getPid()).stream().mapToLong(Long::valueOf).max().orElse(0);
        smObs.setOrderno(orderNoMax+1);
        smObs.setCreatetime(LocalDateTime.now().toString());
        List<SmObs> smObsList = smObsMapper.getAllSmObsList();
        smObsList.add(smObs);
        if(smObs.getLevelcode()==null){
            List<TreeStructure> treeStructureList = smObsList.stream()
                    .map(smObs_ex -> new TreeStructure(smObs_ex.getId(), smObs_ex.getPid(), smObs_ex.getOrderno()))
                    .collect(Collectors.toList());
            smObs.setLevelcode(generateLevelCode(generateTreeStructureList(treeStructureList,smObs.getId())));
        }
        try{
            smObsMapper.createOneNewObs(smObs);
            //添加学期标签
//            smObs.setObspath(currentTerm);
//            smObsMapper.createOneObsTerm(smObs);

        }catch (Exception e){
            return Result.error(502,"操作错误，或者名称重复");
        }
        return Result.success();
    }

    @Override
    public Result deleteOneObsByID(String id) {
        SmObs smObs = smObsMapper.getSmObsByID(id);
        if(smObs == null)return Result.error("删除教学单位出错");
        //把它的兄弟机构比它orderno大的orderno-1
        smObsMapper.updateBrotherObsOrderNo(id);
        smObsMapper.deleteObsByID(id);
        return Result.success();
    }

    @Override
    public Result deleteObssByIDS(List<String> ids) {
        List<SmObs> smObsList = smObsMapper.getSmObsByIDs(ids);
        if(smObsList.size()<ids.size())return Result.error(404,"批量删除教学单位出错");
        //按照从大到小排序
        try{
            smObsList.sort((o1, o2) -> Long.compare(o2.getObsdeep(), o1.getObsdeep()));
            List<String> deleteList = new LinkedList<>();
            for (SmObs smObs : smObsList){
                Queue<String> queue = new LinkedList<>();
                queue.add(smObs.getId());
                while(!queue.isEmpty()){
                    String now = queue.poll();
                    deleteList.add(now);
                    List<String> childId = smObsMapper.getSmObsIdByPid(now);
                    queue.addAll(childId);
                }
            }
            smObsMapper.deleteClassByIDs(deleteList);
            smObsMapper.deleteProfessionByIDs(deleteList);
            smObsMapper.deleteObsByIDs(deleteList);
            stRolesMapper.deleteRolesByObsid(deleteList);
            stUsersMapper.updateTeacherByObsid(deleteList);
            stUsersMapper.updateStudentByObsid(deleteList);
            smObsMapper.updateCourseProfession(deleteList);
        }catch (Exception e){
            return Result.error("删除错误");
        }
        return Result.success();
    }

    @Override
    public Result getObsTree() {
//        String currentTerm = stUsersMapper.getCurrentTerm();
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
    public Result createOnePersonnelRoster(PersonnelRoster personnelRoster) throws JsonProcessingException {
        int loginNameLen = personnelRoster.getLoginname().length();
        if(loginNameLen<3 || loginNameLen>15)
            return Result.error("用户名长度在3-15个字符之间");
        List<String> obsIDList = smObsMapper.getObsIDByObsName(personnelRoster.getObsname());
        if(obsIDList.isEmpty())
            return Result.error("所属院系/班级输入错误");
        List<String> stUsersList  = stUsersMapper.getStUsersByloginName(personnelRoster.getLoginname());
        if(!stUsersList.isEmpty())return Result.error("登录名已存在");
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
        if(levelID == 104){
            CmProfession cmProfession= new CmProfession(smObs);
            return updateOneProfession(cmProfession);
        }
        else if(levelID == 105) {
            CmClass cmClass = new CmClass(smObs);
            return updateClass(cmClass);
        }
        return Result.success();
    }

    @Override
    public Result getObsRPList(String obsid) {
        long teacherDeep = stLevelMapper.getTeacherLevel();
        List<ObsRPTree> allObsTree = smObsMapper.getRPTree(teacherDeep);
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
            if(cmProfession.getProname()!=null){
                cmCourseMapper.updateProfessionName(cmProfession);
            }
        }catch (NullPointerException e){
            return Result.error("更新失败");
        }
        return Result.success();
    }

    @Override
    public String getSchoolObs() {
        return smObsMapper.getSchoolObs().getId();
    }
    @Transactional
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


//    @Override
//    public List<String> getObsIdByObsName(String obsname) {
//        return smObsMapper.getObsIDByObsName(obsname);
//    }

    @Transactional
    @Override
    public Result copyHistoryObs(String copyTerm,String termid) throws JsonProcessingException {
//        String currentTerm = stUsersMapper.getCurrentTerm();
//        String historyTerm = getPreTerm(currentTerm);
//        List<SmObsTree> allObsTree = smObsMapper.getAllSmObsTree(copyTerm);
//        List<SmObsTree> smObsTrees = buildObsTreeByPid(allObsTree,"0");
//        change(smObsTrees,"0",termid);
        return Result.success();
    }

    @Override
    public String getPisById(String obsid) {
        SmObs obs = smObsMapper.selectById(obsid);
        return obs.getPid();
    }

    @Override
    public List<ProfessionResponse> getAllProfessionListA() {
        return smObsMapper.getAllProfessionListA();
    }

    @Transactional
    public void change(List<SmObsTree> smObsTrees, String pid, String termid) throws JsonProcessingException {
        log.debug("开始执行机构变更操作，参数：pid=" + pid + "，termid=" + termid + "，机构数量=" + (smObsTrees != null ? String.valueOf(smObsTrees.size()) : "0"));

        if (smObsTrees == null || smObsTrees.isEmpty()) {
            log.debug("机构列表为空，直接返回");
            return;
        }

        for (SmObsTree smObsTree : smObsTrees) {
            String newId = null;

            if (smObsTree == null) {
                log.debug("SmObsTree对象为null，跳过处理");
                continue;
            }

            log.debug("处理机构：id=" + smObsTree.getId() + "，name=" + smObsTree.getObsname());

            SmObs smObs = smObsMapper.getSmObsByID(smObsTree.getId());

            if (smObs == null) {
                log.debug("未找到对应的SmObs记录：id=" + smObsTree.getId());
                throw new RuntimeException("未找到ID为 " + smObsTree.getId() + " 的机构记录");
            }

            if (!Objects.equals(pid, "0")) {
                newId = generateEnhancedID("sm_obs");
                String oldId = smObs.getId();

                log.debug("更新机构ID：oldId=" + oldId + "，newId=" + newId + "，pid=" + pid);

                smObs.setId(newId);
                smObs.setPid(pid);
                smObs.setObsname(smObs.getObsname());

                // 1. 创建新机构记录
                try {
                    createOneObs(smObs);
                    log.debug("成功创建新机构记录：id=" + newId);

                    // 验证插入是否成功（通过影响行数）
                    // 注意：这里假设 createOneObs 返回 int，如果不返回可以改其他验证方式
                    SmObs justInserted = smObsMapper.getSmObsByID(newId);
                    String kk =  justInserted != null ? "找到" : "未找到";
                    log.debug("立即查询结果：{}"+kk);
                } catch (DataAccessException e) {
                    log.debug("创建机构记录失败：id=" + newId + "，错误=" + e.getMessage());
                    throw new RuntimeException("创建机构记录失败，ID=" + newId, e);
                } catch (Exception e) {
                    log.debug("创建机构记录发生未知异常：id=" + newId + "，错误=" + e.getMessage());
                    throw new RuntimeException("创建机构记录异常，ID=" + newId, e);
                }
                try {
                    checkSmObs(smObs);
                    log.debug("成功创建新子机构记录：id=" + newId);

                    // 验证插入是否成功（通过影响行数）
                    // 注意：这里假设 createOneObs 返回 int，如果不返回可以改其他验证方式
                } catch (DataAccessException e) {
                    log.debug("创建子机构记录失败：id=" + newId + "，错误=" + e.getMessage());
                    throw new RuntimeException("创建机构记录失败，ID=" + newId, e);
                } catch (Exception e) {
                    log.debug("创建子机构记录发生未知异常：id=" + newId + "，错误=" + e.getMessage());
                    throw new RuntimeException("创建子机构记录异常，ID=" + newId, e);
                }
                ;

                // 2. 处理人员关联
//                没有catelog
                List<PersonnelRoster> teacherList = stUsersMapper.getTeacherByObsid(oldId);
                List<PersonnelRoster> studentList = stUsersMapper.getStudentByObsid(oldId);

                List<PersonnelRoster> personnelRosterList = new ArrayList<>();
                personnelRosterList.addAll(teacherList);
                personnelRosterList.addAll(studentList);

                log.debug("找到人员记录：oldId=" + oldId + "，教师=" + teacherList.size() + "，学生=" + studentList.size() + "，总数=" + personnelRosterList.size());

                int successCount = 0;
                for (PersonnelRoster personnelRoster : personnelRosterList) {
                    personnelRoster.setObsid(newId);
                    stUsersService.updateOnePersonnelRoster(personnelRoster);

                }
                log.debug("人员关联更新完成，共更新" + personnelRosterList.size() + "条，成功验证" + successCount + "条");
            } else {
                newId = smObs.getId();
                log.debug("pid为0，保持原机构ID：" + newId);
            }

            if (smObsTree.getChildren() != null && !smObsTree.getChildren().isEmpty()) {
                log.debug("开始处理子节点：parentId=" + newId + "，子节点数量=" + smObsTree.getChildren().size());
                change(smObsTree.getChildren(), newId, termid);
                log.debug("子节点处理完成：parentId=" + newId);
            }
        }
        log.debug("机构变更操作完成：pid=" + pid + "，termid=" + termid);
    }

    private String getPreTerm(String currentTerm) {
        if(currentTerm.endsWith("秋")){
            int year = Integer.parseInt(currentTerm.substring(0, 4));
            year-=1;
            return year+"春";
        }
        else{
            return currentTerm.substring(0, 4)+"春";
        }
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
