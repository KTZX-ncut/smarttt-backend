package com.example.smartttevaluation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.smartttcourse.mapper.CmCourseMapper;
import com.example.smartttcourse.pojo.CmClassroom;
import com.example.smartttevaluation.dto.ClassroomInfoReq;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.mapper.AttainmentEvaluationMapper;
import com.example.smartttevaluation.pojo.CmCourse;
import com.example.smartttevaluation.service.AttainmentEvaluationService;
import com.example.smartttevaluation.service.CmCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttainmentEvaluationImpl implements AttainmentEvaluationService {
    @Autowired
    private AttainmentEvaluationMapper attainmentEvaluationMapper;
    @Autowired
    private CmCourseAssessmentService cmCourseAssessmentService;

    @Override
    public Result checkRole(String obsId) {
        JSONObject res = new JSONObject();
        boolean isCourseManager = attainmentEvaluationMapper.checkRole(obsId) > 0;
        res.put("isCourseManager", isCourseManager);
        if (!isCourseManager) {
            res.put("classroomName", attainmentEvaluationMapper.getClassroomByClassroomId(obsId).getClassroomName());
        }
        return Result.success(res);
    }

    public void pakageClassroomInfo(ClassroomInfoReq classroomInfoReq, CmClassroom cmClassroom, String obsId) {
        classroomInfoReq.setClassroomId(cmClassroom.getId());
        classroomInfoReq.setClassroomName(cmClassroom.getClassroomName());
        classroomInfoReq.setTermName(attainmentEvaluationMapper.getTermName(cmClassroom.getTermId()));

        CmCourse course = attainmentEvaluationMapper.getCourseByCourseId(obsId);
        if(course == null)  // 如果是任课教师，要使用根据课堂id的查询函数
            course = attainmentEvaluationMapper.getCourseByClassroomId(obsId);

        classroomInfoReq.setCourseName(course.getCoursechinesename());
        classroomInfoReq.setProfessionName(attainmentEvaluationMapper.getProfessionName(obsId));
        classroomInfoReq.setTime(cmClassroom.getTeachTime() + cmClassroom.getLabTime() + cmClassroom.getPracticeTime());
        classroomInfoReq.setTeacherName(cmClassroom.getTeacherName());
    }

    @Override
    public Result getClassroomByCourseId(String obsId) {
        List<CmClassroom> classroomList = attainmentEvaluationMapper.getClassroomByCourseId(obsId);
        List<ClassroomInfoReq> classroomInfoReqs = new ArrayList<>();
        for (CmClassroom cmClassroom : classroomList) {
            ClassroomInfoReq classroomInfoReq = new ClassroomInfoReq();
            pakageClassroomInfo(classroomInfoReq, cmClassroom, obsId);
            classroomInfoReqs.add(classroomInfoReq);
        }
        return Result.success(classroomInfoReqs);
    }

    @Override
    public Result getClassroomByClassroomId(String obsId) {
        CmClassroom cmClassroom = attainmentEvaluationMapper.getClassroomByClassroomId(obsId);
        ClassroomInfoReq classroomInfoReq = new ClassroomInfoReq();
        pakageClassroomInfo(classroomInfoReq, cmClassroom, obsId);
        return Result.success(classroomInfoReq);
    }

    @Override
    public Result getCourseByClassroomId(String obsId) {
        return Result.success(attainmentEvaluationMapper.getCourseByClassroomId(obsId));
    }

    @Override
    public Result teacherGetAssessmentTable(String obsId) {
        // 获取课程id
        String courseId;
        courseId = attainmentEvaluationMapper.getCourseByClassroomId(obsId).getId();
        return cmCourseAssessmentService.getAssessmentTable(courseId);
    }
}
