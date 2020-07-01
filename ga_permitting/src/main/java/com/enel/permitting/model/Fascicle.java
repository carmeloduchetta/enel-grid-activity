package com.enel.permitting.model;


//import java.sql.Date;
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
import javax.persistence.Transient;

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

/*@SqlResultSetMappings({
	@SqlResultSetMapping(
			name = "getFascicleResult", 
			classes = @ConstructorResult(
					targetClass = FascicleResult.class, columns={
							@ColumnResult(name="an_idfascicolo", type = Integer.class),
							@ColumnResult(name="as_cdstatofascicolo", type = String.class),
							@ColumnResult(name="as_cdstatosuccessivo", type = String.class),
							@ColumnResult(name="as_cdstatoprecedente", type = String.class),
							@ColumnResult(name="ad_dttermineprevista", type = Date.class),
							@ColumnResult(name="as_listastati", type = String.class),
							@ColumnResult(name="as_repaintgraph", type = String.class),
							@ColumnResult(name="an_major_code", type = Integer.class),
							@ColumnResult(name="an_minor_code", type = Integer.class),
							@ColumnResult(name="as_major_msg", type = String.class),
							@ColumnResult(name="as_minor_msg", type = String.class)
					}
			)
	)
})*/

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "salvaFascicoloReale", 
		procedureName = "ARDESIAI.PCK_GEST_FASCREAL.SALVA_FASCICOLO_REALE",
		parameters = { 
				@StoredProcedureParameter(mode = ParameterMode.INOUT, type = Long.class, name = "an_idfascicolo"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "an_idente"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "an_iddestinatario"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdfascicolo"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_dsfascicolo"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cditer"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_utente"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_idinddestinat"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_idunitaresp"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdtiporichiesta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdtiporisposta"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_ggterminilegge"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_swterminilegge"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_ggtempomedio"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdstatoiteriniz"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdstatoiterfine"),

				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtfirma"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtspedizione"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtricevutaritorno"),
		
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_forzastato"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_idpumaistanza"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_protpumaistanza"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_databrevimanuist"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtinizioattesa"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_flagavanzamento"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtrisposta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdrispostaottenuta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_condizioni"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdpumarisposta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "an_idprofilopuma"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_databrevimanurisp"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtfascfine"),
				
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdesitofasc"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_swprescrizione"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_noteprescrizione"),
				
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_cdstatofascicolo"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_cdstatosuccessivo"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_cdstatoprecedente"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Date.class, name = "ad_dttermineprevista"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_listastati"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_repaintgraph"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "an_major_code"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "an_minor_code"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_major_msg"),
				@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "as_minor_msg")		

		}/*,
		resultSetMappings = {"getFascicleResult"}*/
		)		
})


@ApiModel
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "FASCREAL", schema = "ARDESIAI")
public class Fascicle {
	
		@Id
	    @GeneratedValue()
		private Long idfascicolo;
		
		//@Transient
		@Column()
		private Long iditer;
		
		//@Transient
		@Column()
		private Integer idfascicolomod;
		
		//@Transient
		@Column()
		private String cdfascicolomod;
		
		//@Transient
		@Column()
		private String cdtipofascicolo;
		
		//@Transient
		@Column()
		private String swfacoltativoprogetto;
		
		//@Transient
		@Column()
		private String swfacoltativoricorso;
		
		//@Transient
		@Column()
		private Integer nmlivello;
		
		//@Transient
		@Column()
		private Date dtfasciniz;
		
		//@Transient
		@Column()
		private Integer idautorizzazione;
		
		//@Transient
		@Column()
		private String cdautorizzazione;
		
		//@Transient
		@Column()
		private String note;
		
		//@Transient
		@Column()
		private String swrealizzazione;
		
		//@Transient
		@Column()
		private String swstoriadoc;
		
		//@Transient
		@Column()
		private Integer idservitu;
		
		//@Transient
		@Column()
		private String canclogica;
		
		//@Transient
		@Column()
		private Date datains;
		
		//@Transient
		@Column()
		private  String procins;
		
		//@Transient
		@Column()
		private Date datamod;
		
		//@Transient
		@Column()
		private String procmod;
		
		//@Transient
		@Column()
		private String swcessioneresp;
	    
	    @Column()
	    @ApiParam(value = "The ID of the Ente.", required = true)
		private Long idente;
	    
	    @Column()
		private Long iddestinatario;
	    
	    @Column()
	    @ApiModelProperty(
	    		  required=true,
	    		  value = "Code of a Fascicle",
	    		  name = "Fascicle Code",
	    		  dataType = "String",
	    		  example = "test procedure")
		private String cdfascicolo;
	    
	    @Column()
		private String dsfascicolo;
	    
	    @Column()
	    @ApiModelProperty(required=true)
		private String cditer;
	    
	    @Column()
	    @ApiModelProperty(required=true)
		private String userins;
	    
	    @Column()
		private String usermod;
	    
	    @Column()
		private Integer idinddestinat;
		
	    @Column()
	    @ApiModelProperty(required=true)
	    private Integer idunitaresp;
		
	    @Column()
	    @ApiModelProperty(required=true)
	    private String cdtiporichiesta;
		
	    @Column()
	    @ApiModelProperty(required=true)
	    private String cdtiporisposta;
		
	    @Column()
	    private Integer ggterminilegge;
		
	    @Column()
	    private String swterminilegge;
		
	    @Column()
	    private Integer ggtempomedio;
		
	    @Column()
	    private String cdstatoiteriniz;
		
	    @Column()
	    private String cdstatoiterfine;
		 
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
		private Date dtfirma;
		
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
	    private Date dtspedizione;
		
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
	    private Date dtricevutaritorno;
		
		//private String forzastato; 
		
	    @Column()
	    private String idpumaistanza;
		
	    @Column()
	    private String protpumaistanza;
	    
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
		private Date dtbrevimanuist;        //dataBreviManuIst;

	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
		private Date dtinizioattesa;
		//private String flagavanzamento; 
		
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
	    private Date dtrisposta;
		
	    @Column()
	    private String cdrispostaottenuta;
		//private String condizioni;
		
	    @Column()
	    private String cdpumarisposta;
		
	    @Column()
	    private String idprofilopuma;
		
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
		private Date dtbrevimanurisp;       //dataBreviManuRisp;
		
	    @Column()
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="CET")
	    private Date dtfascfine;
		
	    @Column()
	    private String cdesitofasc;
		
	    @Column()
	    private String swprescrizione;
		
	    @Column()
	    private String noteprescrizione;
		
	    @Column()
		private String cdstatofascicolo;
		
		
	    @Override
	    public String toString() {
	        return "Fascicle {" +
	                "idfascicolo=" + idfascicolo +
	                ", cditer='" + cditer + '\'' +
	                ", description='" + dsfascicolo + '\'' +
	                ", risposta='" + dtrisposta + '\'' +
	                ", termini legge=" + ggterminilegge +
	                '}';
	    }

}
