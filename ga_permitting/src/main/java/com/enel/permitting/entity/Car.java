package com.enel.permitting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
//@Table(name = "CAR", schema = "WLSPOOL_ARDESIA")
@NamedNativeQuery(
	    name = "FIND_CARS_AFTER_YEAR",
	    query = "{ ? = call FIND_CARS_AFTER_YEAR( ? ) }",
	    callable = true,
	    resultSetMapping = "cars"
	)
	@SqlResultSetMapping(
	    name = "cars",
	    entities = {
	        @EntityResult(
	            entityClass = Car.class
	        )
	    }
	)

@NamedStoredProcedureQuery(name = "getTotalCarsbyModelEntity", procedureName = "GET_TOTAL_CARS_BY_MODEL", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "model_in", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class) })

public class Car {
	
    @Id
    @GeneratedValue()
    private long id;

    @Column
    private String model;

    @Column
    private Integer year;

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Car() {
    }

    public Car(String model, Integer year) {
        this.model = model;
        this.year = year;
    }
}
