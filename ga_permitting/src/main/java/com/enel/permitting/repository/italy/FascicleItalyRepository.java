package com.enel.permitting.repository.italy;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.enel.permitting.model.Fascicle;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

public interface FascicleItalyRepository extends JpaRepository<Fascicle, Long> {	
    
    Fascicle findFascicoloByCditer(String cditer);
    Page findAll(Pageable pageable);
    
    @Procedure(name = "startSession")
    void startSession(
    		@Param("as_user") String user,
    		@Param("as_application") String application,
    		@Param("as_locale") String local    		
    );

    @Procedure(name = "endSession")
    void endSession();

    @Procedure(name = "salvaFascicoloReale")
    HashMap<String,Object> saveFascicle(
    		@Param("an_idfascicolo")       Long idfascicolo,
    		@Param("an_idente")            Long idente,
    		@Param("an_iddestinatario")    Long iddestinatario,
    		
    		@Param("as_cdfascicolo")       String cdfascicolo,
    		@Param("as_dsfascicolo")       String dsfascicolo,
    		@Param("as_cditer")            String cditer,
    		@Param("as_utente")            String utente,
    		
    		@Param("an_idinddestinat")     Integer idinddestinat,
    		@Param("an_idunitaresp")       Integer idunitaresp,
    		
    		@Param("as_cdtiporichiesta")   String cdtiporichiesta,
    		@Param("as_cdtiporisposta")    String cdtiporisposta,
    		
    		@Param("an_ggterminilegge")    Integer ggterminilegge,
    		
    		@Param("as_swterminilegge")    String swterminilegge,
    		
    		@Param("an_ggtempomedio")      Integer ggtempomedio,
    		
    		@Param("as_cdstatoiteriniz")   String cdstatoiteriniz,
    		@Param("as_cdstatoiterfine")   String cdstatoiterfine,

    		@Param("ad_dtfirma")           Date dtfirma,
    		@Param("ad_dtspedizione")      Date dtspedizione,
    		@Param("ad_dtricevutaritorno") Date dtricevutaritorno,
    			
    		@Param("as_forzastato")        String forzastato,
    		@Param("as_idpumaistanza")     String idpumaistanza,
    		@Param("as_protpumaistanza")   String protpumaistanza,
    		
    		@Param("ad_databrevimanuist")  Date dataBreviManuIst,
    		@Param("ad_dtinizioattesa")    Date dtinizioattesa,
    		
    		@Param("as_flagavanzamento")   String flagavanzamento,
    		
    		@Param("ad_dtrisposta")        Date dtrisposta,
    		
    		@Param("as_cdrispostaottenuta") String cdrispostaottenuta,
    		@Param("as_condizioni")        String condizioni,
    		@Param("as_cdpumarisposta")    String cdpumarisposta,
    		@Param("an_idprofilopuma")     String idprofilopuma,
    		
    		@Param("ad_databrevimanurisp") Date dataBreviManuRisp,
    		@Param("ad_dtfascfine")        Date dtfascfine,
    		
    		@Param("as_cdesitofasc")       String cdesitofasc,
    		@Param("as_swprescrizione")    String swprescrizione,
    		@Param("as_noteprescrizione")  String noteprescrizione
    );
    
}
