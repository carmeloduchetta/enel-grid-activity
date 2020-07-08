package com.enel.permitting.spain.repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.config.country.SpainDatabaseConfiguration;
import com.enel.permitting.repository.spain.FascicleSpainRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GaPermittingApplication.class, SpainDatabaseConfiguration.class})//(classes = GaPermittingApplication.class)
public class FascicleSpainRepositoryIntegrationTest {

	@Autowired
	private ApplicationContext context;
	
	@PersistenceContext
    private EntityManager entityManager;
	
		
    //@Autowired
    private FascicleSpainRepository fascicleRepository;
    
    /*@Test
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
    }*/
    
    //@Test 
    public void saveFascicleByProcedureTestByRepository() {
    	
    	Long idente = new Long(540);
    	Long iddestinatario = new Long(262865);
    	String user = "ES40983228B";
    	fascicleRepository.startSession(user, "ARDESIA_ONLINE", "es");
    	HashMap<String,Object> result = fascicleRepository.saveFascicle(
	    	        null,    //.............idfascicolo, 
	    	        idente,  //.............identeprivate, mandatory
	    	        iddestinatario,    //.............iddestinatario, 
	    	        "unit test procedure spain 1.1",           //.............cdfascicolo, mandatory
	    	        "concessione su strade consorziali SPAIN", //.............dsfascicolo, 
	    	        "24216449",     //.............cditer, mandatory
	    	        user,  //.............utente, mandatory
	    	        null,  //.............idinddestinat, 
	    	        262865,  //.............idunitaresp, mandatory
	    	        "AUTOR", //.............cdtiporichiesta, mandatory
	    	        "RI", //.............cdtiporisposta, mandatory
	    	        null, //.............ggterminilegge, optional check with Spain and Italy
	    	        null, //.............swterminilegge, optional check with Spain and Italy
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
    	fascicleRepository.endSession();
    	printMap(result);
    	    
    }

    /*@Test 
    public void saveFascicleByProcedureTest2ByRepository() {
    	
    	Long idente = new Long(500);
    	HashMap<String,Object> result = fascicleRepository.saveFascicle(
	    	        null,    //.............idfascicolo, 
	    	        idente,  //.............identeprivate, mandatory
	    	        268617,    //.............iddestinatario, 
	    	        "unit test procedure spain 1.1",           //.............cdfascicolo, mandatory
	    	        "concessione su strade consorziali SPAIN", //.............dsfascicolo, 
	    	        "U00052445",     //.............cditer, mandatory
	    	        "ES40983228B",  //.............utente, mandatory
	    	        null,  //.............idinddestinat, 
	    	        265517,  //.............idunitaresp, mandatory
	    	        "TRATT", //.............cdtiporichiesta, mandatory
	    	        "RI", //.............cdtiporisposta, mandatory
	    	        null, //.............ggterminilegge, optional check with Spain and Italy
	    	        null, //.............swterminilegge, optional check with Spain and Italy
	    	        30, //.............ggtempomedio, 
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
    	
    	printMap(result);
    	    
    }*/

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    
    @Test 
    public void saveFascicleByProcedureTestByEM() {
    	
    	StoredProcedureQuery startSession = entityManager
    			.createStoredProcedureQuery("COMMONSERVICES.P#ENVIRONMENT.STARTSESSION")
    			.registerStoredProcedureParameter(
				    1,
				    String.class,
				    ParameterMode.INOUT
				)
				.registerStoredProcedureParameter(
				    2,
				    String.class,
				    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
					    3,
					    String.class,
					    ParameterMode.IN
				)
				.setParameter(1, "ES40983228B")
				.setParameter(2, "ARDESIA_ONLINE")
				.setParameter(3, "es");
    	
    	startSession.execute();
    	
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

				.setParameter(2, 540)
				.setParameter(3, 262865)
				.setParameter(4, "unit test procedure spain 1.0")
				.setParameter(5, "concessione su strade consorziali EM")
				.setParameter(6, "24216449")
				.setParameter(7, "ES40983228B")
				//.setParameter(8, 262865)
				.setParameter(9, 262865)
				.setParameter(10, "AUTOR")
				.setParameter(11, "RI");
				//.setParameter(12, 90)
				//.setParameter(13, "N");
    			 
				query.execute();
				
				Integer idfascicolo = (Integer) query.getOutputParameterValue(1);
				String major_msg = (String) query.getOutputParameterValue(44);
				String minor_msg = (String) query.getOutputParameterValue(45);
				
				System.out.println("idfascicolo: "+idfascicolo + " major: "+major_msg+ " minor: "+minor_msg);

				StoredProcedureQuery endSession = entityManager
		    			.createStoredProcedureQuery("COMMONSERVICES.P#ENVIRONMENT.ENDSESSION");
				endSession.execute();
    }
}
