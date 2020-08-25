package com.enel.config;

public enum FilterTypes {
	
	
	IDMODELENTITY("ID_MODEL_ENTITY"), FILTER_COLUMN1("<name_COLUMN1>"), FILTER_COLUMN2("<name_COLUMN2>"), FILTER_COLUMN3("<name_COLUMN3>"), FILTER_COLUMN4("<name_COLUMN4>"); 
	
	private String description;
	
	private FilterTypes(final String description) {
		this.description=description;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
