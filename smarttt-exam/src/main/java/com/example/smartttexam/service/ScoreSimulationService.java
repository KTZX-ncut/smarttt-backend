package com.example.smartttexam.service;

import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.ScoreSimulationRequest;

/**
 * 作答分数模拟服务
 */
public interface ScoreSimulationService {

    /** 模拟学生作答分数 */
    Result simulateScores(ScoreSimulationRequest request);

    /** 查询模拟结果 */
    Result getSimulatedScores(String testId, String stuId);
}
