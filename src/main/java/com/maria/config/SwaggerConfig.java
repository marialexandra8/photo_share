package com.maria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created on 8/20/2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).groupName("default").apiInfo(apiInfo()).select()
                .paths(PathSelectors.regex("/api.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Photo_share REST API documentation")
                .description("Documentation for Photo Share API endpoints")
                .contact("Maria Caciula")
                .version("1.0")
                .build();
    }
}
