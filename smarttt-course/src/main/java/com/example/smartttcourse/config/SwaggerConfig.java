package com.example.smartttcourse.config;

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
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
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
                .groupName("smarttt-course")
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class)
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .tags(
                        new Tag("1. 课程切换", "教师课程切换与当前课程上下文切换"),
                        new Tag("2. 学期管理", "学期新增、删除、更新与当前学期设置"),
                        new Tag("3. 系统配置", "运行环境和数据库识别信息"),
                        new Tag("4. 课程管理", "课程列表、历史课程、负责人和课程内容复制"),
                        new Tag("5. 课堂管理", "课堂列表、课堂新增编辑及负责人查询"),
                        new Tag("6. 课堂学生管理", "课堂学生名单、导入、删除与层级查询"),
                        new Tag("7. 教学大纲", "教学大纲文件的查询、上传、下载和删除"),
                        new Tag("8. 课程资源", "课程资源文件的查询、上传、下载和删除"),
                        new Tag("9. 课程教案", "课程教案文件的查询、上传、下载和删除"),
                        new Tag("10. 教学日历", "教学日历文件的查询、上传、下载和删除")
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.smartttcourse.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * API文档的显示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SMARTTT 课程模块接口文档")
                .description("smarttt-course 模块接口说明。\n"
                        + "1. 页面展示的完整访问前缀为 /api。\n"
                        + "2. 大部分业务接口需要在请求头中携带 token。\n"
                        + "3. 文件上传接口使用 multipart/form-data，请在 Swagger 页面直接选择文件。\n"
                        + "4. 通用响应结构为 Result：code 为业务状态码，msg 为提示信息，data 为业务数据。")
                .version("v1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("token", "token", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/(?!config/system$).*"))
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
