package com.enel.permitting.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Document implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cddocumento;
	private String dsdocumento;
	private Integer iddocumento;
	private String dtstampa;
	private String idprofilopuma;
	
}
