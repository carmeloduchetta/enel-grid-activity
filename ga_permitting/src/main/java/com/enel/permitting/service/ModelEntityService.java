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
    private ModelEntityCountry1Repository fascicleItalyRepository;

    @Autowired
    private ModelEntityCountry2Repository fascicleSpainRepository;

    public ModelEntityService() {
    }
    
    public ModelEntityResult createFascicle(ModelEntity fascicle, Country country) {
    	 	
    	try {
    		
        	LOGGER.info(JSONHelper.toJSONwithPrettyPrint(fascicle));
        	HashMap<String,Object> mapResult = null;
        	
        	Country currentCountry = Country.fromName(country.name());

    	    switch(currentCountry){
    	    
    	    	case it:
    	    		
    	    		//fascicleItalyRepository.startSession(fascicle.userins(), ApiConstants.APPLICATION_INIZIALIZATION, country.name());
    	    		mapResult = fascicleItalyRepository.saveFascicle(
    	        			fascicle.getIdfascicolo() == null ? null : fascicle.getIdfascicolo(),fascicle.getIdente() == null ? null : fascicle.getIdente(), fascicle.getIddestinatario() == null ? null : fascicle.getIddestinatario(),fascicle.getCdfascicolo(),fascicle.getDsfascicolo(),
    	        			fascicle.getCditer(), fascicle.getUserins(), fascicle.getIdinddestinat() == null ? null : fascicle.getIdinddestinat(), fascicle.getIdunitaresp() == null ? null : fascicle.getIdunitaresp(), fascicle.getCdtiporichiesta(),
    	        			fascicle.getCdtiporisposta(),fascicle.getGgterminilegge() == null ? null : fascicle.getGgterminilegge(),fascicle.getSwterminilegge(), fascicle.getGgtempomedio() == null ? null : fascicle.getGgtempomedio(),fascicle.getCdstatoiteriniz(),
    	        			fascicle.getCdstatoiterfine(),fascicle.getDtfirma(),fascicle.getDtspedizione(),fascicle.getDtricevutaritorno(),null, 
    	        			fascicle.getIdpumaistanza(), fascicle.getProtpumaistanza(), fascicle.getDtbrevimanuist(), fascicle.getDtinizioattesa(),null,
    	        			fascicle.getDtrisposta(), fascicle.getCdrispostaottenuta(), null, fascicle.getCdpumarisposta(), fascicle.getIdprofilopuma(),
    	        			fascicle.getDtbrevimanurisp(), fascicle.getDtfascfine(), fascicle.getCdesitofasc(), fascicle.getSwprescrizione(),fascicle.getNoteprescrizione());
    	    		//fascicleItalyRepository.endSession();
    	    		break;
    	    	
    	    	case es:
    	    		
    	    		fascicleSpainRepository.startSession(fascicle.getUserins(), ApplicationConstants.APPLICATION_INIZIALIZATION, country.name());
    	    		mapResult = fascicleSpainRepository.saveFascicle(
    	        			fascicle.getIdfascicolo() == null ? null : fascicle.getIdfascicolo(),fascicle.getIdente() == null ? null : fascicle.getIdente(), fascicle.getIddestinatario() == null ? null : fascicle.getIddestinatario(),fascicle.getCdfascicolo(),fascicle.getDsfascicolo(),
    	        			fascicle.getCditer(), fascicle.getUserins(), fascicle.getIdinddestinat() == null ? null : fascicle.getIdinddestinat(), fascicle.getIdunitaresp() == null ? null : fascicle.getIdunitaresp(), fascicle.getCdtiporichiesta(),
    	        			fascicle.getCdtiporisposta(),fascicle.getGgterminilegge() == null ? null : fascicle.getGgterminilegge(),fascicle.getSwterminilegge(), fascicle.getGgtempomedio() == null ? null : fascicle.getGgtempomedio(),fascicle.getCdstatoiteriniz(),
    	        			fascicle.getCdstatoiterfine(),fascicle.getDtfirma(),fascicle.getDtspedizione(),fascicle.getDtricevutaritorno(),null, 
    	        			fascicle.getIdpumaistanza(), fascicle.getProtpumaistanza(), fascicle.getDtbrevimanuist(), fascicle.getDtinizioattesa(),null,
    	        			fascicle.getDtrisposta(), fascicle.getCdrispostaottenuta(), null, fascicle.getCdpumarisposta(), fascicle.getIdprofilopuma(),
    	        			fascicle.getDtbrevimanurisp(), fascicle.getDtfascfine(), fascicle.getCdesitofasc(), fascicle.getSwprescrizione(),fascicle.getNoteprescrizione());
    	    		fascicleSpainRepository.endSession();
    	    		/*mapResult = fascicleSpainRepository.saveFascicle(
    	        			fascicle.getIdfascicolo(),fascicle.getIdente(), fascicle.iddestinatario() == null ? null : fascicle.iddestinatario(),fascicle.getCdfascicolo(),fascicle.getDsfascicolo(),
    	        			fascicle.getCditer(), fascicle.getUserins(), fascicle.getIdinddestinat() == null ? null : fascicle.getIdinddestinat(), fascicle.getIdunitaresp() == null ? null : fascicle.getIdunitaresp(), fascicle.getCdtiporichiesta(),
    	        			fascicle.getCdtiporisposta(),fascicle.getGgterminilegge() == null ? null : fascicle.getGgterminilegge(),fascicle.getSwterminilegge(), fascicle.getGgtempomedio() == null ? null : fascicle.getGgtempomedio(),fascicle.getCdstatoiteriniz(),
    	        			fascicle.getCdstatoiterfine(),fascicle.getDtfirma(),fascicle.getDtspedizione(),fascicle.getDtricevutaritorno(),null, 
    	        			fascicle.getIdpumaistanza(), fascicle.getProtpumaistanza(), fascicle.getDtbrevimanuist(), fascicle.getDtinizioattesa(),null,
    	        			fascicle.getDtrisposta(), fascicle.getCdrispostaottenuta(), null, fascicle.getCdpumarisposta(), fascicle.getIdprofilopuma(),
    	        			fascicle.getDtbrevimanurisp(), fascicle.getDtfascfine(), fascicle.getCdesitofasc(), fascicle.getSwprescrizione(),fascicle.getNoteprescrizione());*/
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
    		//return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    		ex.getStackTrace();
    		return null;
    	}
    	 	
    }

    
    public ModelEntity getFascicolo(Long id, Country country) {
    	
    	try {    	
    		
    		Country currentCountry = Country.fromName(country.name());

    	    switch(currentCountry){
    	    
    	    	case it:
    	    		return fascicleItalyRepository.findById(id).get();
    	    	case es:
    	    		return fascicleSpainRepository.findById(id).get();
    	    	
    	    	default: 
    	    		return null;    		    	    	
    	    }
    	    
    	}catch(Exception ex) {
    		ex.getStackTrace();
    		return null;
    	}
    }

}
