package com.example.smartttcourse.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import com.example.smartttcourse.dto.SimpleCourse;
import com.example.smartttcourse.dto.Token;
import com.example.smartttcourse.mapper.CmTermMapper;
import com.example.smartttcourse.mapper.StUsersMapper;
import com.example.smartttcourse.pojo.CmCourse;
import com.example.smartttcourse.pojo.StRoleUser;
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
import java.util.List;

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
        try{
            for(SimpleCourse simpleCourse:simpleCourseList){
                simpleCourse.setTermname(cmCourseMapper.getTermName(simpleCourse.getSchooltermId()));
                simpleCourse.setResponsiblePersonList(cmCourseMapper.getCourseRP(simpleCourse.getId()));
            }
        }catch (NullPointerException e){
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
        if(CollectionUtil.isNotEmpty(classroomIdList)){
            cmClassRoomService.deleteClassroomRoleUser(classroomIdList);
        }
        cmCourseMapper.deleteCourseByID(ids);
        return Result.success();
    }

    @Override
    public Result historyCourseByTerm(String termID, String obsid) {
        return Result.success(cmCourseMapper.historyCourseByTerm(termID,obsid));
    }

    @Override
    public Result copyHistoryCourse(String id) {
        CmCourse cmCourse = cmCourseMapper.getCopyCourse(id);
        cmCourse.setId(generateEnhancedID("cm_course"));
        cmCourse.setSchooltermId(cmTermMapper.getCurrentTerm());
        cmCourseMapper.createCourse(cmCourse);
        List<StRoleUser> roleUserList = cmCourseMapper.getHistoryRP(id);
        for(StRoleUser stRoleUser: roleUserList){
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

}

