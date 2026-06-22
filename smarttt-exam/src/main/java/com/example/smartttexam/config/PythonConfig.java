package com.example.smartttexam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Python环境配置，将application.properties中的python.path设为系统属性
 */
@Configuration
public class PythonConfig {

    @Value("${python.path:python}")
    private String pythonPath;

    @PostConstruct
    public void init() {
        System.setProperty("python.path", pythonPath);
    }
}
