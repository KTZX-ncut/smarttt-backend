package com.example.smartttevaluation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableAspectJAutoProxy(exposeProxy = true)

@SpringBootApplication
@MapperScan("com.example.smartttevaluation.mapper")
@EnableTransactionManagement
@EnableAsync
@EnableScheduling // 开启定时任务
public class SmartttEvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartttEvaluationApplication.class, args);
    }
}
