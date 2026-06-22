package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.AuthRequired;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.ScoreSimulationRequest;
import com.example.smartttexam.service.ScoreSimulationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "分数模拟")
@RestController
@RequestMapping("/exam/scoresim")
public class ScoreSimulationController {

    @Autowired
    private ScoreSimulationService scoreSimulationService;

    @PostMapping("/simulate")
    @AuthRequired(type = "admin", menu = "", isReadOnly = false)
    public Result simulateScores(@RequestBody ScoreSimulationRequest request) {
        return scoreSimulationService.simulateScores(request);
    }

    /** 查询模拟结果，支持 testId 或 paperId */
    @GetMapping("/result")
    @AuthRequired(type = "admin", menu = "", isReadOnly = true)
    public Result getSimulatedScores(@RequestParam(required = false) String testId,
                                      @RequestParam(required = false) String paperId,
                                      @RequestParam(required = false) String stuId) {
        return scoreSimulationService.getSimulatedScores(testId, paperId, stuId);
    }
}
