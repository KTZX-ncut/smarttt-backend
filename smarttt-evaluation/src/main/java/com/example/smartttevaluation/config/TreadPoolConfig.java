package com.example.smartttevaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lunSir
 * @create 2024-11-29 19:46
 */
@Configuration
public class TreadPoolConfig {

    @Bean("portraitTreadPool")
    public ThreadPoolExecutor getLabelThreadPool(){
        return new ThreadPoolExecutor(100,
                100,
                3, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(40),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
