package com.example.smartttevaluation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.smartttevaluation.exception.res.Result;
import com.example.smartttevaluation.pojo.ExternalAssessmentTaskDetail;
import com.example.smartttevaluation.service.ExternalAssessmentTaskDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exAssessTaskDetail")
@RequiredArgsConstructor
public class ExternalAssessmentTaskDetailController {

    private final ExternalAssessmentTaskDetailService externalAssessmentTaskDetailService;


    @GetMapping("/list/{exTaskId}")
    public Result getExternalAssessmentTaskDetailList(@PathVariable("exTaskId") String exTaskId){
        QueryWrapper<ExternalAssessmentTaskDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("ex_assessment_task_id",exTaskId);
        return Result.ok().code(200).data(externalAssessmentTaskDetailService.list(wrapper)).msg("查询成功");
    }


}
