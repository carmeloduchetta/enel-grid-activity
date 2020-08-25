package com.enel.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enel.beans.ModelEntityResponse;
import com.enel.config.Country;
import com.enel.config.CRUDOperation;
import com.enel.config.FilterTypes;
import com.enel.exception.ModelEntityStatus;
import com.enel.model.ModelEntity;
import com.enel.model.Glossary;
import com.enel.model.id.IdModelEntity;
import com.enel.repository.ModelEntityRepository;
import com.enel.repository.GlossaryRepository;
import com.enel.util.JSONHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@Service
public class ModelEntityService implements IModelEntityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelEntityService.class);

    @Autowired
    private ModelEntityRepository modelEntityRepository;
    
    @Autowired
    private GlossaryRepository glossaryEntityRepository;

    public ModelEntityService() {
    	
    }
    
    public ModelEntityResponse createOrUpdateModelEntity(ModelEntity modelEntity, Country country, CRUDOperation dbOperation) {
	    	 	
    	try {
    		
        	LOGGER.info(JSONHelper.toJSONwithPrettyPrint(modelEntity));    	    
    	    LOGGER.info("start saving");
    	    modelEntity.setCountry(country.name().toUpperCase()); 
    	    HashMap<String,String> result = validationCheck(modelEntity,dbOperation);
    	    
    	    Optional <ModelEntity> opModelEntity = getModelEntity(modelEntity.getIdModelEntity(),modelEntity.getCountry());
    	    
    	    switch(dbOperation){
    	    
    	    	case CREATE:
    	    		opModelEntity.ifPresent(theModelEntity -> result.put(FilterTypes.IDMODELENTITY.getDescription(), String.valueOf(modelEntity.getIdModelEntity())));
    	    		
    	    		break;
    	    		
    	    	case UPDATE:
    	    		if(!opModelEntity.isPresent())
    	    		result.put(FilterTypes.IDMODELENTITY.getDescription(), String.valueOf(modelEntity.getIdModelEntity()));
    	    		
    	    		break;
    	    		
    	    	default: 		
    	    		break;
    	    	
    	    }
    	        	    
    	    if(result.isEmpty()) {
    	    	  
    	    	Long createdIdModelEntity = null;
    	    	ModelEntity createdModelEntity = null;
    	    	String message = null;
    	    	
    	    	switch(dbOperation){
        	    
	    	    	case CREATE:
	    	    		
	    	    		if(null == modelEntity.getIdModelEntity()) {   	    	
	        	    		createdModelEntity = modelEntityRepository.save(modelEntity);
	        	    		createdIdModelEntity = createdModelEntity.getIdModelEntity();
	        	    	}
	    	    		 
	    	    		else {
	        	    		insertModelEntity(modelEntity);
	        	    		createdIdModelEntity = modelEntity.getIdModelEntity();
	        	    	}
	    	    		
	    	    		message = "ModelEntity created with Success.";
	    	    		break;
	    	    		
	    	    	case UPDATE:
	    	   
	    	    		createdModelEntity = modelEntityRepository.save(modelEntity);
	    	    		createdIdModelEntity = createdModelEntity.getIdModelEntity();
	    	    		message = "ModelEntity updated with Success.";
	    	    		break;
	    	    		
	    	    	case PATCH:
	    	    		
	    	    		//Optional <ModelEntity> opModelEntity = getModelEntity(modelEntity.getIdModelEntity(),modelEntity.getCountry());	    	    		
	    	    		JSONHelper.nullAwareBeanCopy(opModelEntity.get(),modelEntity);
	    	    		createdModelEntity = modelEntityRepository.save(opModelEntity.get());
	    	    		createdIdModelEntity = createdModelEntity.getIdModelEntity();
	    	    		
	    	    		message = "ModelEntity updated with Success.";
	    	    		break;
	    	    		
	    	    	default: 		
	    	    		break;
    	    	
    	    	}
    	    	
    	    		
    	        result.put(FilterTypes.IDMODELENTITY.getDescription(), String.valueOf(createdIdModelEntity));
    	    	
    	    	return getModelEntityResponse(ModelEntityStatus.STATUS_0.getCode(),message,result,null);
    	    	
    	    }
    	    	    
    	    return getModelEntityResponse(ModelEntityStatus.STATUS_8.getCode(),"Values: "+result.values()+" not valid for "+result.keySet(),null,null);
    	       	        		
    	} catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		ex.getStackTrace();
    		return getModelEntityResponse(ModelEntityStatus.STATUS_12.getCode(),null,null,ex.getMessage());
    	}	 
    	
    }
        
    public ModelEntityResponse getModelEntityResponse(Integer code, String message, Map<String,String> result, String details) {
    	return ModelEntityResponse.builder()
    			.additionalResponseData(result)
    			.code(code)
    			.details(details)
    			.message(message)
    			.build();
    }
	    
    public Optional<ModelEntity> getModelEntity(Long idModelEntity, String country) {
     
    	 return findById(IdModelEntity.builder().idModelEntity(idModelEntity).country(country.toUpperCase()).build());
    	 
    }
    
    public List<ModelEntity> getModelEntityByCountry(Country country){
    	    	
    	LOGGER.info("Country: "+country.name());
    
    	return modelEntityRepository.findModelEntityByCountry(country.name().toUpperCase());
    	
    }

      
    public List<Glossary> getGlossaryByColumnNameAndCountry(FilterTypes filterType,Country country){
    	
    	LOGGER.info("Column Type: "+filterType.getDescription()+" - Country: "+country.name().toUpperCase());
    	return glossaryEntityRepository.findGlossaryByColumnNameAndCountry(filterType.getDescription(), country.name().toUpperCase());
    
    }
    
    public void insertModelEntity(ModelEntity modelEntity) {
    	
    	modelEntityRepository.insertModelEntity(modelEntity.getCountry(), modelEntity.getIdModelEntity(), modelEntity.getColumn_1(), modelEntity.getColumn_2(), modelEntity.getColumn_3(), modelEntity.getColumn_4());    	
    }   	
    
    public HashMap<String,String> validationCheck(ModelEntity modelEntity, CRUDOperation dboperation) {
    	
    	/* Metodo che si occupa di fare il check sul glossario dell'entità dei valori passati nelle operazioni di CRUD.
    	 * L'HashMap istanziato viene popolato solamente nel caso il controllo di validazione fallisca
         * Il controllo viene fatto leggendo in modo iterativo gli stati possibili per le colonne (ex. FILTER_COLUMN2) sulle quali si vuole fare la validazione
         */
    	
    	HashMap<String,String> currentResult = new HashMap<String,String>(); 
    	List<Glossary> current = null;
    	 	
    	try {
    		    		
    		 if(StringUtils.isNotBlank(modelEntity.getColumn_2())) 
    			currentResult.put(FilterTypes.FILTER_COLUMN2.getDescription(), modelEntity.getColumn_2());   		
    		
    		Iterator<Map.Entry<String, String>> iterator = currentResult.entrySet().iterator();
    	    while (iterator.hasNext()) {
    	        Map.Entry<String, String> entry = iterator.next();
    	        LOGGER.info(entry.getKey() + ":" + entry.getValue());
    	        current = glossaryEntityRepository.findGlossaryByColumnNameAndCodeAndCountry(entry.getKey(),entry.getValue(),modelEntity.getCountry());
    	        if(current != null && current.size()>=1)
    	            iterator.remove();
    	    } 		
    		    	    
    	}
    	catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		LOGGER.info(ex.getCause().toString());
    		ex.getStackTrace();
    	}
    	
    	return currentResult;
    	    	
    }

	@Override
	public Optional<ModelEntity> findById(IdModelEntity id) {
		// TODO Auto-generated method stub
		return modelEntityRepository.findById(id);
	}
    
    public ModelEntityResponse deleteModelEntity(Long idModelEntity,Country country) {
    	
    	try {

    		LOGGER.info("start delete");
    		
    		modelEntityRepository.deleteById(IdModelEntity.builder().idModelEntity(idModelEntity).country(country.name().toUpperCase()).build());
  			
    	    return getModelEntityResponse(ModelEntityStatus.STATUS_0.getCode(),"ModelEntity deleted with Success.",null,null);

    	}catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		ex.getStackTrace();
    		return getModelEntityResponse(ModelEntityStatus.STATUS_8.getCode(),"ModelEntity not found",null,null);
    	}	 

    }

}
