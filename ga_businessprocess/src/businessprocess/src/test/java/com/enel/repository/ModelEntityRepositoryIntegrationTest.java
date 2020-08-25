package com.enel.repository;

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
import com.enel.repository.ModelEntityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaBusinessProcessApplication.class)
public class ModelEntityRepositoryIntegrationTest {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	DataSource datasource;
	
    @Autowired
    private ModelEntityRepository modelEntityRepository;
    
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
    	/*HashMap<String,Object> result = modelEntityRepository.saveModelEntity(
    			    param1, //.............param1, 
	    	        param2, //..............param2,
	    	        null,   //.............param3, 
	    	        "test procedure",    //.............param4
	    	       null //.............param5,     	        
	    );*/
    	    
    }
    
}
