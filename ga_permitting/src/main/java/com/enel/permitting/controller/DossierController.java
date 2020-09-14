package com.enel.permitting.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.enel.permitting.beans.DossierResponse;
import com.enel.permitting.config.ApplicationConstants;
import com.enel.permitting.config.CRUDOperation;
import com.enel.permitting.config.Country;
import com.enel.permitting.config.FilterTypes;
import com.enel.permitting.exception.DataFormatException;
import com.enel.permitting.exception.DossierStatus;
import com.enel.permitting.primarymodel.Dossier;
import com.enel.permitting.primarymodel.Glossary;
import com.enel.permitting.service.primary.DossierService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value = ApplicationConstants.BASE_BUSINESS_PROCESS_URL+"/{country}/dossier")
@Api(tags = {ApplicationConstants.API_DOCUMENTATION_TAGS})
public class DossierController extends AbstractRestHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DossierController.class);

    @Autowired
    private DossierService dossierService;
    
    @RequestMapping(value = "",
    		method = RequestMethod.POST,
    		consumes = ApplicationConstants.API_STANDARD_JSON_FORMAT,
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = ApplicationConstants.API_DOCUMENTATION_OPERATION, notes = ApplicationConstants.API_DOCUMENTATION_OPERATION_NOTES)
    public ResponseEntity<DossierResponse> createDossier( 
    //public DossierResponse createDossier( 
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, 
    					@RequestBody @Valid Dossier dossier) {
                        //HttpServletRequest request, HttpServletResponse response) {
    	
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	//if (dossier.getCountry() != null && !dossier.getCountry().equalsIgnoreCase(country.name())) throw new DataFormatException("The Country value doesn't match.");
    	DossierResponse dossierResponse = dossierService.createOrUpdateDossier(dossier,country, CRUDOperation.CREATE);
    	
    	DossierStatus dossierStatus = DossierStatus.fromCode(dossierResponse.getCode());

	    switch(dossierStatus){
	    
	    	case STATUS_0:
	    		status = HttpStatus.CREATED;
	    		break;
	    	case STATUS_4:
	    		status = HttpStatus.CREATED;
	    		break;
	    		
	    	default: 		
	    		break;
	    	
	    }
    	
    	//return dossierResponse;
    	return new ResponseEntity<DossierResponse>(dossierResponse,status);
    }
    
    
    @RequestMapping(value = "/{id}",
    		method = RequestMethod.PUT,
    		consumes = ApplicationConstants.API_STANDARD_JSON_FORMAT,
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a Dossier resource.", notes = "You have to provide a valid Dossier ID in the URL and in the payload. The ID attribute can not be updated.")
    public ResponseEntity<DossierResponse> fullUpdateDossier( 
    //public DossierResponse fullUpdateDossier( 
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, 
    					@ApiParam(value = "The ID of the existing Dossier resource.", required = true)
                        @PathVariable("id") Long id,
    					@RequestBody @Valid Dossier dossier
                        ) {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	if (!id.equals(dossier.getIdFascicolo())) throw new DataFormatException("The Dossier ID doesn't match.");
    	DossierResponse dossierResponse = dossierService.createOrUpdateDossier(dossier,country,CRUDOperation.UPDATE);
    	
    	DossierStatus dossierStatus = DossierStatus.fromCode(dossierResponse.getCode());

	    switch(dossierStatus){
	    
	    	case STATUS_0:
	    		status = HttpStatus.OK;
	    		break;
	    	case STATUS_4:
	    		status = HttpStatus.OK;
	    		break;
	    		
	    	default: 		
	    		break;
	    	
	    }
    	
    	//return dossierResponse;
    	return new ResponseEntity<DossierResponse>(dossierResponse,status);
    }
    
    @RequestMapping(value = "/{id}",
    		method = RequestMethod.PATCH,
    		consumes = ApplicationConstants.API_STANDARD_JSON_FORMAT,
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a Dossier resource.", notes = "You have to provide a valid Dossier ID in the URL and in the payload. The ID attribute can not be updated.")
    public ResponseEntity<DossierResponse> updateDossier( 
    //public DossierResponse updateDossier( 
    					@ApiParam(value = "Country Reference", required = true, type = "Country")
    					@PathVariable("country") Country country, 
    					@ApiParam(value = "The ID of the existing Dossier resource.", required = true)
                        @PathVariable("id") Long id,
    					@RequestBody @Valid Dossier dossier
                        ) {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	if (!id.equals(dossier.getIdFascicolo())) throw new DataFormatException("The Dossier ID doesn't match.");
    	DossierResponse dossierResponse = dossierService.createOrUpdateDossier(dossier,country,CRUDOperation.PATCH);
    	

    	DossierStatus dossierStatus = DossierStatus.fromCode(dossierResponse.getCode());

	    switch(dossierStatus){
	    
	    	case STATUS_0:
	    		status = HttpStatus.OK;
	    		break;
	    	case STATUS_4:
	    		status = HttpStatus.OK;
	    		break;
	    		
	    	default: 		
	    		break;
	    	
	    }
    	
    	//return dossierResponse;
    	return new ResponseEntity<DossierResponse>(dossierResponse,status);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Dossier.", notes = "You have to provide a valid Dossier ID.")
    public
    @ResponseBody Optional<Dossier> getDossier(
    						@ApiParam(value = "Country Reference", required = true, type = "Country")
    						@PathVariable("country") Country country,
    						@ApiParam(value = "The ID of the Dossier.", required = true)
                            @PathVariable("id") Long id,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Optional<Dossier> dossier = dossierService.getDossier(id,country.name());
        checkResourceFound(dossier.get());
        return dossier;
    }
   
   
    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a list of all dossier.", notes = "")
    public @ResponseBody Page<Dossier> getDossierByCountry(
    		@ApiParam(value = "COUNTRY", required = true, type = "Country")
    		@PathVariable("country") Country country, 
    		@RequestParam(value = "STATOFASCICOLO", required = false) String code,
    		@RequestParam(value = "TIPORICHIESTA", required = false) String tiporichiesta,
    		@RequestParam(value = "TIPORISPOSTA", required = false) String tiporisposta,
    		@RequestParam(value = "PAGENUMBER", required = false, defaultValue = ApplicationConstants.API_PAGE_NUMBER_DEFAULT) Integer pageNumber,
    		@RequestParam(value = "PAGESIZE", required = false, defaultValue = ApplicationConstants.API_PAGE_SIZE_DEFAULT) Integer pageSize,
    		HttpServletRequest request, HttpServletResponse response){
    	
    	return dossierService.getPaginationDossiers(code, tiporichiesta, tiporisposta, country, pageNumber, pageSize);

    }
    
    @RequestMapping(value = "/glossary",
            method = RequestMethod.GET,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get dossier's Glossary .", notes = "")
    public @ResponseBody List<Glossary> getGlossaryByCountry(
    		@ApiParam(value = "Country Reference", required = true, type = "Country")
    		@PathVariable("country") Country country, 
    		@RequestParam(value = "columnType", required = true) FilterTypes filterType,
    		HttpServletRequest request, HttpServletResponse response){
    	
    	return dossierService.getGlossaryByColumnNameAndCountry(filterType, country);
    }
    
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete Dossier.", notes = "You have to provide a valid Dossier ID.")
    public
    @ResponseBody DossierResponse deleteDossier(
    						@ApiParam(value = "Country Reference", required = true, type = "Country")
    						@PathVariable("country") Country country,
    						@ApiParam(value = "The ID of the Dossier.", required = true)
                            @PathVariable("id") Long id,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	DossierResponse dossierResponse = dossierService.deleteDossier(id,country);
        return dossierResponse;
    }
    
    /*@RequestMapping(value = "/{id}",
    		method = RequestMethod.PATCH,
    		consumes = "application/json-patch+json",
    		produces = ApplicationConstants.API_STANDARD_JSON_FORMAT)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Patch a Dossier resource.", notes = "You have to provide a valid Dossier ID in the URL and in the payload. The ID attribute can not be updated.")
    public DossierResponse patchDossier( 
    		@ApiParam(value = "Country Reference", required = true, type = "Country")
    		@PathVariable("country") Country country, 
    		@ApiParam(value = "The ID of the existing Dossier resource.", required = true)
    		@PathVariable("id") Long id,
    		@RequestBody JsonPatch jsonPatch
    		) {

    	Optional<Dossier> dossier = dossierService.getDossier(id,country.name());
    	checkResourceFound(dossier.get());
    	DossierResponse dossierResponse = dossierService.patchDossier(jsonPatch,dossier.get(),country);

    	return dossierResponse;

    }*/

    
}
