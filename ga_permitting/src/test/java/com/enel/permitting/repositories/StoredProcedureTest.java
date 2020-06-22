package com.enel.permitting.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.entity.Car;

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
	
	@Test
	public void testFunction() {
		
		List<Car> rawcars = entityManager
				.createNamedQuery("FIND_CARS_AFTER_YEAR")
				.setParameter(1, 2018)
				.getResultList();
				     
		for(Car car: rawcars) {

			System.out.println("ID CAR: "+car.getId()+" - Model: "+car.getModel()+" - Year: "+car.getYear());
			
		}
				
	}
 
 
}