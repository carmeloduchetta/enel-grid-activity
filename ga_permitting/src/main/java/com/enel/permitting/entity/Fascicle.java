package com.enel.permitting.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * a simple domain entity doubling as a DTO
 */

/*@SqlResultSetMappings({
	@SqlResultSetMapping(
			name = "getFascicleResult", 
			classes = @ConstructorResult(
					targetClass = FascicleResult.class, columns={
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
				@StoredProcedureParameter(mode = ParameterMode.INOUT, type = Integer.class, name = "an_idfascicolo"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_identeprivate"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_iddestinatario"),
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
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dataBreviManuIst"),

				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtinizioattesa"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_flagavanzamento"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dtrisposta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdrispostaottenuta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_condizioni"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdpumarisposta"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "an_idprofilopuma"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "ad_dataBreviManuRisp"),

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

		}),/*,
		resultSetMappings = {"getFascicleResult"}*/
				@NamedStoredProcedureQuery(
						name = "salvaFascicoloRealeWithMiniParams", 
						procedureName = "ARDESIAI.PCK_GEST_FASCREAL.SALVA_FASCICOLO_REALE",
						parameters = { 
								@StoredProcedureParameter(mode = ParameterMode.INOUT, type = Integer.class, name = "an_idfascicolo"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_idente"),								
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdfascicolo"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_dsfascicolo"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cditer"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_utente"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_idunitaresp"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdtiporichiesta"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_cdtiporisposta"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "an_ggterminilegge"),
								@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "as_swterminilegge"),
																
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

						}
	)
})

@Entity
@Table(name = "FASCICLE", schema = "ARDESIAI")
//@Table(name = "FASCICLE", schema = "WLSPOOL_ARDESIA")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Fascicle {
	
		@Id
	    @GeneratedValue()
	    private long id;

	    @Column(nullable = false)
	    private String name;

	    @Column()
	    private String description;

	    @Column()
	    String city;

	    @Column()
	    private int rating;
	    
	    private Integer an_major_code;
	    private Integer an_minor_code;
	    private String as_major_msg;
	    private String as_minor_msg;
	    
	    public Fascicle() {
	    }

	    public Fascicle(String name, String description, int rating) {
	        this.name = name;
	        this.description = description;
	        this.rating = rating;
	    }

	    public long getId() {
	        return this.id;
	    }

	    // for tests ONLY
	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getRating() {
	        return rating;
	    }

	    public void setRating(int rating) {
	        this.rating = rating;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    @Override
	    public String toString() {
	        return "Fascicle {" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                ", description='" + description + '\'' +
	                ", city='" + city + '\'' +
	                ", rating=" + rating +
	                '}';
	    }

		public Integer getAn_major_code() {
			return an_major_code;
		}

		public void setAn_major_code(Integer an_major_code) {
			this.an_major_code = an_major_code;
		}

		public Integer getAn_minor_code() {
			return an_minor_code;
		}

		public void setAn_minor_code(Integer an_minor_code) {
			this.an_minor_code = an_minor_code;
		}

		public String getAs_major_msg() {
			return as_major_msg;
		}

		public void setAs_major_msg(String as_major_msg) {
			this.as_major_msg = as_major_msg;
		}

		public String getAs_minor_msg() {
			return as_minor_msg;
		}

		public void setAs_minor_msg(String as_minor_msg) {
			this.as_minor_msg = as_minor_msg;
		}

}
