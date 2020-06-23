package com.enel.permitting.entity;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.NamedNativeQuery;

@NamedNativeQuery(
	    name = "ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI",
	    query = "{ ? = call ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI( ? ) }",
	    callable = true,
	    resultSetMapping = "documents"
	)
	@SqlResultSetMapping(
	    name = "documents",
	    entities = {
	        @EntityResult(
	            entityClass = Document.class
	        )
	    }
	)

public class Document {

	private String cddocumento;

	public String getDsdocumento() {
		return dsdocumento;
	}
	public void setDsdocumento(String dsdocumento) {
		this.dsdocumento = dsdocumento;
	}
	public Long getIddocumento() {
		return iddocumento;
	}
	public void setIddocumento(Long iddocumento) {
		this.iddocumento = iddocumento;
	}
	public String getDtstampa() {
		return dtstampa;
	}
	public void setDtstampa(String dtstampa) {
		this.dtstampa = dtstampa;
	}
	public String getIdprofilopuma() {
		return idprofilopuma;
	}
	public void setIdprofilopuma(String idprofilopuma) {
		this.idprofilopuma = idprofilopuma;
	}
	private String dsdocumento;
	private Long iddocumento;
	private String dtstampa;
	private String idprofilopuma;
	
	public String getCddocumento() {
		return cddocumento;
	}
	public void setCddocumento(String cddocumento) {
		this.cddocumento = cddocumento;
	}

}
