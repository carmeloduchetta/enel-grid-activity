package com.enel.permitting.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.entity.Car;
import com.enel.permitting.entity.Document;

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
				.createStoredProcedureQuery("GET_TOTAL_CARS_BY_MODEL")
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
	
	//@Test
	public void testFunction() {
		
		List<Car> rawcars = entityManager
				.createNamedQuery("FIND_CARS_AFTER_YEAR")
				.setParameter(1, 2018)
				.getResultList();
				     
		for(Car car: rawcars) {

			System.out.println("ID CAR: "+car.getId()+" - Model: "+car.getModel()+" - Year: "+car.getYear());
			
		}
				
	}
	
	//@Test
	public void testArdesiaiFunction() {
		
		List<Document> documents = entityManager
				//.createNamedQuery("ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI")
				.createNamedQuery("ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI")
				.setParameter(1, 100928)
				.getResultList();
				     
		for(Document document: documents) {

			System.out.println("ID DOCUMENT: "+document.getIddocumento()+" - Model: "+document.getDsdocumento());
			
		}
				
	}
	
	@Test
	public void testArdesiaFunctionAsProc() {
		List<Object> documents = entityManager
				//.createNativeQuery("{ ? = call ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI( ? ) }")
				.createNativeQuery("SELECT ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI(?) FROM DUAL;")
				.setParameter(1, 2011)
				.getResultList();
				 
				//query.execute();
				 
				//List<Document> documents = (List<Document>) query.getOutputParameterValue(2);
	}
 
 
}