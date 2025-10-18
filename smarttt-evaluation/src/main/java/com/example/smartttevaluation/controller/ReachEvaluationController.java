package com.example.smartttevaluation.controller;


import com.example.smartttevaluation.exception.res.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reach-evaluation")
public class ReachEvaluationController {
    @GetMapping("/calculate")
    public Result calculate(@RequestParam("classroomId") String classroomId) {
        // 1. 获取所有的课堂目标

        return Result.ok().code(200).msg("计算完成！");
    }
}
