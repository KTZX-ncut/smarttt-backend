package com.example.smartttevaluation.controller;

import com.example.smartttcourse.Utils.AuthorizationAspect;
import com.example.smartttevaluation.Utils.AuthRequired;
import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.service.AttainmentEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttevaluation.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/evaluation/attainment")
//达成性评价
public class AttainmentEvaluationController {
    @Autowired
    private AttainmentEvaluationService attainmentEvaluationService;

    /**
     * 查询是不是课程负责人
     */
    @GetMapping("")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549", isReadOnly = true)
    public Result checkRole(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return attainmentEvaluationService.checkRole(token.getObsid());
    }

    /**
     * 获取课堂的学生列表
     */
    @GetMapping("getClassroomStuList")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549")
    public Result getStuListByClassroomId(@RequestParam("classroomId") String classroomId) {
        return attainmentEvaluationService.getClassroomStuList(classroomId);
    }

    /**
     * 获取课程下的所有课堂的信息
     */
    @GetMapping("/getClassroomByCourseId")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549", isReadOnly = true)
    public Result getClassroomByCourseId(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return attainmentEvaluationService.getClassroomByCourseId(token.getObsid());
    }
    /**
     * 根据课堂id获取课堂信息
     */
    @GetMapping("getClassroomByClassroomId")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549", isReadOnly = true)
    public Result getClassroomByClassroomId(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return attainmentEvaluationService.getClassroomByClassroomId(token.getObsid());
    }
    /**
     * 根据课堂id获取课程信息
     */
    @GetMapping("getCourseByClassroomId")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549", isReadOnly = true)
    public Result getCourseByClassroomId(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return attainmentEvaluationService.getCourseByClassroomId(token.getObsid());
    }

    /**
     * 计算总评成绩和课程目标达成度
     */
    @GetMapping("calc")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549")
    public Result calc(HttpServletRequest request) {
        Token token = getTokenFromContext();
        return attainmentEvaluationService.calcTotalScore(token.getObsid());
    }

    /**
     * 获取总评成绩
     */
    @GetMapping("getTotalScore")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549")
    public Result getTotalScore(@RequestParam("classroomId") String classroomId) {
        return attainmentEvaluationService.getTotalScore(classroomId);
    }

    /**
     * 获取课程目标达成度
     */
    @GetMapping("getTargetAchievement")
    @AuthRequired(type = "admin", menu = "531500340-ce704fab-6961-4091-a671-ce8d9e6b6549")
    public Result getTargetAchievement(@RequestParam("classroomId") String classroomId) {
        return attainmentEvaluationService.getTargetAchievement(classroomId);
    }
}
