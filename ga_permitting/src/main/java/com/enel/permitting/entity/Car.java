package com.enel.permitting.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.enel.permitting.beans.CarResult;
import com.enel.permitting.beans.Document;

@Entity
//@Table(name = "CAR", schema = "WLSPOOL_ARDESIA")
@Table(name = "CAR", schema = "ARDESIAI")

@NamedNativeQueries({
	@NamedNativeQuery(
		    name = "FIND_CARS_AFTER_YEAR",
		    query = "{ ? = call ARDESIAI.PCK_GEST_FASCREAL.FIND_CARS_AFTER_YEAR( ? ) }",
		    callable = true,
		    resultSetMapping = "cars"
	),
	@NamedNativeQuery(
		    name = "getDocuments",
		    query = "{ ? = call ARDESIAI.PCK_GEST_FASCREAL.GET_LISTA_DOCUMENTI( ? ) }",
		    callable = true,
		    resultSetMapping = "documents"
	)
})


@SqlResultSetMappings({
	@SqlResultSetMapping(
		    name = "documents",
		    classes = @ConstructorResult(
		    		targetClass = Document.class, columns = {
		    				@ColumnResult(name = "cddocumento", type = String.class),
		    				@ColumnResult(name = "dsdocumento", type = String.class),
		    				@ColumnResult(name = "iddocumento", type = Integer.class),
		    				@ColumnResult(name = "dtstampa", type = String.class),
		    				@ColumnResult(name = "idprofilopuma", type = String.class)
		    })
	),
	@SqlResultSetMapping(
		    name = "cars",
		    		 classes = @ConstructorResult(
		 		    		targetClass = CarResult.class, columns = {
		 		    				@ColumnResult(name = "id", type = long.class),
		 		    				@ColumnResult(name = "model", type = String.class),
		 		    				@ColumnResult(name = "year", type = Integer.class)
		 		    		})
		    /*entities = {
		        @EntityResult(
		            entityClass = Car.class
		        )
		    }*/
	)
})
@NamedStoredProcedureQuery(name = "getTotalCarsbyModelEntity", procedureName = "ARDESIAI.GET_TOTAL_CARS_BY_MODEL", parameters = {
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
