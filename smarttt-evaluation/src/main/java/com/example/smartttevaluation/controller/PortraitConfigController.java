package com.example.smartttevaluation.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.CalculatePaper;
import com.example.smartttevaluation.service.PortraitConfigService;
import com.example.smartttevaluation.vo.TestPaperInfoVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluation/conf")
public class PortraitConfigController {

    @Resource
    private PortraitConfigService portraitConfigService;


    /**
     * 获取考试信息列表
     */
    @GetMapping("/getTestInfoList")
    public Result getTestInfoList(@RequestParam("classroomId") String classroomId) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(classroomId), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        // 获取所有的作业
        List<TestPaperInfoVO> testPaperInfoVOList = portraitConfigService.getTestPaperInfo(classroomId);
        // 获取所有的实验
        List<TestPaperInfoVO> experimentInfoVOList = portraitConfigService.getTestExperimentPaperInfo(classroomId);
        // 合并作业和实验
        testPaperInfoVOList.addAll(experimentInfoVOList);
        List<CalculatePaper> calculatePaperList = portraitConfigService.getConfTestInfoList(classroomId);
        // 获取 calculatePaperList 中的所有 paperId
        Set<String> calculatePaperIds = calculatePaperList.stream()
                .map(CalculatePaper::getPaperId)
                .collect(Collectors.toSet());

        // 过滤 testPaperInfoVOList 中 paperId 不在 calculatePaperIds 中的元素
        List<TestPaperInfoVO> filteredTestPaperInfoVOList = testPaperInfoVOList.stream()
                .filter(testPaperInfoVO -> !calculatePaperIds.contains(testPaperInfoVO.getPaperId()))
                .collect(Collectors.toList());
        return Result.success(filteredTestPaperInfoVOList);
    }

    /**
     * 将考试信息插入插入配置作业表
     */
    @PostMapping("/saveConfPaper")
    public Result saveConfPaper(@RequestBody List<CalculatePaper> calculatePaperList) {
        Boolean flag = false;
        if(calculatePaperList != null && calculatePaperList.size() > 0){
            flag = portraitConfigService.saveConfPaper(calculatePaperList);
        }
        return Result.success(flag);
    }

    /**
     * 删除配置试卷数据
     */
    @DeleteMapping("/removeConfPaper")
    public Result removeConfPaperByIds(@RequestBody List<Long> idList) {
        if(CollectionUtil.isEmpty(idList)){
            return Result.error("idList不能为空");
        }
        portraitConfigService.removeConfPaperByIds(idList);
        return Result.success(true);
    }
    /**
     * 查询配置试卷列表
     */
    @GetMapping("/getConfTestInfoList")
    public Result getConfTestInfoList(@RequestParam("classroomId") String classroomId) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(classroomId), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        List<CalculatePaper> calculatePaperList = portraitConfigService.getConfTestInfoList(classroomId);
        return Result.success(calculatePaperList);
    }

    /**
     * 改动配置试卷列表的顺序
     */
    @PutMapping("/updateConfTestInfoListRow")
    public Result updateConfTestInfoListRow(@RequestParam("classroomId") String classroomId,
                                            @RequestBody List<Integer> idList) {
        SmartAssert.checkExpression(StrUtil.isNotBlank(classroomId), ResponseEnum.CLASSROOM_ID_NOT_NULL);
        List<CalculatePaper> calculatePaperList = portraitConfigService.getConfTestInfoList(classroomId);
        if(!Objects.equals(calculatePaperList.size(), idList.size())){
            return Result.error("idList数据个数非法");
        }
        for (int i = 0; i < calculatePaperList.size(); i++) {
            int indexOf = idList.indexOf(calculatePaperList.get(i).getId());
            if(indexOf == -1){
                return Result.error("idList数据非法");
            }
            calculatePaperList.get(i).setRow(indexOf + 1);
        }
        Boolean f = portraitConfigService.updateConfTestInfoListRow(calculatePaperList);
        return Result.success(f);
    }


}
