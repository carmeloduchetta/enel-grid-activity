package com.enel.repository.country1;

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

import com.enel.GaBusinessProcessApplication;
import com.enel.config.ApplicationConstants;
import com.enel.repository.country1.ModelEntityCountry1Repository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaBusinessProcessApplication.class)
public class ModelEntityCountry1RepositoryIntegrationTest {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	DataSource datasource;
	
    @Autowired
    private ModelEntityCountry1Repository modelEntityRepository;
    
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
    public void saveModelEntityByProcedureTestWithRepository() {
    	 
    	Long param1 = new Long(1000012);
    	Long param2 = new Long(2990012);
    	HashMap<String,Object> result = modelEntityRepository.saveModelEntity(
    			    param1, //.............param1, 
	    	        param2, //..............param2,
	    	        null,   //.............param3, 
	    	        "test procedure",    //.............param4
	    	       null //.............param5,     	        
	    );
    	    
    }

    
    @Test 
    public void saveModelEntityByProcedureTestWithEM() {
    	
    	StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery(ApplicationConstants.STORE_PROCEDURE_1)
				.registerStoredProcedureParameter(
				    1,
				    Integer.class,
				    ParameterMode.INOUT
				)
				.registerStoredProcedureParameter(
				    2,
				    Long.class,
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
					    Date.class,
					    ParameterMode.IN
				)
						
				.registerStoredProcedureParameter(
					    6,
					    String.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    7,
					    Date.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    8,
					    Integer.class,
					    ParameterMode.OUT
				)
				.registerStoredProcedureParameter(
					    9,
					    String.class,
					    ParameterMode.OUT
				)

				.setParameter(1, 1000012)
				.setParameter(2, 2990012)
				.setParameter(4, "test procedure");
    			 
				query.execute();
				
				Integer param_out_1 = (Integer) query.getOutputParameterValue(1);
			
				String param_out_2 = (String) query.getOutputParameterValue(44);
				String param_out_3 = (String) query.getOutputParameterValue(45);
				
				System.out.println("param_out_1: "+param_out_1 + " param_out_2: "+param_out_2+ " param_out_3: "+param_out_3);
				
    }
}
