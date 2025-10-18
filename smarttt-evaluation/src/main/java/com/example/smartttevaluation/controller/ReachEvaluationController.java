package com.example.smartttevaluation.controller;


import com.example.smartttevaluation.exception.res.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reach-evaluation")
public class ReachEvaluationController {
    @GetMapping("/calculate")
    public Result calculate() {
        return null;
    }
}
