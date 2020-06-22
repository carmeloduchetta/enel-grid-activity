package com.enel.permitting.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.EntityResult;

import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.ParameterMode;

@Entity
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
	            entityClass = Car.class,
	            fields = {
	                @FieldResult(
	                    name = "mioid",
	                    column = "id"
	                )
	            }
	        )
	    }
	)
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
