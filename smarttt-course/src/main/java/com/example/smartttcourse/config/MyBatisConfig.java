package com.example.smartttcourse.config;

import com.example.smartttcourse.Utils.DynamicTableInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    @Bean
    public DynamicTableInterceptor dynamicTableInterceptor() {
        return new DynamicTableInterceptor();
    }
}