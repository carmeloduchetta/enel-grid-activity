package com.enel.repository.country2;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.enel.model.ModelEntity;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

public interface ModelEntityCountry2Repository extends JpaRepository<ModelEntity, Long> {	
    
    @Procedure(name = "ContextProcedure1")
    HashMap<String,Object> saveModelEntity(
    		@Param("<name_param1>") Long param1,
    		@Param("<name_param2>") Long param2,
    		@Param("<name_param3>") Integer param3,
    		@Param("<name_param4>") String param4,
    		@Param("<name_param5>") Date param5		
    );
    
    @Procedure(name = "ContextProcedure2")
    void startSession(
    		@Param("<name_param1>") String param1,
    		@Param("<name_param2>") String param2,
    		@Param("<name_param3>") String param3    		
    );
    
    @Procedure(name = "ContextProcedure3")
    void endSession();
 
}
