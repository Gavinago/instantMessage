package com.instant.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* 	类说明 
*
* @author grl
* @date 2019年11月16日  新建 
*/
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		// 详细定制
        		// 设置swagger-ui.html页面上的一些元素信息。
                .apiInfo(metaData())
        		.select()
                // 扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.instant.controller"
                ))
                // 定义要生成文档的Api的url路径规则
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                // 标题
                .title("APi接口文档")
                // 描述
                .description("SCT RESTful API ")
                // 文档版本
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}