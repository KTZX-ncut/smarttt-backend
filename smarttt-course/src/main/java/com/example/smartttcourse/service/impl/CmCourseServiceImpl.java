package com.example.smartttcourse.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.mapper.StUsersMapper;
import com.example.smartttcourse.pojo.*;
import com.example.smartttcourse.service.CmClassRoomService;
import com.example.smartttcourse.service.CmCourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.mapper.CmCourseMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@Service
public class CmCourseServiceImpl implements CmCourseService {

    @Autowired
    private CmCourseMapper cmCourseMapper;
    @Autowired
    private CmTermMapper cmTermMapper;
    @Autowired
    private StUsersMapper stUsersMapper;

    @Resource
    private CmClassRoomService cmClassRoomService;

    @Override
    public Result getCourse(Token token) {
        List<SimpleCourse> simpleCourseList = cmCourseMapper.getCourse(token);
        try {
            for (SimpleCourse simpleCourse : simpleCourseList) {
                simpleCourse.setTermname(cmCourseMapper.getTermName(simpleCourse.getSchooltermId()));
                simpleCourse.setResponsiblePersonList(cmCourseMapper.getCourseRP(simpleCourse.getId()));
            }
        } catch (NullPointerException e) {
            return Result.error("获取失败");
        }
        return Result.success(simpleCourseList);
    }

    @Override
    public Result createCourse(CmCourse cmCourse) {
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setProfessionName(cmCourseMapper.getObsName(cmCourse.getProfessionId()));
        cmCourseMapper.createCourse(cmCourse);
        return Result.success(cmCourse.getId());
    }

    @Override
    @Transactional
    public Result deleteCourseByID(List<String> ids) {
        // 1. 删除课程下的对应的用户角色
        cmCourseMapper.deleteCourseRoleUser(ids);
        // 2. 删除课堂下的对应的用户角色
        List<String> classroomIdList = cmClassRoomService.getClassroomIdByCourseIdList(ids);
        if (CollectionUtil.isNotEmpty(classroomIdList)) {
            cmClassRoomService.deleteClassroomRoleUser(classroomIdList);
        }
        cmCourseMapper.deleteCourseByID(ids);
        return Result.success();
    }

    @Override
    public Result historyCourseByTerm(String termID, String obsid) {
        return Result.success(cmCourseMapper.historyCourseByTerm(termID, obsid));
    }

    @Override
    public Result copyHistoryCourse(String id) {
        CmCourse cmCourse = cmCourseMapper.getCopyCourse(id);
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setSchooltermId(cmTermMapper.getCurrentTerm());
        cmCourseMapper.createCourse(cmCourse);
        List<StRoleUser> roleUserList = cmCourseMapper.getHistoryRP(id);
        for (StRoleUser stRoleUser : roleUserList) {
//            stRoleUser.setRoleid("516761049-234512f3-7c19-4580-abe2-ebfb1dd8db21");
            stRoleUser.setId(generateEnhancedID("st_roleuser"));
//            stRoleUser.setObsdeep(-1);
            stRoleUser.setCreatetime(LocalDate.now().toString());
            stUsersMapper.createOneRoleUser(stRoleUser);
        }
        return Result.success();
    }

    @Override
    public Result updateOneCourse(CmCourse cmCourse) {
        cmCourseMapper.updateOneCourse(cmCourse);
        return Result.success();
    }

    @Override
    public Result getTeacherCourse(Token token) {
        return Result.success(cmCourseMapper.getTeacherOtherCourse(token));
    }

    /**
     * 根据courseId查出来termId
     */
    @Override
    public String getTermIdByCourseId(String courseId) {
        return cmCourseMapper.getTermIdByCourseId(courseId);
    }

    @Override
    public Integer countByCourseId(String courseIdOrClassroomId) {
        return cmCourseMapper.countByCourseId(courseIdOrClassroomId);
    }

    @Override
    public Result getPreCourseByCode(String termId, String obsId) {
        String courseCode = cmCourseMapper.getCourseCode(obsId);
        return Result.success(cmCourseMapper.getPreCourseByCode(termId, courseCode));
    }

    /**
     * 获取当前专业下的所有课程（不按学期过滤）
     */
    @Override
    public Result getAllCourses(Token token) {
        return Result.success(cmCourseMapper.getAllCourses(token.getObsid()));
    }

