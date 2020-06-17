package com.enel.permitting.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enel.permitting.domain.Fascicle;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

@Repository
public interface FascicleRepository extends JpaRepository<Fascicle, Long> {
	
	// Storeprocedure F_GETPROVVBYELE
	//1) "AUISYS" string user
	//2) "2020" int anno
	//3) "RM" o "MD"
	//4) 35738 o 639101
	//5) 28 o 34
	//6) null
	
	//OUTPUT
	//SYS REF CURSOR
	
	//MAREMT.p_TipoProvvedimentoView.f_GetDettTipoProv (an_id_tipoprov NUMBER)
	
    /*@Query(value = "CALL maremt.p_stdoracle.condml(:an_arg1,:an_arg2,:an_major_code,:an_minor_code,:as_major_msg,:as_minor_msg);", nativeQuery = true)
    List<String> findProvvCondl(
    		@Param("an_arg1") int arg1,
    		@Param("an_arg2") int arg2,
    		@Param("an_major_code") int majorcode,
    		@Param("an_minor_code") int minorcode,
    		@Param("as_major_msg") String majormsg,
    		@Param("as_minor_msg") String minormsg
    		);*/
    
    //@Query(value = "CALL maremt.p_stdoracle.condml(:an_arg1,:an_arg2);", nativeQuery = true)
    @Procedure(procedureName = "maremt.p_stdoracle.condml")
    //String findProvvCondl(Integer arg1,Integer arg2);
    String findProvvCondl(Integer arg1,Integer arg2,Integer an_major,Integer an_minor, String majormsg,String minormsg);
    
    
    @Procedure(procedureName = "maremt.p_stdoracle.condml")
    String getTotalByModelEntity(Integer an_arg1,Integer an_arg2, Integer an_major_code,Integer an_minor_code, String as_major_msg, String as_minor_msg);
    
    /*@Procedure(value = "Fascicle.myStoredProcedure", procedureName = "maremt.p_stdoracle.condml")
    Map<String, String> executeMyStoredProcedure(
    		@Param("pInParam") Integer pInParam,
    		@Param("pInParam") Integer pInParam1,
    		@Param("pOutParam") Integer majorcode,
    		@Param("pOutParam") Integer minorcode,
    		@Param("pOutParam") String majormsg,
    		@Param("pOutParam") String minormsg
    		);*/
    
    
    /*@NamedStoredProcedureQuery(
    		name = "calculate", 
    		procedureName = "calculate", 
    		parameters = { 
    			@StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "x"), 
    			@StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "y"), 
    			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Double.class, name = "sum")
    		}
    	)*/
    
    /*@Query(value = "CALL P_STDORACLE(:an_arg1,:an_arg2);", nativeQuery = true)
    String findProvvCondl(
    		@Param("an_arg1") int arg1,
    		@Param("an_arg2") int arg2
    		);*/
    
	//Car instead of Fascicle
	
    /*@Query(value = "CALL FIND_CARS_AFTER_YEAR(:year_in);", nativeQuery = true)
    List<Car> findCarsAfterYear(@Param("year_in") Integer year_in);
	//Car instead of Fascicle*/
    
    Fascicle findFascicoloByCity(String city);
    Page findAll(Pageable pageable);
}
