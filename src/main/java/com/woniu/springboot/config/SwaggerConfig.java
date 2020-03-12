package com.woniu.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cl
 * @Date 2020/3/6 14:09
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //控制层包扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.woniu.springboot.api"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                .title("springboot整合swagger")
                .description("springboot整合swagger详细信息。。。。")
                .version("1.0.0")
                //作者信息
                .contact(new Contact("LynnChan","www.tencent.com","LynnChan1024@qq.com"))
                .license("The Apache License")
                .licenseUrl("www.apache.com")
                .build());
    }
}
