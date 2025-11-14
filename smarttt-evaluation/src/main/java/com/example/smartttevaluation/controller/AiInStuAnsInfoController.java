package com.example.smartttevaluation.controller;

import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.dto.CalculatePortraitReq;
import com.example.smartttevaluation.dto.PaperInfoDto;
import com.example.smartttevaluation.dto.StudentDynamicStateReq;
import com.example.smartttevaluation.dto.StudentPortraitReq;
import com.example.smartttevaluation.event.CalculateRequestEvent;
import com.example.smartttevaluation.exception.cus.BusinessException;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.service.AiInStuAnsInfoService;
import com.example.smartttevaluation.vo.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 形成型评价模块的相关代码
 */
@RestController
@RequestMapping("/dynamic/portrait")
public class AiInStuAnsInfoController {

    @Resource
    private AiInStuAnsInfoService aiInStuAnsInfoService;

    @Resource
    private ApplicationEventPublisher  applicationEventPublisher;


    /**
     * 根据课堂id获取课程id
     * */
    @GetMapping("getCourseId")
    public Result getCourseIdByClassroomId(@RequestParam("classroomId") String classroomId){
        SmartAssert.checkExpression(StrUtil.isNotBlank(classroomId),ResponseEnum.CLASSROOM_ID_NOT_NULL);
        String courseId = aiInStuAnsInfoService.getCourseIdByClassroomId(classroomId);
        if (StrUtil.isBlank(courseId)){
            return Result.error(-600,"非法的classroomId");
        }
        return Result.success(courseId);

    }


