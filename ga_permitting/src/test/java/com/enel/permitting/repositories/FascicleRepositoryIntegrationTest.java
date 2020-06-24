package com.enel.permitting.repositories;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.beans.FascicleResult;
import com.enel.permitting.repository.FascicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaPermittingApplication.class)
public class FascicleRepositoryIntegrationTest {

	@PersistenceContext
    private EntityManager entityManager;
	
    @Autowired
    private FascicleRepository fascicleRepository;
    
    //@Test 
    public void saveFascicleByProcedureTestByRepository() {
    	
	   FascicleResult result = fascicleRepository.saveFascicle(
	    	        null, //.............idfascicolo, 
	    	        1000640, //.............identeprivate, 
	    	        null, //.............iddestinatario, 
	    	        "test procedure 2", //.............cdfascicolo, 
	    	        "concessione su strade consorziali", //.............dsfascicolo, 
	    	        "160920", //.............cditer, 
	    	        "A325137", //.............utente, 
	    	        null, //.............idinddestinat, 
	    	        1000491456, //.............idunitaresp, 
	    	        "AUTOR", //.............cdtiporichiesta, 
	    	        "RI", //.............cdtiporisposta, 
	    	        90, //.............ggterminilegge, 
	    	        "N", //.............swterminilegge, 
	    	        null, //.............ggtempomedio, 
	    	        null, //.............cdstatoiteriniz, 
	    	        null, //.............cdstatoiterfine, 
	    	        null, //.............dtfirma, 
	    	        null, //.............dtspedizione, 
	    	        null, //.............dtricevutaritorno, 
	    	        null, //.............forzastato, 
	    	        null, //.............idpumaistanza, 
	    	        null, //.............protpumaistanza, 
	    	        null, //.............dataBreviManuIst, 
	    	        null, //.............dtinizioattesa, 
	    	        null, //.............flagavanzamento, 
	    	        null, //.............dtrisposta, 
	    	        null, //.............cdrispostaottenuta, 
	    	        null, //.............condizioni, 
	    	        null, //.............cdpumarisposta, 
	    	        null, //.............idprofilopuma, 
	    	        null, //.............dataBreviManuRisp, 
	    	        null, //.............dtfascfine, 
	    	        null, //.............cdesitofasc, 
	    	        null, //.............swprescrizione, 
	    	        null  //.............noteprescrizione
	    	);
    	    
    }

    @Test
    public void saveFascicleByProcedureTestWithMiniParams() {
    	
 	   fascicleRepository.saveFascicleWithMiniParams(
 			   		null,
 			   		1000012, //.............identeprivate, 
 	    	        "test procedure 2", //.............cdfascicolo, 
 	    	        "concessione su strade consorziali", //.............dsfascicolo, 
 	    	        "1925484", //.............cditer, 
 	    	        "A325137", //.............utente, 
 	    	        1000491080, //.............idunitaresp, 
 	    	        "AUTOR", //.............cdtiporichiesta, 
 	    	        "RI", //.............cdtiporisposta, 
 	    	        90, //.............ggterminilegge, 
 	    	        "N" //.............swterminilegge, 
 	    	);
     	    
     }

    /**
     * 
     :AN_IDENTE,
:AN_IDDESTINATARIO,
:AS_CDFASCICOLO,
:AS_DSFASCICOLO,
:AS_CDITER,
:AS_UTENTE,
:AN_IDINDDESTINAT,
:AN_IDUNITARESP,
:AS_CDTIPORICHIESTA,
:AS_CDTIPORISPOSTA,
:AN_GGTERMINILEGGE,
:AS_SWTERMINILEGGE,
:AN_GGTEMPOMEDIO,
:AS_CDSTATOITERINIZ,
:AS_CDSTATOITERFINE,
:AD_DTFIRMA,
:AD_DTSPEDIZIONE,
:AD_DTRICEVUTARITORNO,
:AS_FORZASTATO,
:AS_IDPUMAISTANZA,
:AS_PROTPUMAISTANZA,
:AD_DATABREVIMANUIST,
:AD_DTINIZIOATTESA,
:AS_FLAGAVANZAMENTO,
:AD_DTRISPOSTA,
:AS_CDRISPOSTAOTTENUTA,
:AS_CONDIZIONI,
:AS_CDPUMARISPOSTA,
:AN_IDPROFILOPUMA,
:AD_DATABREVIMANURISP,
:AD_DTFASCFINE,
:AS_CDESITOFASC,
:AS_SWPRESCRIZIONE,
:AS_NOTEPRESCRIZIONE,
     */
    
    
    //@Test 
    //@Transactional
    public void saveFascicleByProcedureTestByEM() {
    	
    	//entityManager.getTransaction().begin();
    	
    	StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("ARDESIAI.PCK_GEST_FASCREAL.SALVA_FASCICOLO_REALE")
				.registerStoredProcedureParameter(
				    1,
				    Integer.class,
				    ParameterMode.INOUT
				)
				.registerStoredProcedureParameter(
				    2,
				    Integer.class,
				    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    3,
					    Integer.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    4,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    5,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    6,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    7,
					    String.class,
					    ParameterMode.IN
				)
				
				.registerStoredProcedureParameter(
					    8,
					    Integer.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    9,
					    Integer.class,
					    ParameterMode.IN
				)
				
				.registerStoredProcedureParameter(
					    10,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    11,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    12,
					    Integer.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    13,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    14,
					    Integer.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    15,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    16,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    17,
					    Date.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    18,
					    Date.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    19,
					    Date.class,
					    ParameterMode.IN
				)
				
				.registerStoredProcedureParameter(
					    20,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    21,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    22,
					    String.class,
					    ParameterMode.IN
				)
				
				.registerStoredProcedureParameter(
					    23,
					    Date.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    24,
					    Date.class,
					    ParameterMode.IN
				)
				
				.registerStoredProcedureParameter(
					    25,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    26,
					    Date.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    27,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    28,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    29,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    30,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    31,
					    Date.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    32,
					    Date.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    33,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    34,
					    String.class,
					    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    35,
					    String.class,
					    ParameterMode.IN
				)
				
				.registerStoredProcedureParameter(
					    36,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    37,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    38,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    39,
					    Date.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    40,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    41,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    42,
					    Integer.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    43,
					    Integer.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    44,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    45,
					    String.class,
					    ParameterMode.OUT
				)

				.setParameter(2, 1000012)
				.setParameter(4, "test procedure")
				.setParameter(5, "concessione su strade consorziali")
				.setParameter(6, "1925484")
				.setParameter(7, "A325137")
				.setParameter(9, 1000491080)
				.setParameter(10, "AUTOR")
				.setParameter(11, "RI")
				.setParameter(12, 90)
				.setParameter(13, "N");
    			 
				query.execute();
				
				Integer idfascicolo = (Integer) query.getOutputParameterValue(1);
				
				System.out.println("idfascicolo: "+idfascicolo);				
    }
}
