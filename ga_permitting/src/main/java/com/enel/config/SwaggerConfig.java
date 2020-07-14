package com.enel.config;

import com.google.common.base.Predicates;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(ApplicationConstants.API_PACKAGE_DEFINITION)
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(apiInfo());
    }
    

    private ApiInfo apiInfo() {
        	            
        return new ApiInfoBuilder()
                .title(ApplicationConstants.API_DOCUMENTATION_TITLE)
                .description(ApplicationConstants.API_DOCUMENTATION_DESCRITPTION)
                .termsOfServiceUrl("github")
                .license(ApplicationConstants.API_OWNER)
                .licenseUrl(ApplicationConstants.API_OWNER_LICENSE_URL)
                .version(ApplicationConstants.API_VERSION)
                .build();
    }

}