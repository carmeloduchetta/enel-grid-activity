package com.enel.permitting.italy.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.repository.italy.FascicleItalyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaPermittingApplication.class)
@Transactional("italyDatabaseTransactionManager")
public class FascicleRepositoryIntegrationTest {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	DataSource datasource;
	
    @Autowired
    private FascicleItalyRepository fascicleRepository;
    
    //@Test
    public void checkRegisterParameterOnDB() {
		try {
			Connection conn = datasource.getConnection();
	    	DatabaseMetaData dbmd = conn.getMetaData();
	    	if (dbmd.supportsNamedParameters() == true)
	    	{
	    		System.out.println("NAMED PARAMETERS FOR CALLABLE"
	    	                        + "STATEMENTS IS SUPPORTED");
	    	}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //@Test 
    public void saveFascicleByProcedureTestByRepository() {
    	
	   //HashMap<String,Object> result 
    	Long idente = new Long(1000012);
    	HashMap<String,Object> result = fascicleRepository.saveFascicle(
	    	        null,    //.............idfascicolo, 
	    	        idente, //.............identeprivate, mandatory
	    	        null,    //.............iddestinatario, 
	    	        "test procedure 6",                  //.............cdfascicolo, mandatory
	    	        "concessione su strade consorziali", //.............dsfascicolo, 
	    	        "1925484",  //.............cditer, mandatory
	    	        "A325137",  //.............utente, mandatory
	    	        null,       //.............idinddestinat, 
	    	        1000491080, //.............idunitaresp, mandatory
	    	        "AUTOR",    //.............cdtiporichiesta, mandatory
	    	        "RI", //.............cdtiporisposta, mandatory
	    	        90,   //.............ggterminilegge, optional check with Spain and Italy
	    	        "N",  //.............swterminilegge, optional check with Spain and Italy
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
    public void saveFascicleByProcedureTestByEM() {
    	
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
				.setParameter(4, "test procedure 1.0.1")
				.setParameter(5, "concessione su strade consorziali EM")
				.setParameter(6, "1925484")
				.setParameter(7, "A325137")
				.setParameter(9, 1000491080)
				.setParameter(10, "AUTOR")
				.setParameter(11, "RI")
				.setParameter(12, 90)
				.setParameter(13, "N");
    			 
				query.execute();
				
				Integer idfascicolo = (Integer) query.getOutputParameterValue(1);
			
				String major_msg = (String) query.getOutputParameterValue(44);
				String minor_msg = (String) query.getOutputParameterValue(45);
				
				System.out.println("idfascicolo: "+idfascicolo + " major: "+major_msg+ " minor: "+minor_msg);
				
    }
}
