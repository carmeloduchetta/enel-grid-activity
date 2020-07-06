package com.enel.permitting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GaPermittingApplication {

    private static final Class<GaPermittingApplication> applicationClass = GaPermittingApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(GaPermittingApplication.class, args);
	}
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
