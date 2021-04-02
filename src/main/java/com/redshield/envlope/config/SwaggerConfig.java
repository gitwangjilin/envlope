package com.redshield.envlope.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: SwaggerConfig
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/1/18   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.redshield.envlope.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("密码机相关功能")
                .description("密码机相关功能")
                .termsOfServiceUrl("http://localhost:8080/doc.html")
                .contact(new Contact("", "http://localhost:8080/doc.html", ""))
                .version("V1.0")
                .build();
    }

}