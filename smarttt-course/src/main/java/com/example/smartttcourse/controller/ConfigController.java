package com.example.smartttcourse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/config")
@Api(tags = "3. 系统配置", description = "系统运行环境和数据库识别相关接口")
public class ConfigController {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    private String projectModelName = "smarttt_course模块";

    @GetMapping("/system")
    @ApiOperation(value = "获取系统环境标识", notes = "根据当前数据库连接信息判断课程模块连接的是主系统还是子系统。")
    public String getSystemConfig(){
        // 截取数据库名称
        String databaseName = databaseUrl.substring(databaseUrl.lastIndexOf("/") + 1, databaseUrl.lastIndexOf("?"));
        if ("smarttt_new".equals(databaseName)){
            return projectModelName +"： 当前系统为子系统，用的数据库名称为：" + databaseName;
        }
        if ("smarttt_main".equals(databaseName)){
            return  projectModelName + "： 当前系统为主系统，用的数据库名称为：" + databaseName;
        }

        return "ERROR!";
    }

}
