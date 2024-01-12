package com.example.smartttadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))//告诉要扫描哪个包，com包下的所有API都交给Swagger2管理
                .paths(PathSelectors.any()).build();

    }

    /**
     * API文档的显示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智能教学平台项目")
                .description("智能教学平台项目")
                .version("1.0")
                .build();
    }
}