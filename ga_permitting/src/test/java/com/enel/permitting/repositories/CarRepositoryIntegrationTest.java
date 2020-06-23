package com.enel.permitting.repositories;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import com.enel.permitting.GaPermittingApplication;
import com.enel.permitting.entity.Car;
import com.enel.permitting.repository.CarRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GaPermittingApplication.class)
//@Sql(scripts = { "/test-car-data.sql" })
//@Sql(scripts = "/test-car-cleanup.sql", executionPhase = AFTER_TEST_METHOD)
public class CarRepositoryIntegrationTest {

    private static final String CAR_BMW_MODEL_ = "BMW";
    private static final String CAR_BENZ_MODEL_ = "BENZ";
    private static final String CAR_PORCHE_MODEL_ = "PORCHE";
    
    private Integer countResult;
    
    @Autowired
    private CarRepository carRepository;

    @Before
    public void setUp() {

        Car car1 = new Car(CAR_BMW_MODEL_, 2000);
        Car car2 = new Car(CAR_BENZ_MODEL_, 2010);
        Car car3 = new Car(CAR_PORCHE_MODEL_, 2005);
        Car car4 = new Car(CAR_PORCHE_MODEL_, 2004);

        carRepository.saveAll(Arrays.asList(car1, car2, car3, car4));
    }

    /*@After
    public void tearDown() {

        carRepository.deleteAll();
    }*/
    
    @Test 
    public void whenFindByModel_getTotalCarsByModel() {
    	
    	//Integer resultCount;
    	    	
    	//assertEquals(2,carRepository.getTotalCarsByModel(CAR_BMW_MODEL_));
    	//assertEquals(2,carRepository.getTotalCarsByModelProcedureName(CAR_BMW_MODEL_));
    	
    	//carRepository.GET_TOTAL_CARS_BY_MODEL(CAR_BMW_MODEL_);
    	//carRepository.getTotalCarsByModelProcedureName(CAR_BMW_MODEL_);
    	//assertEquals(2,carRepository.getTotalCarsByModelEntiy(CAR_BMW_MODEL_).intValue());
    	assertEquals(2,carRepository.getTotalCarsByModelEntiy(CAR_BMW_MODEL_).intValue());
    	//assertEquals(2,carRepository.getTotalCarsByModelProcedureName(CAR_BMW_MODEL_));
    	//Long year = new Long(2001);
    	//carRepository.getNativeTotalCarsByModel(CAR_BMW_MODEL_);
    	//assertEquals(2,carRepository.findCarsAfterYear(2011).size());
    	
    	//assertEquals(13,carRepository.getAllCars().size());
    
    }

    @Test
    public void whenFindByModel_thenReturnsCorrectResult() {

        assertEquals(4, carRepository.findByModel(CAR_PORCHE_MODEL_).size());
    }

    @Test
    public void whenByYearLessThanEqual_thenReturnsCorrectResult() {

        assertEquals(8, carRepository.findByYearLessThanEqual(2018).size());
    }

    @Test
    public void whenByAgeGreaterThanEqual_thenReturnsCorrectResult() {

        assertEquals(4, carRepository.findByYearGreaterThanEqual(2018).size());
    }

    @Test
    public void whenByAgeBetween_thenReturnsCorrectResult() {

        assertEquals(11, carRepository.findByYearBetween(2000, 2020).size());
    }



}