    /**
     * 查询课堂列表(支持课堂名称模糊查询)
     */
    @GetMapping("/getClassroomList")
    public Result getClassroomList(@RequestParam("courseId") String courseId,
                                   @RequestParam(value = "classroomName", required = false) String classroomName) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId), ResponseEnum.COURSE_ID_NOT_NULL);
        List<ClassroomVO> classroomVOList = aiInStuAnsInfoService.getClassroomList(courseId, classroomName);
        return Result.success(classroomVOList);
    }

    /**
     * 获取考试下的所有学生信息
     */
    @GetMapping("/getTestStudent")
    public Result getTestStudentList(@RequestParam("testId") String testId) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(testId), ResponseEnum.TEST_ID_NOT_NULL);
        List<TestStudentVO> testStudentVOList = aiInStuAnsInfoService.getTestStudentList(testId);
        return Result.success(testStudentVOList);
    }

    /**
     * 获取考试/试卷信息
     */
    @GetMapping("/testPaperInfo")
    public Result getTestPaperInfo(@RequestParam("courseId") String courseId,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "classroomId", required = false) String classroomId) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId), ResponseEnum.COURSE_ID_NOT_NULL);
        List<TestPaperInfoVO> testPaperInfoVOList = aiInStuAnsInfoService.getTestPaperInfo(courseId, search,classroomId);
        return Result.success(testPaperInfoVOList);
    }

    /**
     * 形成性评价画像
     */
    @GetMapping("/student")
    public Result getStudentPortrait(StudentPortraitReq studentPortraitReq) {
        // stuId (userId)  is not null
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getStuId()), ResponseEnum.STUID_IS_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getClassroomId()), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getCourseId()), ResponseEnum.COURSE_ID_NOT_NULL);
        SmartAssert.checkExpression(Objects.nonNull(studentPortraitReq.getNum()), ResponseEnum.NUM_NOT_NULL);
        StudentPortraitVO studentPortraitVO =  aiInStuAnsInfoService.getStudentPortrait(studentPortraitReq.getCourseId(),
                studentPortraitReq.getClassroomId(),
                studentPortraitReq.getStuId(),
                studentPortraitReq.getNum());
        return Result.success(studentPortraitVO);
    }

    /**
     * 学生试卷评价
     */
    @GetMapping("/studentPaper")
    public Result studentPaper(@RequestParam("stuId") String stuId,
                               @RequestParam("paperId") String paperId,
                               @RequestParam("courseId") String courseId,
                               @RequestParam("classroomId") String classroomId) {
        // stuId (userId)  is not null
        SmartAssert.checkExpression(!StrUtil.isBlank(stuId), ResponseEnum.STUID_IS_NOT_NULL);
        // testId is not null
        SmartAssert.checkExpression(!StrUtil.isBlank(paperId), ResponseEnum.PAPER_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(classroomId), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(courseId), ResponseEnum.COURSE_ID_NOT_NULL);

        PaperInfoDto paperInfoDto = new PaperInfoDto();
        paperInfoDto.setPaperId(paperId);
        StudentPaperPortraitVO studentPaperPortraitVO = aiInStuAnsInfoService.getStudentPaperPortrait(Arrays.asList(paperInfoDto), stuId, courseId, classroomId);

        return Result.success(studentPaperPortraitVO);
    }


    /**
     *  课堂评价
     */
    @GetMapping("/classroom")
    public Result getClassroomPortrait(StudentPortraitReq studentPortraitReq) {
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getClassroomId()), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getCourseId()), ResponseEnum.COURSE_ID_NOT_NULL);
        SmartAssert.checkExpression(Objects.nonNull(studentPortraitReq.getNum()), ResponseEnum.NUM_NOT_NULL);
        StudentPortraitVO studentPortraitVO =  aiInStuAnsInfoService.getClassroomPortrait(studentPortraitReq.getCourseId(),studentPortraitReq.getClassroomId(),studentPortraitReq.getNum());
        return Result.success(studentPortraitVO);
    }

    /**
     * 一键计算形成行评价
     */
    @PostMapping("/calculate")
    public Result calculate(@RequestBody CalculatePortraitReq calculatePortraitReq){
        // 校参
        SmartAssert.checkExpression(StrUtil.isNotBlank(calculatePortraitReq.getCourseId()),ResponseEnum.COURSE_ID_NOT_NULL);
        SmartAssert.checkExpression(StrUtil.isNotBlank(calculatePortraitReq.getClassroomId()),ResponseEnum.CLASSROOM_ID_NOT_NULL);

        // 发布事件
        CalculateRequestEvent calculateRequestEvent = new CalculateRequestEvent(this, calculatePortraitReq);
        applicationEventPublisher.publishEvent(calculateRequestEvent);
        // 一键计算算的是：学生的评价分析，课堂的评价分析。（而且不止一次）
        // 为了更好的扩展，留出了 试卷 和 学生的 口子
        // 一个学生 16 次循环，假设有120个学生 16 * 120，访问数据库次数耗时。
        // 一个课堂16次循环。
        boolean succeed = aiInStuAnsInfoService.calculatePortrait(calculatePortraitReq);
        return Result.ok().data(calculateRequestEvent.getTaskId()).code(200);
    }

    /**
     * 查询异步任务完成进度
     */
    @GetMapping("/task")
    public Result getTaskSchedule(@RequestParam("taskId") String taskId){
        String s = CalculateRequestEvent.taskMap.get(taskId);
        if (StrUtil.isNotBlank(s)){
            if ("success".equals(s) || "failed".equals(s)){
                // 移除该任务
                CalculateRequestEvent.taskMap.remove(taskId);
            }
            return Result.success(s);
        }
        return Result.error(-710,"不存在该任务或者该任务已经执行完成");
    }
    /**
     * 获取评价总次数
     */
    @GetMapping("/getEvalTotal")
    public Result getEvalTotal(StudentPortraitReq studentPortraitReq){
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getClassroomId()), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getCourseId()), ResponseEnum.COURSE_ID_NOT_NULL);
        Integer total = aiInStuAnsInfoService.getEvalTotal(studentPortraitReq.getCourseId(),studentPortraitReq.getClassroomId());
        SmartAssert.checkExpression(Objects.nonNull(total),ResponseEnum.NO_VALID);
        return Result.success(total);
    }

    /**
     * 获取学生参与评价次数
     */
    @GetMapping("/getStudentEvalNums")
    public Result getStudentEvalNums(@RequestParam("stuId") String stuId,
                                     @RequestParam("courseId") String courseId,
                                     @RequestParam("classroomId") String classroomId){
        SmartAssert.checkExpression(!StrUtil.isBlank(stuId), ResponseEnum.STUID_IS_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(courseId), ResponseEnum.COURSE_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(classroomId), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        List<Integer> attendEvalList = aiInStuAnsInfoService.getStudentEvalNums(stuId,courseId,classroomId);
        return Result.success(attendEvalList);
    }


    /**
     * 修改学生是否参与形成性评价的状态
     * 接受的是classroom_student表中的id和dynamic_state
     * @return
     */
    @PutMapping("/modifyStudentDynamicState")
    Result getStudentEvalNums(@RequestBody List<StudentDynamicStateReq> studentDynamicStateReqList){
        // 检验数据
        if(Objects.isNull(studentDynamicStateReqList) || studentDynamicStateReqList.size() == 0){
            return Result.error("studentDynamicStateReqList不能为空");
        }
        for (StudentDynamicStateReq studentDynamicStateReq : studentDynamicStateReqList){
            SmartAssert.checkExpression(!StrUtil.isBlank(studentDynamicStateReq.getClassroomStudentId()),ResponseEnum.CLASSROOM_STUDENT_ID_IS_NOT_NULL);
            if(studentDynamicStateReq.getDynamicState() == null){
                throw new BusinessException("dynamicState不能为空");
            }
            if(studentDynamicStateReq.getDynamicState() != 0 && studentDynamicStateReq.getDynamicState() != 1){
                throw new BusinessException("dynamicState参数只能是0或者1");
            }
        }
        boolean state = aiInStuAnsInfoService.modifyStudentDynamicState(studentDynamicStateReqList);
        return Result.success(state);
    }
}
