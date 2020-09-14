package com.enel.permitting.config;

public enum FilterTypes {
	
	IDFASCICOLO("ID_FASCICOLO"),STATOFASCICOLO("CD_STATO_FASCICOLO"),TIPORICHIESTA("CD_TIPO_RICHIESTA"),TIPORISPOSTA("CD_TIPO_RISPOSTA");
	
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
