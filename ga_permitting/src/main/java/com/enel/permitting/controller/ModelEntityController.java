package com.enel.permitting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping(value = "/permitting/{country}/modelEntity")
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
  
    	ModelEntityResult createdModelEntity = modelEntityService.createFascicle(modelEntity,country);
    	if(createdModelEntity != null && createdModelEntity.getAn_idfascicolo() != null && createdModelEntity.getAn_idfascicolo() > 0)
    		response.setHeader("Location", request.getRequestURL().append("/").append(createdModelEntity.getAn_idfascicolo()).toString());
    	
    	return createdModelEntity;
    }
    
    /** to delete **/
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single fascicle.", notes = "You have to provide a valid fascicle ID.")
    public
    @ResponseBody
    ModelEntity getFascicolo(   @ApiParam(value = "Country Reference", required = true)
    						 @PathVariable("country") Country country,
    						 @ApiParam(value = "The ID of the fascicle.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelEntity fascicolo = this.modelEntityService.getFascicolo(id,country);
        checkResourceFound(fascicolo);

        return fascicolo;
    }

   
}
