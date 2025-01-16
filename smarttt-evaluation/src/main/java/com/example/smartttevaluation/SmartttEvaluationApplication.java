package com.example.smartttevaluation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.smartttevaluation.mapper")
@EnableTransactionManagement
public class SmartttEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartttEvaluationApplication.class, args);
    }
}
