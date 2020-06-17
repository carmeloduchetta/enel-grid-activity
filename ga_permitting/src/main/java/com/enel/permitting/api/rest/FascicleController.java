package com.enel.permitting.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.enel.permitting.domain.Fascicle;
import com.enel.permitting.exception.DataFormatException;
import com.enel.permitting.service.FascicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/permitting/v1/fascicle")
@Api(tags = {"Fascicles"})
public class FascicleController extends AbstractRestHandler {

    @Autowired
    private FascicleService fascicleService;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Fascicle resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createFascicle(@RequestBody Fascicle fascicle,
                                 HttpServletRequest request, HttpServletResponse response) {
        Fascicle createdFascicolo = this.fascicleService.createFascicolo(fascicle);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdFascicolo.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all fascicles.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public
    @ResponseBody
    Page<Fascicle> getAllHotel(@ApiParam(value = "The page number (zero-based)", required = true)
                                      @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @ApiParam(value = "Tha page size", required = true)
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.fascicleService.getAllFascicoli(page, size);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single fascicle.", notes = "You have to provide a valid fascicle ID.")
    public
    @ResponseBody
    Fascicle getFascicolo(@ApiParam(value = "The ID of the fascicle.", required = true)
                             @PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Fascicle fascicolo = this.fascicleService.getFascicolo(id);
        checkResourceFound(fascicolo);
        //todo: http://goo.gl/6iNAkz
        return fascicolo;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a fascicle resource.", notes = "You have to provide a valid fascicle ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateFascicle(@ApiParam(value = "The ID of the existing fascicle resource.", required = true)
                                 @PathVariable("id") Long id, @RequestBody Fascicle fascicolo,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.fascicleService.getFascicolo(id));
        if (id != fascicolo.getId()) throw new DataFormatException("ID doesn't match!");
        this.fascicleService.updateFascicolo(fascicolo);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a fascicle resource.", notes = "You have to provide a valid fascicle ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteHotel(@ApiParam(value = "The ID of the existing fascicle resource.", required = true)
                                 @PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.fascicleService.getFascicolo(id));
        this.fascicleService.deleteFascicolo(id);
    }
}
