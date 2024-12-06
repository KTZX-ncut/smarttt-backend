package com.example.smartttevaluation.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttevaluation.Utils.TermUtil;
import com.example.smartttevaluation.dto.PaperInfoDto;
import com.example.smartttevaluation.dto.StudentPortraitReq;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.AiInStuAnsInfo;
import com.example.smartttevaluation.service.AiInStuAnsInfoService;
import com.example.smartttevaluation.vo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 形成型评价模块的相关代码
 */
@RestController
@RequestMapping("/dynamic/portrait")
public class AiInStuAnsInfoController {

    @Resource
    private AiInStuAnsInfoService aiInStuAnsInfoService;


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
                                   @RequestParam(value = "search", required = false) String search) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(courseId), ResponseEnum.COURSE_ID_NOT_NULL);
        List<TestPaperInfoVO> testPaperInfoVOList = aiInStuAnsInfoService.getTestPaperInfo(courseId, search);
        return Result.success(testPaperInfoVOList);
    }

    /**
     * 形成性评价画像
     */
    @GetMapping("/student")
    public Result getStudentPortrait(StudentPortraitReq studentPortraitReq) {
        SmartAssert.checkExpression(Objects.nonNull(studentPortraitReq.getWeek()), ResponseEnum.WEEK_FAIL);
        // week >= 0
        SmartAssert.checkExpression(studentPortraitReq.getWeek() >= 0, ResponseEnum.WEEK_LE_ZERO_FAIL);
        // stuId (userId)  is not null
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getStuId()), ResponseEnum.STUID_IS_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getClassroomId()), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        SmartAssert.checkExpression(!StrUtil.isBlank(studentPortraitReq.getCourseId()), ResponseEnum.COURSE_ID_NOT_NULL);
        // 找到这个学生下的所有试卷 paper
        QueryWrapper<AiInStuAnsInfo> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT paperId").lambda()
                .eq(AiInStuAnsInfo::getStuid, studentPortraitReq.getStuId())
                .eq(AiInStuAnsInfo::getClassroomid, studentPortraitReq.getClassroomId());

        List<String> paperIdList = aiInStuAnsInfoService.list(wrapper).stream()
                .map(s -> s.getPaperid())
                .collect(Collectors.toList());

        if (CollectionUtil.isEmpty(paperIdList)) {
            // 没有发布作业
            return Result.ok().code(-600).msg("学生还没有作业！");
        }

        // 获取当前学期的开始时间
        String beginDate = aiInStuAnsInfoService.getCurrentTermStartTime();

        // 检验本次请求周的合法作业
        // aiInStuAnsInfoService.getPaperInfoListByIds就是想拿test创建的时间
        List<PaperInfoDto> paperInfoDtoList = aiInStuAnsInfoService.getPaperInfoListByIds(paperIdList);
        List<PaperInfoDto> whitePaperIdList = paperInfoDtoList
                .stream()
                .filter(t ->{
                    Long weekNums = TermUtil.getTeachingWeek(beginDate, t.getCreateTime());
                    Long week = Long.valueOf(studentPortraitReq.getWeek());
                    return weekNums > 0 && weekNums <= week;
                })
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(whitePaperIdList)) {
            return Result.ok().code(-600).msg("没有可生成的数据");
        }
        // 生成数据
        StudentPortraitVO studentPortraitVO = aiInStuAnsInfoService.
                getStudentPortrait(whitePaperIdList,
                        studentPortraitReq.getCourseId(),
                        studentPortraitReq.getStuId(),
                        studentPortraitReq.getClassroomId());
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
        SmartAssert.checkExpression(Objects.nonNull(studentPortraitReq.getWeek()), ResponseEnum.WEEK_FAIL);
        // week >= 0
        SmartAssert.checkExpression(studentPortraitReq.getWeek() >= 0, ResponseEnum.WEEK_LE_ZERO_FAIL);
        // 白名单
        QueryWrapper<AiInStuAnsInfo> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT paperId").lambda()
                .eq(AiInStuAnsInfo::getClassroomid, studentPortraitReq.getClassroomId())
                .eq(AiInStuAnsInfo::getCourseid, studentPortraitReq.getCourseId());

        List<String> paperIdList = aiInStuAnsInfoService.list(wrapper).stream()
                .map(s -> s.getPaperid())
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(paperIdList)) {
            // 没有发布作业
            return Result.ok().code(-600).msg("还没有作业！");
        }

        // 获取当前学期的开始时间
        String beginDate = aiInStuAnsInfoService.getCurrentTermStartTime();

        // 检验本次请求周的合法作业
        // aiInStuAnsInfoService.getPaperInfoListByIds就是想拿test创建的时间
        List<PaperInfoDto> paperInfoDtoList = aiInStuAnsInfoService.getPaperInfoListByIds(paperIdList);
        List<PaperInfoDto> whitePaperIdList = paperInfoDtoList
                .stream()
                .filter(t ->{
                    Long weekNums = TermUtil.getTeachingWeek(beginDate, t.getCreateTime());
                    Long week = Long.valueOf(studentPortraitReq.getWeek());
                    return weekNums > 0 && weekNums <= week;
                })
                .collect(Collectors.toList());
        // 检验
        if (CollectionUtil.isEmpty(whitePaperIdList)) {
            return Result.ok().code(-600).msg("没有可生成的数据");
        }

        // 生成数据
        StudentPortraitVO studentPortraitVO = aiInStuAnsInfoService
                .getClassroomPortrait(whitePaperIdList,
                        studentPortraitReq.getCourseId(),
                        studentPortraitReq.getClassroomId());
        return Result.success(studentPortraitVO);
    }
}
