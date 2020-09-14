package com.enel.permitting.primarymodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Glossary", schema = "ga_dossier")
public class Glossary {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue()
	//@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name="id_glossary", insertable=true, updatable=true, unique=false, nullable=false)
	private Long idGlossary;
	
	@Column()
	private String code;
	
	@Column()
	private String description;
	
	@Column()
	@JsonProperty(access = Access.WRITE_ONLY)
	private String type;
	
	@Column()
	@JsonProperty(access = Access.WRITE_ONLY)
	private String country;
	
	@Column()
	@JsonProperty(access = Access.WRITE_ONLY)
	private String columnName;	
	
	@Column()
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date startDateValidation;
	
	@Column()
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date endDateValidation;
	
	@Column(name="entity_name")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String entityName;
	
	 @Override
	 public String toString() {
        return "Glossary {" +
                "idGlossary=" + idGlossary +
                ", code='" + code + '\'' +
                ", desc='" + description + '\'' +
                ", country='" + country + '\'' +
                ", columnName='" + columnName + '\'' +
                ", entityName=" + entityName +
                '}';
    }
	
}
