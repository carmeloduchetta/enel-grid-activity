package com.enel.beans;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ModelEntityResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer code;
	private String message;	
	private String details;
	
	//@ApiModelProperty(notes = "Additional response data set", required = true)
	private Map<String, String> additionalResponseData;
		
}
