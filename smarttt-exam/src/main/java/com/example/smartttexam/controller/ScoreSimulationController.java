package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.ScoreSimulationRequest;
import com.example.smartttexam.service.ScoreSimulationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作答分数模拟控制器
 * 基础路径: /exam/scoresim
 */
@Api(tags = "分数模拟")
@RestController
@RequestMapping("/exam/scoresim")
public class ScoreSimulationController {

    @Autowired
    private ScoreSimulationService scoreSimulationService;

    /**
     * 模拟学生作答分数
     * POST /exam/scoresim/simulate
     */
    @PostMapping("/simulate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result simulateScores(@RequestBody ScoreSimulationRequest request) {
        return scoreSimulationService.simulateScores(request);
    }

    /**
     * 查询模拟结果
     * GET /exam/scoresim/result?testId=xxx&stuId=xxx（stuId可选）
     */
    @GetMapping("/result")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getSimulatedScores(@RequestParam String testId,
                                      @RequestParam(required = false) String stuId) {
        return scoreSimulationService.getSimulatedScores(testId, stuId);
    }
}
