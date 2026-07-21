package com.example.smartttexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SmartttExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartttExamApplication.class, args);
    }

}
