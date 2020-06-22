package com.enel.permitting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.enel.permitting.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	List<Car> findByModel(String model);
    List<Car> findByYearLessThanEqual(Integer year);
    List<Car> findByYearGreaterThanEqual(Integer year);
    List<Car> findByYearBetween(Integer startYear, Integer endYear);

    @Procedure
    void GET_TOTAL_CARS_BY_MODEL(String model);
    
    @Procedure("GET_TOTAL_CARS_BY_MODEL")
    int getTotalCarsByModel(String model);
    
    @Procedure(procedureName = "GET_TOTAL_CARS_BY_MODEL")
    void getTotalCarsByModelProcedureName(String model);
    
    @Procedure(value = "GET_TOTAL_CARS_BY_MODEL")
    int getTotalCarsByModelValue(String model);

    @Procedure(name = "getTotalCarsbyModelEntity")
    Integer getTotalCarsByModelEntiy(@Param("model_in") String model,@Param("count_out") Integer count);
        
    //@Query(value = "CALL FIND_CARS_AFTER_YEAR(:year_in)", nativeQuery = true)
    //List<Car> findCarsAfterYear(@Param("year_in") Integer year_in);
    
    //CALL WLSPOOL_ARDESIA.GET_TOTAL_CARS_BY_MODEL("BMW",?);
    @Query(value = "CALL GET_TOTAL_CARS_BY_MODEL(:model)", nativeQuery = true)
    void getNativeTotalCarsByModel(@Param("model") String model);
    
    @Query(value = "CALL FIND_CARS_AFTER_YEAR(:year_in)", nativeQuery = true)
    List<Car> findCarsAfterYear(@Param("year_in") Integer year_in);
    
    @Query(value = "SELECT * FROM CAR", nativeQuery = true)
    List<Car> getAllCars();

}