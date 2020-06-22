package com.enel.permitting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enel.permitting.entity.Fascicle;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

@Repository
//@Transactional
public interface FascicleRepository extends JpaRepository<Fascicle, Long> {
	
	//@Query("SELECT con FROM Contact con  WHERE con.phoneType=(:pType) AND con.lastName= (:lName)")
    //List<Fascicle> findByLastNameAndPhoneType(@Param("pType") PhoneType pType, @Param("lName") String lName);
	
	
    
    //@Query(value = "CALL maremt.p_stdoracle.condml(:an_arg1,:an_arg2);", nativeQuery = true)
    @Procedure(procedureName = "maremt.p_stdoracle.condml")
    //String findProvvCondl(Integer arg1,Integer arg2);
    String findProvvCondl(Integer arg1,Integer arg2,Integer an_major,Integer an_minor, String majormsg,String minormsg);
    
    
    @Procedure(procedureName = "maremt.p_stdoracle.condml")
    String getTotalByModelEntity(Integer an_arg1,Integer an_arg2, Integer an_major_code,Integer an_minor_code, String as_major_msg, String as_minor_msg);
    
   
    
    Fascicle findFascicoloByCity(String city);
    Page findAll(Pageable pageable);
}
