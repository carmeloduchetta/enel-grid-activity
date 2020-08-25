package com.enel.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.enel.beans.ModelEntityResponse;
import com.enel.config.ApplicationConstants;
import com.enel.config.CRUDOperation;
import com.enel.config.Country;
import com.enel.config.FilterTypes;
import com.enel.exception.DataFormatException;
import com.enel.exception.ModelEntityStatus;
import com.enel.model.ModelEntity;
import com.enel.model.Glossary;
import com.enel.service.ModelEntityService;
import com.github.fge.jsonpatch.JsonPatch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;


@RestController
@RequestMapping(value = ApplicationConstants.BASE_BUSINESS_PROCESS_URL+"/{country}/modelEntity")
@Api(tags = {ApplicationConstants.API_DOCUMENTATION_TAGS})
public class ModelEntityController extends AbstractRestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelEntityController.class);

    @Autowired
    private ModelEntityService modelEntityService;
    
    @RequestMapping(value = "",
    		method = RequestMethod.POST,
    		consumes = ApplicationConstants.API_STANDARD_JSON_FORMAT,
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = ApplicationConstants.API_DOCUMENTATION_OPERATION, notes = ApplicationConstants.API_DOCUMENTATION_OPERATION_NOTES)
    public ResponseEntity<ModelEntityResponse> createModelEntity( 
     
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, 
    					@RequestBody @Valid ModelEntity modelEntity) {
                      
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	//if (modelEntity.getCountry() != null && !modelEntity.getCountry().equalsIgnoreCase(country.name())) throw new DataFormatException("The Country value doesn't match.");
    	ModelEntityResponse modelEntityResponse = modelEntityService.createOrUpdateModelEntity(modelEntity,country, CRUDOperation.CREATE);
    	
    	ModelEntityStatus modelEntityStatus = ModelEntityStatus.fromCode(modelEntityResponse.getCode());

	    switch(modelEntityStatus){
	    
	    	case STATUS_0:
	    		status = HttpStatus.CREATED;
	    		break;
	    		
	    	case STATUS_4:
	    		status = HttpStatus.CREATED; 
	    		
	    	default: 		
	    		break;
	    	
	    }
    	
    	return new ResponseEntity<ModelEntityResponse>(modelEntityResponse,status);
    }
    
    
    @RequestMapping(value = "/{id}",
    		method = RequestMethod.PUT,
    		consumes = ApplicationConstants.API_STANDARD_JSON_FORMAT,
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a ModelEntity resource.", notes = "You have to provide a valid ModelEntity ID in the URL and in the payload. The ID attribute can not be updated.")
    public ResponseEntity<ModelEntityResponse> fullUpdateModelEntity( 
     
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, 
    					@ApiParam(value = "The ID of the existing ModelEntity resource.", required = true)
                        @PathVariable("id") Long id,
    					@RequestBody @Valid ModelEntity modelEntity) {
    	
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	if (!id.equals(modelEntity.getIdModelEntity())) throw new DataFormatException("The ModelEntity ID doesn't match.");
    	ModelEntityResponse modelEntityResponse = modelEntityService.createOrUpdateModelEntity(modelEntity,country,CRUDOperation.UPDATE);
    	
    	ModelEntityStatus modelEntityStatus = ModelEntityStatus.fromCode(modelEntityResponse.getCode());

	    switch(modelEntityStatus){
	    
	    	case STATUS_0:
	    		status = HttpStatus.OK;
	    		break;
	    		
	    	case STATUS_4:
	    		status = HttpStatus.OK; 
	    		
	    	default: 		
	    		break;
	    	
	    }
    	
    	return new ResponseEntity<ModelEntityResponse>(modelEntityResponse,status);
    }
    
    
    @RequestMapping(value = "/{id}",
    		method = RequestMethod.PATCH,
    		consumes = ApplicationConstants.API_STANDARD_JSON_FORMAT,
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a ModelEntity resource.", notes = "You have to provide a valid ModelEntity ID in the URL and in the payload. The ID attribute can not be updated.")
    public ResponseEntity<ModelEntityResponse> updateModelEntity( 
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, 
    					@ApiParam(value = "The ID of the existing ModelEntity resource.", required = true)
                        @PathVariable("id") Long id,
    					@RequestBody @Valid ModelEntity modelEntity) {  			
    	
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
    	if (!id.equals(modelEntity.getIdModelEntity())) throw new DataFormatException("The ModelEntity ID doesn't match.");
    	ModelEntityResponse modelEntityResponse = modelEntityService.createOrUpdateModelEntity(modelEntity,country,CRUDOperation.PATCH);
    	
    	ModelEntityStatus modelEntityStatus = ModelEntityStatus.fromCode(modelEntityResponse.getCode());

	    switch(modelEntityStatus){
	    
	    	case STATUS_0:
	    		status = HttpStatus.OK;
	    		break;
	    		
	    	case STATUS_4:
	    		status = HttpStatus.OK; 
	    		
	    	default: 		
	    		break;
	    	
	    }
    	
    	return new ResponseEntity<ModelEntityResponse>(modelEntityResponse,status);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single ModelEntity.", notes = "You have to provide a valid ModelEntity ID.")
    public
    @ResponseBody Optional<ModelEntity> getModelEntity(
    						@ApiParam(value = "Country Reference", required = true, type = "Country")
    						@PathVariable("country") Country country,
    						@ApiParam(value = "The ID of the ModelEntity.", required = true)
                            @PathVariable("id") Long id,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Optional<ModelEntity> modelEntity = modelEntityService.getModelEntity(id,country.name());
        checkResourceFound(modelEntity.get());
        return modelEntity;
    }
    
    @RequestMapping(value = "/glossary",
            method = RequestMethod.GET,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get modelEntity's Glossary .", notes = "")
    public @ResponseBody List<Glossary> getGlossaryByCountry(
    		@ApiParam(value = "Country Reference", required = true, type = "Country")
    		@PathVariable("country") Country country, 
    		@RequestParam(value = "columnType", required = true) FilterTypes filterType,
    		HttpServletRequest request, HttpServletResponse response){
    	
    	return modelEntityService.getGlossaryByColumnNameAndCountry(filterType, country);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete ModelEntity.", notes = "You have to provide a valid ModelEntity ID.")
    public
    @ResponseBody ModelEntityResponse deleteModelEntity(
    						@ApiParam(value = "Country Reference", required = true, type = "Country")
    						@PathVariable("country") Country country,
    						@ApiParam(value = "The ID of the ModelEntity.", required = true)
                            @PathVariable("id") Long id,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelEntityResponse modelEntityResponse = modelEntityService.deleteModelEntity(id,country);
        return modelEntityResponse;
    }
    
}
