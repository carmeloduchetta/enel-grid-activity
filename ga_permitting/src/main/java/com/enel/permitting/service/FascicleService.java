package com.enel.permitting.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enel.permitting.beans.FascicleResult;
import com.enel.permitting.entity.Fascicle;
import com.enel.permitting.repository.FascicleRepository;
import com.enel.permitting.util.JSONHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Sample service to demonstrate what the API would use to get things done
 */
@Service
public class FascicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FascicleService.class);

    @Autowired
    private FascicleRepository fascicoloRepository;

    public FascicleService() {
    }

    public Fascicle createFascicolo(Fascicle fascicolo) {
        return fascicoloRepository.save(fascicolo);
    }
    
    public FascicleResult createFascicle(Fascicle fascicle) {
    	 	
    	try {
    		
        	LOGGER.info(JSONHelper.toJSONwithPrettyPrint(fascicle));
        	  
    		HashMap<String,Object> mapResult = fascicoloRepository.saveFascicle(
    			fascicle.getIdfascicolo() == 0 ? null : fascicle.getIdfascicolo(),fascicle.getIdente() == 0 ? null : fascicle.getIdente(), fascicle.getIddestinatario() == 0 ? null : fascicle.getIddestinatario(),fascicle.getCdfascicolo(),fascicle.getDsfascicolo(),
    			fascicle.getCditer(), fascicle.getUserins(), fascicle.getIdinddestinat() == 0 ? null : fascicle.getIdinddestinat(), fascicle.getIdunitaresp() == 0 ? null : fascicle.getIdunitaresp(), fascicle.getCdtiporichiesta(),
    			fascicle.getCdtiporisposta(),fascicle.getGgterminilegge() == 0 ? null : fascicle.getGgterminilegge(),fascicle.getSwterminilegge(), fascicle.getGgtempomedio() == 0 ? null : fascicle.getGgtempomedio(),fascicle.getCdstatoiteriniz(),
    			fascicle.getCdstatoiterfine(),fascicle.getDtfirma(),fascicle.getDtspedizione(),fascicle.getDtricevutaritorno(),null, 
    			fascicle.getIdpumaistanza(), fascicle.getProtpumaistanza(), fascicle.getDtbrevimanuist(), fascicle.getDtinizioattesa(),null,
    			fascicle.getDtrisposta(), fascicle.getCdrispostaottenuta(), null, fascicle.getCdpumarisposta(), fascicle.getIdprofilopuma(),
    			fascicle.getDtbrevimanurisp(), fascicle.getDtfascfine(), fascicle.getCdesitofasc(), fascicle.getSwprescrizione(),fascicle.getNoteprescrizione());
    		
    		LOGGER.info(JSONHelper.toJSONwithPrettyPrint(mapResult));
    		
    		return JSONHelper.convertValue(mapResult, FascicleResult.class);
    		
    	} catch(Exception ex) {
    		ex.getStackTrace();
    		return null;
    	}
    	
    	
    }

    public Fascicle getFascicolo(Integer id) {
        //return fascicoloRepository.findOne(id);
        return fascicoloRepository.findById(id).get();
    }

    public void updateFascicolo(Fascicle fascicolo) {
        fascicoloRepository.save(fascicolo);
    }

    public void deleteFascicolo(Integer id) {
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
