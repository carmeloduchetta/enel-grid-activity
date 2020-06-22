package com.enel.permitting.repositories;

import java.util.List;

import javax.persistence.StoredProcedureQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.enel.permitting.repository.FascicleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = { SpringConfiguration.class })
//@EnableAutoConfiguration
@Configuration
//@WebAppConfiguration
public class TestRepositoryDAO {
	
	//private static final Logger LOGGER = Logger.getLogger(FascicleRepository.class);

	@Autowired
	private FascicleRepository fascicleDao;
	
	 
	private int arg1;
	private int arg2;
	
	private int majorcode;
	private int minorcode;
	private String majormsg;
	private String minormsg;
	
	
	@Test
	//@Ignore
	//@Transactional
	public void getAccountById() throws Exception {
		
		setArg1(1);
		setArg2(2);		
		
		try {			
			
			
			
			System.out.println("Before to invoke storedprocedure... ");
			//String results = fascicleDao.findProvvCondl(2,2);
			//String result = fascicleDao.getTotalByModelEntity(2, 4);
			String result = fascicleDao.getTotalByModelEntity(2,4,6,8,"testmsg","testmsg1");
			//String results = fascicleDao.findProvvCondl(2,3,4,5,"testmsg","testmsg1");
			//fascicleDao.findProvvCondl(1, 3, 2, 4, "a", "b");
			
			
			///Map<String, String> results = fascicleDao.executeMyStoredProcedure(1, 3, 2, 4, "a", "b");
			System.out.println("SUCCESS: "+1);
			System.out.println("SUCCESS: "+3);
			System.out.println("SUCCESS: "+2);
			System.out.println("SUCCESS: "+4);
		}catch(Exception ex) {
			
			ex.getCause();
			
			System.out.println("FAILED:" +ex.getMessage());
		
		}
		
		//LOGGER.info(" paytmAccount: " + Mapper.toJSON(paytmAccount));
		
	}


	//@Test
	public void storeprocedureTest() {
		
		try {
			/*
			StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("calculate");
			query.setParameter("x", 1.23d);
			query.setParameter("y", 4.56d);
			query.execute();
			Double sum = (Double) query.getOutputParameterValue("sum");
			*/
		}catch(Exception ex) {
			ex.getMessage();
			System.out.println("FAILED:" +ex.getMessage());
		}
		
	}
	
	
	public int getArg1() {
		return arg1;
	}


	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}


	public int getArg2() {
		return arg2;
	}


	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}


	public int getMajorcode() {
		return majorcode;
	}


	public void setMinorcode(int majorcode) {
		this.minorcode = majorcode;
	}
	
	public int getMinorcode() {
		return minorcode;
	}


	public void setMajorcode(int majorcode) {
		this.majorcode = majorcode;
	}


	public String getMajormsg() {
		return majormsg;
	}


	public void setMajormsg(String majormsg) {
		this.majormsg = majormsg;
	}


	public String getMinormsg() {
		return minormsg;
	}


	public void setMinormsg(String minormsg) {
		this.minormsg = minormsg;
	}

}
