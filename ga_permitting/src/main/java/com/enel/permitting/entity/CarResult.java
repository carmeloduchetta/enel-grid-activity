package com.enel.permitting.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
    private String model;
    private Integer year;
    
}
