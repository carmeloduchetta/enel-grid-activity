package com.enel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.ApiModel;
import lombok.RequiredArgsConstructor;

@ApiModel
@RequiredArgsConstructor
public enum Country {
	
	/** List Eligible Valid Countries Value */
	country1,country2;
	
	private static final Logger log = LoggerFactory.getLogger(Country.class);
	
	public static Country fromName(final String name) {

        for (Country po : values()) {
        	if( po.name().equalsIgnoreCase(name)){
	            return po;
	        }
        }
        
        log.error("Invalid Country code " + name);
        return null;
        
    }
}
