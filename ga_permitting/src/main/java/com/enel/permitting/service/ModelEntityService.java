package com.enel.permitting.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enel.permitting.beans.ModelEntityResult;
import com.enel.permitting.config.ApplicationConstants;
import com.enel.permitting.config.Country;
import com.enel.permitting.model.ModelEntity;
import com.enel.permitting.repository.country1.ModelEntityCountry1Repository;
import com.enel.permitting.repository.country2.ModelEntityCountry2Repository;
import com.enel.permitting.util.JSONHelper;

@Service
public class ModelEntityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelEntityService.class);

    @Autowired
    private ModelEntityCountry1Repository modelEntityCountry1Repository;

    @Autowired
    private ModelEntityCountry2Repository modelEntityCountry2Repository;

    public ModelEntityService() {
    }
    
    public ModelEntityResult createModelEntity(ModelEntity modelEntity, Country country) {
    	 	
    	try {
    		
        	LOGGER.info(JSONHelper.toJSONwithPrettyPrint(modelEntity));
        	HashMap<String,Object> mapResult = null;
        	
        	Country currentCountry = Country.fromName(country.name());

    	    switch(currentCountry){
    	    
    	    	case country1:
    	    		
    	    		modelEntityCountry1Repository.startSession(modelEntity.getColumn_3(), ApplicationConstants.APPLICATION_INIZIALIZATION, country.name());
    	    		mapResult = modelEntityCountry1Repository.saveModelEntity(
    	        			modelEntity.getIdModelEntity(),modelEntity.getColumn_1(), modelEntity.getColumn_2(),modelEntity.getColumn_3(), modelEntity.getColumn_4());
    	    		modelEntityCountry1Repository.endSession();
    	    		break;
    	    	
    	    	case country2:
    	    		
    	    		modelEntityCountry2Repository.startSession(modelEntity.getColumn_3(), ApplicationConstants.APPLICATION_INIZIALIZATION, country.name());
    	    		mapResult = modelEntityCountry2Repository.saveModelEntity(
    	        			modelEntity.getIdModelEntity(),modelEntity.getColumn_1(), modelEntity.getColumn_2(),modelEntity.getColumn_3(), modelEntity.getColumn_4());
    	    		modelEntityCountry2Repository.endSession();
    	        	break;
    	        	
    	    	default: 
    	    		break;    		    	    	
    	    }
        	  
    		if(mapResult != null)
    			LOGGER.info(JSONHelper.toJSONwithPrettyPrint(mapResult));
   		
    		return JSONHelper.convertValue(mapResult, ModelEntityResult.class);
    		
    	} catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		LOGGER.info(ex.getCause().toString());
    		ex.getStackTrace();
    		return null;
    	}
    	 	
    }

}
