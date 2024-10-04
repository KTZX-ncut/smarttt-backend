package com.example.smartttevaluation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.smartttevaluation.mapper")
public class SmartttEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartttEvaluationApplication.class, args);
    }
}
