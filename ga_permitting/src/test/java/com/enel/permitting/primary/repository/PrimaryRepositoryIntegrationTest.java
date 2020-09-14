package com.enel.permitting.primary.repository;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.primarymodel.Glossary;
import com.enel.permitting.repository.primary.DossierRepository;
import com.enel.permitting.repository.primary.GlossaryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaPermittingApplication.class)
public class PrimaryRepositoryIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryRepositoryIntegrationTest.class);
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	private DossierRepository dossierRepository;
	
	@Autowired
	private GlossaryRepository glossaryRepository;
	
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
	
	@Test
    public void testGlossaryQueriesConnection() {
		try {
			
			List<Glossary> glossaries = glossaryRepository.findAll();			
			LOGGER.info("Glossaries found: "+glossaries.size());
			for(int i=0; i<10; i++) {
				Glossary current = glossaries.get(i);
				LOGGER.info(current.getIdGlossary()+" "+current.getEntityName()+" "+current.getColumnName());
			}
			assertEquals(82,glossaries.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	
}
