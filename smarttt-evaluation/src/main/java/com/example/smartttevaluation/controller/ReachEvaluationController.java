package com.example.smartttevaluation.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttevaluation.dto.StudentDynamicStateReq;
import com.example.smartttevaluation.dto.StudentReachStateReq;
import com.example.smartttevaluation.exception.cus.BusinessException;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.CmClassroomStudent;
import com.example.smartttevaluation.pojo.FeAssessmentCategories;
import com.example.smartttevaluation.pojo.FeCourseObjectives;
import com.example.smartttevaluation.service.CmClassroomStudentService;
import com.example.smartttevaluation.service.FeAssessmentCategoriesService;
import com.example.smartttevaluation.service.FeCourseObjectivesService;
import com.example.smartttevaluation.service.ReachEvaluationService;
import com.example.smartttevaluation.vo.ReachCategoryEvaluationVO;
import com.example.smartttevaluation.vo.ReachObjectiveEvaluationVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reach-evaluation")
@AllArgsConstructor
public class ReachEvaluationController {

    private final ReachEvaluationService reachEvaluationService;
    private final FeCourseObjectivesService feCourseObjectivesService;

    private final CmClassroomStudentService classroomStudentService;

    private final FeAssessmentCategoriesService feAssessmentCategoriesService;
    @GetMapping("/calculate")
    public Result calculate(@RequestParam("classroomId") String classroomId) {
        // 根据classroomId 获取对应的课堂id
        String courseId = reachEvaluationService.getCourseIdByClassroomId(classroomId);
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId), ResponseEnum.CLASSROOM_ID_NOT_EXIST);
        // 获取所有的课堂目标
        QueryWrapper<FeCourseObjectives> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<FeCourseObjectives> courseObjectivesList = feCourseObjectivesService.list(wrapper);
        // 获取该课堂下所有的学生
        List<CmClassroomStudent> classroomStudentList = classroomStudentService.list(new QueryWrapper<CmClassroomStudent>().eq("classroomId", classroomId));
        // 获取所有的考核类型
        List<FeAssessmentCategories> categoriesList = feAssessmentCategoriesService.list(new QueryWrapper<FeAssessmentCategories>().eq("course_id", courseId));
        // 计算每一个学生每一个目标的达成度
        reachEvaluationService.calculate(classroomStudentList, courseObjectivesList,categoriesList,classroomId,courseId);
        return Result.ok().code(200).msg("计算完成！");
    }

    @GetMapping("/getReachCategoryEvaluation")
    public Result getReachCategoryEvaluation(@RequestParam("classroomId") String classroomId) {
        // 根据classroomId 查询考核类型维度的达成性评价结果数据
        List<ReachCategoryEvaluationVO> reachCategoryEvaluationList =reachEvaluationService.getReachCategoryEvaluation(classroomId);
        // 组装数据
        for (ReachCategoryEvaluationVO reachCategoryEvaluation : reachCategoryEvaluationList){
            // 根据学生的user_id获取 学号， 姓名
            String stuNo = reachEvaluationService.getStuNoByUserId(reachCategoryEvaluation.getUserId());
            String studentName = reachEvaluationService.getStudentNameByUserId(reachCategoryEvaluation.getUserId());
            reachCategoryEvaluation.setStuNo(stuNo);
            reachCategoryEvaluation.setStudentName(studentName);
        }
        return Result.ok().code(200).data(reachCategoryEvaluationList.stream().collect(Collectors.groupingBy(ReachCategoryEvaluationVO::getUserId)));
    }

    @GetMapping("/getReachObjectiveEvaluation")
    public Result getReachObjectiveEvaluation(@RequestParam("classroomId") String classroomId) {
        // 根据classroomId 查询课程目标的达成性评价结果数据
        List<ReachObjectiveEvaluationVO> reachObjectiveEvaluationList =reachEvaluationService.getReachObjectiveEvaluation(classroomId);
        // 组装数据
        for (ReachObjectiveEvaluationVO reachObjectiveEvaluation : reachObjectiveEvaluationList){
            // 根据学生的user_id获取 学号， 姓名
            String stuNo = reachEvaluationService.getStuNoByUserId(reachObjectiveEvaluation.getUserId());
            String studentName = reachEvaluationService.getStudentNameByUserId(reachObjectiveEvaluation.getUserId());
            reachObjectiveEvaluation.setStuNo(stuNo);
            reachObjectiveEvaluation.setStudentName(studentName);
        }
        return Result.ok().code(200).data(reachObjectiveEvaluationList.stream().collect(Collectors.groupingBy(ReachObjectiveEvaluationVO::getUserId)));
    }

    /**
     * 获取本课堂所有学生下的userId
     */
    @GetMapping("/getUserIdList")
    public Result getStudentNameByUserId(@RequestParam("classroomId") String classroomId) {
        List<String> userIdList = reachEvaluationService.getUserIdList(classroomId);
        return Result.ok().code(200).data(userIdList);
    }


    /**
     * 修改学生是否参与达成性评价的状态
     * 接受的是classroom_student表中的id和reach_state
     * @return
     */
    @PutMapping("/modifyStudentReachState")
    Result getStudentEvalNums(@RequestBody List<StudentReachStateReq> studentReachStateReqList){
        // 检验数据
        if(Objects.isNull(studentReachStateReqList) || studentReachStateReqList.size() == 0){
            return Result.error("studentReachStateReqList不能为空");
        }
        for (StudentReachStateReq studentReachStateReq : studentReachStateReqList){
            SmartAssert.checkExpression(!StrUtil.isBlank(studentReachStateReq.getClassroomStudentId()),ResponseEnum.CLASSROOM_STUDENT_ID_IS_NOT_NULL);
            if(studentReachStateReq.getReachState() == null){
                throw new BusinessException("reachState不能为空");
            }
            if(studentReachStateReq.getReachState() != 0 && studentReachStateReq.getReachState() != 1){
                throw new BusinessException("reachState参数只能是0或者1");
            }
        }
        boolean state = reachEvaluationService.modifyStudentReachState(studentReachStateReqList);
        return Result.success(state);
    }
}
