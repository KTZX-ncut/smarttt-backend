package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTask;
import com.example.smartttevaluation.service.ExternalAssessmentTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lunSir
 * @create 2025-10-18 22:12
 */
@RestController
@RequestMapping("/externalAssessmentTask")
@RequiredArgsConstructor
public class ExternalAssessmentTaskController {

    private final ExternalAssessmentTaskService externalAssessmentTaskService;

    @GetMapping("/list/{labelId}")
    public Result getExternalAssessmentTaskList(@PathVariable("labelId") String labelId){
        SmartAssert.notEmpty(labelId, ResponseEnum.PARAM_IS_NOT_NULL);
        return Result.success(externalAssessmentTaskService.list(new LambdaQueryWrapper<FeExternalAssessmentTask>().eq(FeExternalAssessmentTask::getLabelId,labelId)));
    }

}
