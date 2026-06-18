package com.example.smartttadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 配置swagger2相关的bean
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("smarttt-admin")
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(HttpServletRequest.class)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .tags(
                        new Tag("1. 登录认证", "用户登录、角色选择和基础身份信息获取"),
                        new Tag("2. 首页工作台", "教师/学生首页、菜单、角色切换与密码修改"),
                        new Tag("3. 学校与层级", "学校基础信息与组织层级配置"),
                        new Tag("4. 教学单位", "组织树、单位新增、编辑、删除与历史复制"),
                        new Tag("5. 学院管理", "学院节点及学院负责人维护"),
                        new Tag("6. 部门管理", "部门节点及部门负责人维护"),
                        new Tag("7. 专业管理", "专业节点及专业负责人维护"),
                        new Tag("8. 班级管理", "班级节点、班级维护与班级学生查询"),
                        new Tag("9. 人员管理", "教师/学生名单、导入、查询与编辑"),
                        new Tag("10. 角色管理", "角色的增删改查"),
                        new Tag("11. 角色授权", "角色菜单树与权限状态更新"),
                        new Tag("12. 教学目标", "教学目标设定查询"),
                        new Tag("13. 系统配置", "运行环境与数据库识别信息")
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.smartttadmin.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * API文档的显示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SMARTTT 管理端接口文档")
                .description("smarttt-admin 管理模块接口说明。\n"
                        + "1. 页面展示的完整访问前缀为 /api。\n"
                        + "2. 登录相关接口无需 token，其余大部分业务接口需要在请求头中携带 token。\n"
                        + "3. 通用响应结构为 Result：code 表示状态码，msg 表示消息，data 表示业务数据。\n"
                        + "4. 文档用于开发调试，字段说明以中文为主，便于前后端联调。")
                .version("v1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("token", "token", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/(?!login).*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScopeBuilder()
                .scope("global")
                .description("访问需要 token 的接口")
                .build();
        return Collections.singletonList(new SecurityReference("token", new AuthorizationScope[]{authorizationScope}));
    }
}
