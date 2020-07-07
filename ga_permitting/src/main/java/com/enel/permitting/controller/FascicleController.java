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

import com.enel.permitting.beans.FascicleResult;
import com.enel.permitting.config.Country;
import com.enel.permitting.model.Fascicle;
import com.enel.permitting.service.FascicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/permitting/v1/{country}/fascicle")
@Api(tags = {"Fascicles"})
public class FascicleController extends AbstractRestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(FascicleController.class);

    @Autowired
    private FascicleService fascicleService;
    
    @RequestMapping(value = "",
    		method = RequestMethod.POST,
    		consumes = "application/json; charset=UTF-8",
    		produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Fascicle resource.", notes = "Returns the URL of the new resource in the Location header.")
    public FascicleResult createFascicle( 
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, @RequestBody Fascicle fascicle, //) {
                         HttpServletRequest request, HttpServletResponse response) {
  
    	FascicleResult createdFascicle = fascicleService.createFascicle(fascicle,country);
    	if(createdFascicle != null && createdFascicle.getAn_idfascicolo() != null && createdFascicle.getAn_idfascicolo() > 0)
    		response.setHeader("Location", request.getRequestURL().append("/").append(createdFascicle.getAn_idfascicolo()).toString());
    	
    	return createdFascicle;
    }
    

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single fascicle.", notes = "You have to provide a valid fascicle ID.")
    public
    @ResponseBody
    Fascicle getFascicolo(   @ApiParam(value = "Country Reference", required = true)
    						 @PathVariable("country") Country country,
    						 @ApiParam(value = "The ID of the fascicle.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Fascicle fascicolo = this.fascicleService.getFascicolo(id,country);
        checkResourceFound(fascicolo);

        return fascicolo;
    }

   
}
