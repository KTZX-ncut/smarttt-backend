package com.example.smartttevaluation.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String databaseUrl;
    private String projectModelName = "smarttt_evaluation模块";
    @GetMapping("/system")
    public String getSystemConfig(){
        // 截取数据库名称
        String databaseName = databaseUrl.substring(databaseUrl.lastIndexOf("/") + 1, databaseUrl.lastIndexOf("?"));
        if ("smarttt_new".equals(databaseName)){
            return projectModelName + "： 当前系统为子系统，用的数据库名称为：" + databaseName;
        }
        if ("smarttt_main".equals(databaseName)){
            return  projectModelName + "： 当前系统为主系统，用的数据库名称为：" + databaseName;
        }

        return "ERROR!";
    }

}
