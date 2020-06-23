package com.enel.permitting.entity;

import java.io.Serializable;

public class CarResult implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
    private String model;
    private Integer year;
    
    public CarResult() {
    	
    }
    
    public CarResult(long id, String model, Integer year) {
    	this.id=id;
        this.model = model;
        this.year = year;
    }

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}