    @Transactional
    @Override
    public Result copyInfo(String pastId, String obsId) {
        // 旧-新keywordId的映射
        Map<String, String> keywordIdMap = new HashMap<>();
        // 复制关键字
        cmCourseMapper.deleteKeyword(obsId);
        List<CmKeywords> keywords = cmCourseMapper.getPastKeyword(pastId);
        for (CmKeywords keyword : keywords) {
            String id = keyword.getId();
            keyword.setId(generateEnhancedID("cm_keywords"));
            keywordIdMap.put(id, keyword.getId());
            keyword.setCourseid(obsId);
//            keyword.setCreateTime(LocalDateTime.now());
        }
        cmCourseMapper.copyKeyword(keywords);

        // 复制能力
        cmCourseMapper.deleteAbility(obsId);
        List<CmGetability> abilities = cmCourseMapper.getPastAbility(pastId);
        Map<String, String> abilityIdMap = new HashMap<>();
        for (CmGetability ability : abilities) {
            String oldAbilityId = ability.getId();
            ability.setId(generateEnhancedID("cm_getability"));
            abilityIdMap.put(oldAbilityId, ability.getId());
            ability.setCourseid(obsId);
        }
        if (!abilities.isEmpty()) cmCourseMapper.copyAbility(abilities);

        // 复制kwa
        cmCourseMapper.deleteKwa(obsId);
        List<CmKwadict> kwas = cmCourseMapper.getPastKwa(pastId);
        Map<String, String> kwaIdMap = new HashMap<>();
        for (CmKwadict kwa : kwas) {
            String id = kwa.getId();
            kwa.setId(generateEnhancedID("cm_kwadict"));
            kwaIdMap.put(id, kwa.getId());
            kwa.setKeywordid(keywordIdMap.get(kwa.getKeywordid()));
            kwa.setAbilityid(abilityIdMap.get(kwa.getAbilityid()));
            kwa.setCourseid(obsId);
        }
        if (!kwas.isEmpty()) cmCourseMapper.copyKwa(kwas);

        // 先存下来未进行删除操作前的当前课程的知识单元，用于后续删除与这些知识单元挂钩的kwa条目
        // （因为cm_course_unit_kwa表无courseId标识）
        List<CmKnowledgeUnit> beforeDeleteCourseUnits = cmCourseMapper.getPastUnit(obsId);
        // 复制知识单元
        cmCourseMapper.deleteUnit(obsId);
        List<CmKnowledgeUnit> units = cmCourseMapper.getPastUnit(pastId);
        // 旧-新unitId映射
        Map<String, String> unitIdMap = new HashMap<>();
        // 父id-子节点映射
        Map<String, List<CmKnowledgeUnit>> childrenMap = new HashMap<>();
        for (CmKnowledgeUnit unit : units) {
            String pid = unit.getPid();
            childrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(unit);
        }
        // 入队根节点
        List<CmKnowledgeUnit> roots = childrenMap.get("0");
        if (roots == null || roots.isEmpty()) {
            return Result.error("无有效的根知识单元");
        }
        Queue<CmKnowledgeUnit> queue = new LinkedList<>(roots);

        while (!queue.isEmpty()) {
            CmKnowledgeUnit current = queue.poll();
            String oldId = current.getId();
            String newId = generateEnhancedID("cm_course_unit");
            current.setId(newId);
            current.setCourseid(obsId);
            unitIdMap.put(oldId, newId);

            List<CmKnowledgeUnit> children = childrenMap.get(oldId);
            if (children != null) {
                for (CmKnowledgeUnit child : children) {
                    child.setPid(newId);
                    queue.offer(child);
                }
            }
        }
        cmCourseMapper.copyUnit(units);

        // 复制知识单元kwa
        for (CmKnowledgeUnit unit : beforeDeleteCourseUnits) cmCourseMapper.deleteUnitKwa(unit.getId());
        List<CmKnowledgeUnitKwa> unitKwas = new ArrayList<>();
        for (Map.Entry<String, String> entry : unitIdMap.entrySet()) {
            unitKwas.addAll(cmCourseMapper.getPastUnitKwa(entry.getKey()));
        }
        for (CmKnowledgeUnitKwa unitKwa : unitKwas) {
            unitKwa.setId(generateEnhancedID("cm_course_unit_kwa"));
            unitKwa.setUnitid(unitIdMap.get(unitKwa.getUnitid()));
            unitKwa.setKwaid(kwaIdMap.get(unitKwa.getKwaid()));
        }
        cmCourseMapper.copyUnitKwa(unitKwas);

        // 复制2D图谱连线数据
        cmCourseMapper.deleteLine(obsId);
        List<CmLines> lines = cmCourseMapper.getPastLine(pastId);
        for (CmLines line : lines) {
            line.setId(generateEnhancedID("cm_lines"));
            line.setStartunitid(unitIdMap.get(line.getStartunitid()));
            line.setEndunitid(unitIdMap.get(line.getEndunitid()));
            line.setStartkwaid(kwaIdMap.get(line.getStartkwaid()));
            line.setEndkwaid(kwaIdMap.get(line.getEndkwaid()));
            line.setCourseid(obsId);
        }
        cmCourseMapper.copyLine(lines);

        // 复制课程目标
        cmCourseMapper.deleteTarget(obsId);
        List<CmCoursetarget> targets = cmCourseMapper.getPastTarget(pastId);
        // 旧-新targetId映射
        Map<String, String> targetIdMap = new HashMap<>();
        for (CmCoursetarget target : targets) {
            String id = target.getId();
            target.setId(generateEnhancedID("cm_course_target"));
            targetIdMap.put(id, target.getId());
            target.setCourseid(obsId);
        }
        cmCourseMapper.copyTarget(targets);

        // 复制课程目标关联的kwa
        cmCourseMapper.deleteTargetKwa(obsId);
        List<CmCoursetargetKwa> targetKwas = cmCourseMapper.getPastTargetKwa(pastId);
        for (CmCoursetargetKwa targetKwa : targetKwas) {
            targetKwa.setId(generateEnhancedID("cm_course_target_kwa"));
            targetKwa.setTargetId(targetIdMap.get(targetKwa.getTargetId()));
            targetKwa.setKwaId(kwaIdMap.get(targetKwa.getKwaId()));
            targetKwa.setObsId(obsId);
        }
        cmCourseMapper.copyTargetKwa(targetKwas);

        // 复制考核项
        cmCourseMapper.deleteCheckitem(obsId);
        List<CmCheckitem> checkitems = cmCourseMapper.getPastCheckitem(pastId);
        // 旧-新考核项Id映射
        Map<String, String> checkitemIdMap = new HashMap<>();
        // 父-子id节点映射
        Map<String, List<CmCheckitem>> checkitemChildrenMap = new HashMap<>();
        for (CmCheckitem checkitem : checkitems) {
            String pid = checkitem.getPid();
            checkitemChildrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(checkitem);
        }
        List<CmCheckitem> checkitemRoots = checkitemChildrenMap.get("0");
        if (checkitemRoots == null || checkitemRoots.isEmpty()) {
            return Result.error("无有效根考核项");
        }
        Queue<CmCheckitem> checkitemQueue = new LinkedList<>(checkitemRoots);
        while (!checkitemQueue.isEmpty()) {
            CmCheckitem current = checkitemQueue.poll();
            String oldId = current.getId();
            String newId = generateEnhancedID("cm_course_checkitem");
            current.setId(newId);
            current.setCourseid(obsId);
            checkitemIdMap.put(oldId, current.getId());

            List<CmCheckitem> children = checkitemChildrenMap.get(oldId);
            if (children != null) {
                for (CmCheckitem child : children) {
                    child.setPid(newId);
                    checkitemQueue.offer(child);
                }
            }
        }
        cmCourseMapper.copyCheckitem(checkitems);

        // 复制考核方案分数
        cmCourseMapper.deleteAssessment(obsId);
        List<CmCourseAssessment> assessments = cmCourseMapper.getPastAssessment(pastId);
        for (CmCourseAssessment assessment : assessments) {
            assessment.setId(generateEnhancedID("cm_course_assessment"));
            assessment.setCourseid(obsId);
            assessment.setCoursetargetId(targetIdMap.get(assessment.getCoursetargetId()));
            assessment.setCheckitemId(checkitemIdMap.get(assessment.getCheckitemId()));
        }
        cmCourseMapper.copyAssessment(assessments);

        return Result.success();
    }

    @Transactional
    @Override
    public Result copyFormative(String pastId, String obsId) {
        Map<String, String> keywordIdMap = new HashMap<>();
        cmCourseMapper.deleteKeyword(obsId);
        List<CmKeywords> keywords = cmCourseMapper.getPastKeyword(pastId);
        for (CmKeywords keyword : keywords) {
            String id = keyword.getId();
            keyword.setId(generateEnhancedID("cm_keywords"));
            keywordIdMap.put(id, keyword.getId());
            keyword.setCourseid(obsId);
        }
        if (!keywords.isEmpty()) cmCourseMapper.copyKeyword(keywords);

        cmCourseMapper.deleteAbility(obsId);
        List<CmGetability> abilities = cmCourseMapper.getPastAbility(pastId);
        Map<String, String> abilityIdMap = new HashMap<>();
        for (CmGetability ability : abilities) {
            String oldAbilityId = ability.getId();
            ability.setId(generateEnhancedID("cm_getability"));
            abilityIdMap.put(oldAbilityId, ability.getId());
            ability.setCourseid(obsId);
        }
        if (!abilities.isEmpty()) cmCourseMapper.copyAbility(abilities);

        return Result.success();
    }
}

