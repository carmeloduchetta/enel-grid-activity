package com.enel.permitting.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum DossierStatus {
	
	STATUS_0(0,"Success"),
	STATUS_4(4,"Warning"),
	STATUS_8(8,"Error"),
	STATUS_12(12,"Fatal Error");
	
	private static final Logger log = LoggerFactory.getLogger(DossierStatus.class);
	
	private final Integer code;
	private String description;
	
	private DossierStatus(final Integer code, final String description) {
		this.code=code;
		this.setDescription(description);
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static DossierStatus fromName(final String name) {

        for (DossierStatus po : values()) {
        	if( po.name().equalsIgnoreCase(name)){
	            return po;
	        }
        }        
        log.error("Invalid DossierStatus code " + name);
        return STATUS_12;
        
    }
	
	public static DossierStatus fromCode(final Integer code) {

		for (DossierStatus po : values()) {

			int signum = Integer.compare(po.getCode(), code);

			switch(signum) {

				case 0:
					return po;

				default:
					break;
			}

		}

		log.error("Invalid DossierStatus code " + code);
		return STATUS_12;

	}

}
