package com.example.smartttexam.service;

import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.ScoreSimulationRequest;

public interface ScoreSimulationService {
    Result simulateScores(ScoreSimulationRequest request);
    Result getSimulatedScores(String testId, String paperId, String stuId);
}
