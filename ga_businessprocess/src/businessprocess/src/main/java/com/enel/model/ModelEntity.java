package com.enel.model;

import java.io.Serializable;
import java.sql.Date;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.enel.config.ApplicationConstants;
import com.enel.model.id.IdModelEntity;
/*
 * a simple domain entity doubling as a DTO
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(IdModelEntity.class)
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "DB_TABLE", schema = "DB_SCHEMA")
@DynamicUpdate(value = true)
public class ModelEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ApiModelProperty(readOnly = true)
	@Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = ApplicationConstants.MODEL_ENTITY_SEQUENCE_NAME),
        @Parameter(name = "initial_value", value = ApplicationConstants.MODEL_ENTITY_SEQUENCE_START),
        @Parameter(name = "increment_size", value = ApplicationConstants.MODEL_ENTITY_SEQUENCE_INCREMENT_SIZE)
        }
    )
	@Column(name="<idModelEntity>", insertable=true, updatable=true, unique=false, nullable=false)
	private Long idModelEntity;

	@Id
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	//@ApiModelProperty(readOnly = true)
	@JsonIgnore
	@Column(insertable=true, updatable=true, nullable=false)
	private String country;
	
	@Column()
	private Long column_1;
	
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private String column_2;
    
	@Column()
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="UTC")
	private Date column_3;

    @Column()
    private Integer column_4;
	
    @Override
    public String toString() {
        return "ModelEntity {" +
                "idModelEntity=" + idModelEntity +
                ", column_1='" + column_1 + '\'' +
                ", column_2='" + column_2 + '\'' +
                ", column_3='" + column_3 + '\'' +
                ", column_4='" + column_4 + 
                '}';
    }
}