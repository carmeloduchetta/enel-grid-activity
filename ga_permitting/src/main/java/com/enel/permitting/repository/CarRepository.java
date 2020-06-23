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

    @Procedure(name = "getTotalCarsbyModelEntity")
    Integer getTotalCarsByModelEntiy(@Param("model_in") String model);
       

}