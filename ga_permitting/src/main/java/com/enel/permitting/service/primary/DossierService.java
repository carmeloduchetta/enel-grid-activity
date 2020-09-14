package com.enel.permitting.service.primary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.enel.permitting.beans.DossierResponse;
import com.enel.permitting.config.CRUDOperation;
import com.enel.permitting.config.Country;
import com.enel.permitting.config.FilterTypes;
import com.enel.permitting.exception.DossierStatus;
import com.enel.permitting.primarymodel.Dossier;
import com.enel.permitting.primarymodel.Glossary;
import com.enel.permitting.primarymodel.IdDossier;
import com.enel.permitting.repository.primary.DossierRepository;
import com.enel.permitting.repository.primary.GlossaryRepository;
import com.enel.permitting.service.IDossierService;
import com.enel.permitting.util.JSONHelper;



//import com.github.fge.jsonpatch.JsonPatch;
//import com.github.fge.jsonpatch.JsonPatchException;


@Service
public class DossierService implements IDossierService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossierService.class);

    @Autowired
    private DossierRepository dossierRepository;
    
    @Autowired
    private GlossaryRepository glossaryEntityRepository;

    public DossierService() {
    	
    }
    
    public DossierResponse createOrUpdateDossier(Dossier dossier, Country country, CRUDOperation dbOperation) {
	    	 	
    	try {
    		
        	LOGGER.info(JSONHelper.toJSONwithPrettyPrint(dossier));    	    
    	    LOGGER.info("start saving");
    	    dossier.setCountry(country.name().toUpperCase()); 
    	    HashMap<String,String> result = validationCheck(dossier,dbOperation);
    	    
    	    Optional <Dossier> opDossier = getDossier(dossier.getIdFascicolo(),dossier.getCountry());
    	    
    	    switch(dbOperation){
    	    
    	    	case CREATE:
    	    		opDossier.ifPresent(theDossier -> result.put(FilterTypes.IDFASCICOLO.getDescription(), String.valueOf(dossier.getIdFascicolo())));
    	    		break;
    	    		
    	    	case UPDATE:
    	    		if(!opDossier.isPresent())
    	    			result.put(FilterTypes.IDFASCICOLO.getDescription(), String.valueOf(dossier.getIdFascicolo()));
    	    		break;
    	    		
    	    	default: 		
    	    		break;
    	    	
    	    }
    	        	    
    	    if(result.isEmpty()) {
    	    	  
    	    	Long createdIdFascicolo = null;
    	    	Dossier created = null;
    	    	String message = null;
    	    	
    	    	switch(dbOperation){
        	    
	    	    	case CREATE:
	    	    		
	    	    		if(null == dossier.getIdFascicolo()) {   	    	
	        	    		created = dossierRepository.save(dossier);
	        	    		createdIdFascicolo = created.getIdFascicolo();
	        	    	}
	        	    	else {
	        	    		insertDossier(dossier);
	        	    		createdIdFascicolo = dossier.getIdFascicolo();
	        	    	}
	    	    		message = "Dossier created with Success.";
	    	    		break;
	    	    		
	    	    	case UPDATE:
	    	   
	    	    		created = dossierRepository.save(dossier);
	    	    		createdIdFascicolo = created.getIdFascicolo();
	    	    		message = "Dossier updated with Success.";
	    	    		break;
	    	    		
	    	    	case PATCH:
	    	    		
	    	    		//Optional <Dossier> opDossier = getDossier(dossier.getIdFascicolo(),dossier.getCountry());	    	    		
	    	    		JSONHelper.nullAwareBeanCopy(opDossier.get(),dossier);
	    	    		created = dossierRepository.save(opDossier.get());
	    	    		createdIdFascicolo = created.getIdFascicolo();
	    	    		message = "Dossier updated with Success.";
	    	    		break;
	    	    		
	    	    	default: 		
	    	    		break;
    	    	
    	    	}
    	    	
    	    		
    	    	result.put(FilterTypes.IDFASCICOLO.getDescription(), String.valueOf(createdIdFascicolo));
    	    	return getDossierResponse(DossierStatus.STATUS_0.getCode(),message,result,null);
    	    	
    	    }
    	    	    
    	    return getDossierResponse(DossierStatus.STATUS_8.getCode(),"Values: "+result.values()+" not valid for "+result.keySet(),null,null);
    	       	        		
    	} catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		ex.getStackTrace();
    		return getDossierResponse(DossierStatus.STATUS_12.getCode(),null,null,ex.getMessage());
    	}	 
    	
    }
        
    public DossierResponse getDossierResponse(Integer code, String message, Map<String,String> result, String details) {
    	return DossierResponse.builder()
    			.additionalResponseData(result)
    			.code(code)
    			.details(details)
    			.message(message)
    			.build();
    }
	    
    public Optional<Dossier> getDossier(Long idFascicolo, String country) {
     
    	return findById(IdDossier.builder().idFascicolo(idFascicolo).country(country.toUpperCase()).build());
    }
    
    public List<Dossier> getDossierByCountry(Country country){
    	    	
    	LOGGER.info("Country: "+country.name());
    	return dossierRepository.findDossierByCountry(country.name().toUpperCase());
    }

      
    public List<Glossary> getGlossaryByColumnNameAndCountry(FilterTypes filterType,Country country){
    	
    	LOGGER.info("Column Type: "+filterType.getDescription()+" - Country: "+country.name().toUpperCase());
    	return glossaryEntityRepository.findGlossaryByColumnNameAndCountry(filterType.getDescription(), country.name().toUpperCase());
    
    }
    
    public void insertDossier(Dossier dossier) {
    	
    	dossierRepository.insertDossier(dossier.getCountry(), dossier.getIdFascicolo(), dossier.getCancLogica(), dossier.getCdFascicolo(), dossier.getCdStatoFascicolo(), dossier.getCdTipoRichiesta(), 
    			dossier.getCdTipoRisposta(), dossier.getDataIns(), dossier.getDataMod(), dossier.getDsFascicolo(), 
    			dossier.getGgTempoMedio(), dossier.getGgTerminiLegge(), dossier.getIdDestinatario(), dossier.getIdEnte(), dossier.getIdIter(), 
    			dossier.getSwTerminiLegge(), dossier.getUserIns(), dossier.getUserMod());

    }    
    
    public HashMap<String,String> validationCheck(Dossier dossier, CRUDOperation dboperation) {
    	
    	HashMap<String,String> currentResult = new HashMap<String,String>(); 
    	List<Glossary> current = null;
    	 	
    	try {
    		    		
    		if(StringUtils.isNotBlank(dossier.getCdStatoFascicolo())) 
    			currentResult.put(FilterTypes.STATOFASCICOLO.getDescription(), dossier.getCdStatoFascicolo());
    		   		
    		if(StringUtils.isNotBlank(dossier.getCdTipoRichiesta()))
    			currentResult.put(FilterTypes.TIPORICHIESTA.getDescription(), dossier.getCdTipoRichiesta());
    		    			
    		if(StringUtils.isNotBlank(dossier.getCdTipoRisposta()))   		
    			currentResult.put(FilterTypes.TIPORISPOSTA.getDescription(), dossier.getCdTipoRisposta());
    		
    		Iterator<Map.Entry<String, String>> iterator = currentResult.entrySet().iterator();
    	    while (iterator.hasNext()) {
    	        Map.Entry<String, String> entry = iterator.next();
    	        LOGGER.info(entry.getKey() + ":" + entry.getValue());
    	        current = glossaryEntityRepository.findGlossaryByColumnNameAndCodeAndCountry(entry.getKey(),entry.getValue(),dossier.getCountry());
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
    
    public Page<Dossier> getPaginationDossiers(String cdStatoFascicolo, String cdTipoRichiesta, String cdTipoRisposta, Country country, Integer pageNumber, Integer pageSize){
    	
    	LOGGER.info("Country: "+country.name());   	
    	return dossierRepository.findDossierByCdStatoFascicoloLikeAndCdTipoRichiestaLikeAndCdTipoRispostaLikeAndCountry(cdStatoFascicolo== null ? "%" : cdStatoFascicolo, cdTipoRichiesta == null ? "%" : cdTipoRichiesta, cdTipoRisposta== null ? "%" : cdTipoRisposta, 
    			country.name().toUpperCase(), PageRequest.of(pageNumber,pageSize, Sort.by("idFascicolo").descending()));
    
    }

    
    public List<Dossier> getDossiers(String cdStatoFascicolo, String cdTipoRichiesta, String cdTipoRisposta, Country country){
    	
    	LOGGER.info("Country: "+country.name());
    	return dossierRepository.findDossierByCdStatoFascicoloLikeAndCdTipoRichiestaLikeAndCdTipoRispostaLikeAndCountry(cdStatoFascicolo== null ? "%" : cdStatoFascicolo, cdTipoRichiesta == null ? "%" : cdTipoRichiesta, cdTipoRisposta== null ? "%" : cdTipoRisposta, country.name().toUpperCase());
    
    }

	@Override
	public Optional<Dossier> findById(IdDossier id) {
		// TODO Auto-generated method stub
		return dossierRepository.findById(id);
	}
    
    public DossierResponse deleteDossier(Long idFascicolo,Country country) {
    	
    	try {

    		LOGGER.info("start delete");
    		
    		dossierRepository.deleteById(IdDossier.builder().idFascicolo(idFascicolo).country(country.name().toUpperCase()).build());
    		//Long numDossierDeleted=dossierRepository.deleteDossierByIdFascicolo(id);

    		//if(numDossierDeleted>0)    			
    	    return getDossierResponse(DossierStatus.STATUS_0.getCode(),"Dossier deleted with Success.",null,null);

    	}catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		ex.getStackTrace();
    		return getDossierResponse(DossierStatus.STATUS_8.getCode(),"Dossier not found",null,null);
    		//return getDossierResponse(DossierStatus.STATUS_12.getCode(),null,null,ex.getMessage());

    	}	 

    }

    /*public DossierResponse patchDossier(JsonPatch jsonPatch,Dossier dossier, Country country) {

    	try {
    		LOGGER.info(JSONHelper.toJSONwithPrettyPrint(jsonPatch));    	    
    		LOGGER.info("start saving");
    		
    		Dossier dossierToBeUpdated = applyPatchToDossier(jsonPatch,dossier);
    		dossierToBeUpdated.setCountry(country.name().toUpperCase());  
    		HashMap<String,String> result = validationCheck(dossierToBeUpdated,CRUDOperation.UPDATE);

    		if(result.isEmpty()) {

    			Dossier updated = dossierRepository.save(dossierToBeUpdated);
    			result.put(FilterTypes.IDFASCICOLO.getDescription(), String.valueOf(updated.getIdFascicolo()));

    			return getDossierResponse(DossierStatus.STATUS_0.getCode(),"Dossier updated with Success.",result,null);

    		}    	   
    		else //{  	    	

    			//if(result.containsKey(FilterTypes.IDFASCICOLO.getDescription()))
    			//	result.remove(FilterTypes.IDFASCICOLO.getDescription());

    			return getDossierResponse(DossierStatus.STATUS_8.getCode(),"Values: "+result.values()+" not valid for "+result.keySet(),null,null);

    		//}



    	} catch(Exception ex) {
    		LOGGER.info(ex.getLocalizedMessage());
    		LOGGER.info(ex.getMessage());
    		ex.getStackTrace();
    		return getDossierResponse(DossierStatus.STATUS_12.getCode(),null,null,ex.getMessage());
    	}	 

    }
    private Dossier applyPatchToDossier(
    		JsonPatch patch, Dossier targetDossier) throws JsonPatchException, JsonProcessingException {
    	ObjectMapper objectMapper=new ObjectMapper();
    	JsonNode patched = patch.apply(objectMapper.convertValue(targetDossier, JsonNode.class));
    	return objectMapper.treeToValue(patched, Dossier.class);
    }*/


}
