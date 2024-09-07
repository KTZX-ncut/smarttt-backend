package com.example.smartttcourse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 开启事务
@MapperScan("com.example.smartttcourse.mapper")
public class SmartttCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartttCourseApplication.class, args);
    }

}
