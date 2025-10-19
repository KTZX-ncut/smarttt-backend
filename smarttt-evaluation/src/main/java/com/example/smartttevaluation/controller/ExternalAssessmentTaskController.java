package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.smartttevaluation.exception.res.ResponseEnum;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.exception.utils.SmartAssert;
import com.example.smartttevaluation.pojo.FeExternalAssessmentTask;
import com.example.smartttevaluation.service.ExternalAssessmentTaskService;
import com.example.smartttevaluation.service.FeAssessmentItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2025-10-18 22:12
 */
@RestController
@RequestMapping("/externalAssessmentTask")
@RequiredArgsConstructor
public class ExternalAssessmentTaskController {

    private final ExternalAssessmentTaskService externalAssessmentTaskService;

    private final FeAssessmentItemsService feAssessmentItemsService;

    @GetMapping("/list/{labelId}")
    public Result getExternalAssessmentTaskList(@PathVariable("labelId") String labelId,
                                                @RequestParam(value = "filter",required = false) Boolean filter){
        SmartAssert.notEmpty(labelId, ResponseEnum.PARAM_IS_NOT_NULL);
        List<FeExternalAssessmentTask> list = externalAssessmentTaskService.list(new LambdaQueryWrapper<FeExternalAssessmentTask>().eq(FeExternalAssessmentTask::getLabelId, labelId));
        if (!Objects.isNull(filter) && Objects.equals(filter,true)) {
            // 过滤
            List<String> typeIdList = feAssessmentItemsService.getAllTypeIdList();
            list = list.stream()
                    .filter(t -> typeIdList.contains(t.getId()))
                    .collect(Collectors.toList());
        }
        return Result.success(list);
    }

}
