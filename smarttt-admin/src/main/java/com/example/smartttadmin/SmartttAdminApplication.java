package com.example.smartttadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SmartttAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartttAdminApplication.class, args);
    }

}
