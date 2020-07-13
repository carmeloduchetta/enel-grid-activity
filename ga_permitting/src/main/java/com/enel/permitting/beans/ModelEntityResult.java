package com.enel.permitting.beans;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ModelEntityResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//OUT
	private Integer an_idfascicolo;
	private String as_cdstatofascicolo;
	private String as_cdstatosuccessivo;
	private String as_cdstatoprecedente;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
	private Date ad_dttermineprevista;
	private String as_listastati;
	private String as_repaintgraph;
	private Integer an_major_code;
	private Integer an_minor_code;
	private String as_major_msg;
	private String as_minor_msg;
	
}
