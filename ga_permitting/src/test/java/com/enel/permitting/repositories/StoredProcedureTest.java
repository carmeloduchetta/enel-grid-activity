package com.enel.permitting.repositories;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.entity.CarResult;
import com.enel.permitting.entity.Document;
import oracle.jdbc.OracleTypes;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaPermittingApplication.class)
//@Sql(scripts = { "/test-car-data.sql" })
public class StoredProcedureTest {
	 
    //private static EntityManagerFactory factory = null;
	@PersistenceContext
    private EntityManager entityManager;
	
	@Test
	public void testGetTotalCarsProcedure() {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("ARDESIAI.GET_TOTAL_CARS_BY_MODEL")
				.registerStoredProcedureParameter(
				    1,
				    String.class,
				    ParameterMode.IN
				)
				.registerStoredProcedureParameter(
				    2,
				    Integer.class,
				    ParameterMode.OUT
				)
				.setParameter(1, "BMW");
				 
				query.execute();
				 
				Integer commentCount = (Integer) query.getOutputParameterValue(2);
				System.out.println("commentCount: "+commentCount);
	}
	
	@Test
	public void testFunction() {
	    
		List<CarResult> rawcars = entityManager
				.createNamedQuery("FIND_CARS_AFTER_YEAR")
				.setParameter(1, 2010)
				.getResultList();
				     
		for(CarResult car: rawcars) {

			System.out.println("ID CAR:"+ car.getId() +"- Model: "+car.getModel()+" - Year: "+car.getYear());
			
		}
		
	}
	
	@Test
	public void testArdesiaiFunction() {
		
		List<Document> documents = entityManager
				.createNamedQuery("getDocuments")			
				//.setParameter(1, null)
				.setParameter(1, 100928)
				.getResultList();
		
		for(Document document: documents) {

			System.out.println("ID DOCUMENT: "+document.getIddocumento()+" - Model: "+document.getDsdocumento());
			
		}
				
	}
	
	//@Test
	//@Transactional
	public void testArdesiaFunctionAsProc() {
		Session session = entityManager.unwrap( Session.class );
		 
		session.doWork( 
		    connection -> {
		    try (CallableStatement function = connection
		        .prepareCall(
		            "{ ? = call ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI( ? ) }" )) {
		        function.registerOutParameter( 1, OracleTypes.CURSOR );
		        function.setInt( 2, 100928 );
		        function.execute();
		        //return function.getObject(1);
		    }
		} );
		
	}
 
 
}