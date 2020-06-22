package com.enel.permitting.service;

import com.enel.permitting.entity.Fascicle;
import com.enel.permitting.repository.FascicleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class FascicleService {

    private static final Logger log = LoggerFactory.getLogger(FascicleService.class);

    @Autowired
    private FascicleRepository fascicoloRepository;

    /*@Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;*/

    public FascicleService() {
    }

    public Fascicle createFascicolo(Fascicle fascicolo) {
        return fascicoloRepository.save(fascicolo);
    }

    public Fascicle getFascicolo(long id) {
        //return fascicoloRepository.findOne(id);
        return fascicoloRepository.findById(id).get();
    }

    public void updateFascicolo(Fascicle fascicolo) {
        fascicoloRepository.save(fascicolo);
    }

    public void deleteFascicolo(Long id) {
        fascicoloRepository.deleteById(id);
    }

    //http://goo.gl/7fxvVf
    public Page<Fascicle> getAllFascicoli(Integer page, Integer size) {
    	
    	Pageable sortedByName = PageRequest.of(0, 3, Sort.by("name"));
    	Pageable sortedById = PageRequest.of(0, 3, Sort.by("id"));		 
    	//Pageable sortedByPriceDesc = PageRequest.of(0, 3, Sort.by("price").descending());
        
    	Page pageOfHotels = fascicoloRepository.findAll(sortedById);
        // example of adding to the /metrics
        /*if (size > 50) {
            counterService.increment("Enel.FascicoloService.getAll.largePayload");
        }*/
        return pageOfHotels;
    }
}
