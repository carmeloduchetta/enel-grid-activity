package com.enel.permitting.api.rest.docs;

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
@ComponentScan("com.enel.permitting.api.rest")
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
        
    	String title = "Grid Activity API";
        String description = "<h3>General</h3>"
	            + "<p>Permitting component, thru a set of REST calls, "
	            + "offers the chance to manage a set of fascicles intended to enrich Enel portfolio.</p>";
	            
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl("github")
                .license("Enel")
                .licenseUrl("")
                .version("1.0")
                //.contact(new Contact("siamak"))
                .build();
    }

}
