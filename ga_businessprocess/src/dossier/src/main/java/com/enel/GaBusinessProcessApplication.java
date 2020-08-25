package com.enel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GaBusinessProcessApplication {

    private static final Class<GaBusinessProcessApplication> applicationClass = GaBusinessProcessApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(GaBusinessProcessApplication.class, args);
	}
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
