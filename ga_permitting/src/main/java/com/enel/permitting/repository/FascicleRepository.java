package com.enel.permitting.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enel.permitting.beans.FascicleResult;
import com.enel.permitting.entity.Fascicle;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

@Repository
@Transactional
public interface FascicleRepository extends JpaRepository<Fascicle, Long> {	
    
    Fascicle findFascicoloByCity(String city);
    Page findAll(Pageable pageable);

    @Procedure(name = "salvaFascicoloReale")
    FascicleResult saveFascicle(
    		@Param("an_idfascicolo")       Integer idfascicolo,
    		@Param("an_identeprivate")     Integer identeprivate,
    		@Param("an_iddestinatario")    Integer iddestinatario,
    		@Param("as_cdfascicolo")       String cdfascicolo,
    		@Param("as_dsfascicolo")       String dsfascicolo,
    		@Param("as_cdite")             String cdite,
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
    		@Param("ad_dataBreviManuIst")  Date dataBreviManuIst,

    		@Param("ad_dtinizioattesa")    Date dtinizioattesa,
    		@Param("as_flagavanzamento")   String flagavanzamento,
    		@Param("ad_dtrisposta")        Date dtrisposta,
    		@Param("as_cdrispostaottenuta") String cdrispostaottenuta,
    		@Param("as_condizioni")        String condizioni,
    		@Param("as_cdpumarisposta")    String cdpumarisposta,
    		@Param("an_idprofilopuma")     String idprofilopuma,
    		@Param("ad_dataBreviManuRisp") Date dataBreviManuRisp,

    		@Param("ad_dtfascfine")        Date dtfascfine,
    		@Param("as_cdesitofasc")       String cdesitofasc,
    		@Param("as_swprescrizione")    String swprescrizione,
    		@Param("as_noteprescrizione")  String noteprescrizione
    );
 
}
