package com.enel.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.enel.GaBusinessProcessApplication;
import com.enel.model.Glossary;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaBusinessProcessApplication.class)
public class GlossaryRepositoryIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlossaryRepositoryIntegrationTest.class);

	@Autowired
	private GlossaryRepository glossaryEntityRepository;
	
	//@Test
	public void getGlossaryByRowCodeAndCountry() {
		
		Glossary result = glossaryEntityRepository.findGlossaryByCodeAndCountry("LB", "IT");
		
		if(result != null)
			LOGGER.info(result.toString());
		else 
			LOGGER.info("No Glossary Found");
	}
	
	@Test
	public void getDistinctColumnName() {
		
		List<Glossary> glossaries = glossaryEntityRepository.findDistinctGlossaryByColumnName("cd_stato_fascicolo");
		
		if(glossaries != null)
			for(Glossary current : glossaries){
				LOGGER.info(current.getColumnName());
			}
		else 
			LOGGER.info("No Glossary Found");
	}
}
