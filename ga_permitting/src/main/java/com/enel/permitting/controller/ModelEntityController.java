package com.enel.permitting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.enel.permitting.beans.ModelEntityResult;
import com.enel.permitting.config.Country;
import com.enel.permitting.model.ModelEntity;
import com.enel.permitting.service.ModelEntityService;
import com.enel.permitting.config.ApplicationConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


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
    public ModelEntityResult createModelEntity( 
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, @RequestBody ModelEntity modelEntity,
                        HttpServletRequest request, HttpServletResponse response) {
  
    	ModelEntityResult createdModelEntity = modelEntityService.createModelEntity(modelEntity,country);
    	if(createdModelEntity != null && createdModelEntity.getParam_out_1() != null && createdModelEntity.getParam_out_1() > 0)
    		response.setHeader("Location", request.getRequestURL().append("/").append(createdModelEntity.getParam_out_1()).toString());
    	
    	return createdModelEntity;
    }
   
}
