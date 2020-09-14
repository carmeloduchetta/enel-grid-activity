package com.enel.permitting.primarymodel;

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

import com.enel.permitting.config.ApplicationConstants;
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
@IdClass(IdDossier.class)

@JsonIgnoreProperties(ignoreUnknown = false)
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "Dossier", schema = "ga_dossier")
@DynamicUpdate(value = true)
public class Dossier implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ApiModelProperty(readOnly = true)
	@Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = ApplicationConstants.DOSSIER_SEQUENCE_NAME),
        @Parameter(name = "initial_value", value = ApplicationConstants.DOSSIER_SEQUENCE_START),
        @Parameter(name = "increment_size", value = ApplicationConstants.DOSSIER_SEQUENCE_INCREMENT_SIZE)
        }
    )
	@Column(name="id_fascicolo", insertable=true, updatable=true, unique=false, nullable=false)
	private Long idFascicolo;

	@Id
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	//@ApiModelProperty(readOnly = true)
	@JsonIgnore
	@Column(insertable=true, updatable=true, nullable=false)
	private String country;
	
	@Column()
	private Long idIter;
	
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private String cdFascicolo;
    
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private String dsFascicolo;
    
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private String cdStatoFascicolo;
	
	@Column()
	//@JsonProperty(access = Access.WRITE_ONLY)
	private String cancLogica;
	
	@Column()
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="UTC")
	private Date dataIns;
		
	//@Transient
	@Column()
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="UTC")
	private Date dataMod;
		
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private Long idEnte;
    
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private Long idDestinatario;
    
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
	private String userIns;
    
    @Column()
    // @JsonProperty(access = Access.WRITE_ONLY)
	private String userMod;
    		
    @Column()
    private String cdTipoRichiesta; //as filter
	
    @Column()
    private String cdTipoRisposta;  //as filter
	
    @Column()
    private Integer ggTerminiLegge;
	
    @Column()
    private String swTerminiLegge;
	
    @Column()
    //@JsonProperty(access = Access.WRITE_ONLY)
    private Integer ggTempoMedio;
	
	
    @Override
    public String toString() {
        return "Fascicle {" +
                "idFascicolo=" + idFascicolo +
                ", idIter='" + idIter + '\'' +
                ", description='" + dsFascicolo + '\'' +
                ", cd richiesta='" + cdTipoRichiesta + '\'' +
                ", cd risposta='" + cdTipoRisposta + '\'' +
                ", termini legge=" + ggTerminiLegge +
                '}';
    }
}
