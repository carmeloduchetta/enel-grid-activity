package com.enel.permitting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.enel.permitting.config.ApplicationConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * a simple domain entity doubling as a DTO
 */

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
	    name = "ContextProcedure1", 
		procedureName = ApplicationConstants.STORE_PROCEDURE_1,
		parameters = { 
				@StoredProcedureParameter(mode = ParameterMode.INOUT, type = Long.class, name = "<name_param1>"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "<name_param2>"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "<name_param3>"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "<name_param4>"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "<name_param5>"),
								
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "<name_param6>"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Date.class, name = "<name_param7>"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "<name_param8>"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "<name_param9>")		
			}
		),
		@NamedStoredProcedureQuery(
            name = "ContextProcedure2", 
			procedureName = ApplicationConstants.STORE_PROCEDURE_2,
			parameters = { 
					@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "<name_param1>"),
					@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "<name_param2>"),
					@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "<name_param3>")
			}
		),
		@NamedStoredProcedureQuery(
				name = "ContextProcedure3", 
				procedureName = ApplicationConstants.STORE_PROCEDURE_3 
		)
})


@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "DB_TABLE", schema = "DB_SCHEMA")
public class ModelEntity {
	
		@Id
	    @GeneratedValue()
		private Long idModelEntity;
		
		@Column()
		@ApiParam(value = "This column_1 is documented on Swagger to be mandatory.", required = true)
		private Long column_1;
		
		@Column()
		private Integer column_2;
		
		@Column()
	    @ApiModelProperty(
	    		  required=true,
	    		  value = "Swagger Documentation for value description",
	    		  name = "Swagger Documentation for name description",
	    		  dataType = "Swagger Documentation for data type description. Example String",
	    		  example = "Provide a Documentation Example")
		private String column_3;
				
		@Column()
		/** Define a Date Patter managed both on API and on DB Table **/
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
		private Date column_4;
		
	    @Override
	    public String toString() {
	        return "ModelEntity {" +
	                "idModelEntity=" + idModelEntity +
	                ", column_1='" + column_1 + '\'' +
	                ", column_2='" + column_2 + '\'' +
	                ", column_3='" + column_3 + '\'' +
	                ", column_4=" + column_4 +
	                '}';
	    }

}
