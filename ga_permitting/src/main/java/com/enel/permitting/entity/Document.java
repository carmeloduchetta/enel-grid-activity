package com.enel.permitting.entity;

import java.io.Serializable;


public class Document implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cddocumento;
	private String dsdocumento;
	private Integer iddocumento;
	private String dtstampa;
	private String idprofilopuma;
	
	public Document() {
		
	}
	
	public Document(String cddocumento,String dsdocumento,Integer iddocumento,String dtstampa,String idprofilopuma) {
		this.cddocumento = cddocumento;
		this.dsdocumento = dsdocumento;
		this.iddocumento = iddocumento;
		this.dtstampa = dtstampa;
		this.idprofilopuma = idprofilopuma;
	}
	
	public String getDsdocumento() {
		return dsdocumento;
	}
	public void setDsdocumento(String dsdocumento) {
		this.dsdocumento = dsdocumento;
	}
	public Integer getIddocumento() {
		return iddocumento;
	}
	public void setIddocumento(Integer iddocumento) {
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
	public String getCddocumento() {
		return cddocumento;
	}
	public void setCddocumento(String cddocumento) {
		this.cddocumento = cddocumento;
	}

}
