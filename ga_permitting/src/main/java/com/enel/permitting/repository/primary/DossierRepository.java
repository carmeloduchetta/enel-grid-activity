package com.enel.permitting.repository.primary;


import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.enel.permitting.primarymodel.Dossier;
import com.enel.permitting.primarymodel.IdDossier;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

public interface DossierRepository extends JpaRepository<Dossier, IdDossier> {	

	List<Dossier> findDossierByCountry(String country);
	
	List<Dossier> findDossierByCdStatoFascicoloAndCountry(String cdStatoFascicolo,String country);
	List<Dossier> findDossierByCdTipoRichiestaAndCountry(String cdTipoRichiesta,String country);	
	List<Dossier> findDossierByCdTipoRispostaAndCountry(String cdTipoRisposta,String country);
		
	List<Dossier> findDossierByCdStatoFascicoloAndCdTipoRichiestaAndCountry(String cdStatoFascicolo, String cdTipoRichiesta, String country);
	List<Dossier> findDossierByCdStatoFascicoloAndCdTipoRispostaAndCountry(String cdStatoFascicolo, String cdTipoRisposta, String country);

	List<Dossier> findDossierByCdTipoRispostaAndCdTipoRichiestaAndCountry(String cdTipoRisposta, String cdTipoRichiesta, String country);		
	List<Dossier> findDossierByCdStatoFascicoloAndCdTipoRichiestaAndCdTipoRispostaAndCountry(String cdStatoFascicolo,String cdTipoRichiesta, String cdTipoRisposta,String country);	
	
	List<Dossier> findDossierByCdStatoFascicoloLikeAndCdTipoRichiestaLikeAndCdTipoRispostaLikeAndCountry(String cdStatoFascicolo,String cdTipoRichiesta, String cdTipoRisposta,String country);
	Dossier findDossierByIdFascicolo(Long id);

	@Transactional
	@Modifying
	@Query(
	  value = 
	  	"INSERT INTO ga_dossier.dossier"+
	  	"(country, id_fascicolo, canc_logica, cd_fascicolo, cd_stato_fascicolo, cd_tipo_richiesta, cd_tipo_risposta, data_ins, data_mod, ds_fascicolo, gg_tempo_medio, gg_termini_legge, id_destinatario, id_ente, id_iter, sw_termini_legge, user_ins, user_mod)"+
	  	"VALUES( :country, :id_fascicolo, :canc_logica, :cd_fascicolo, :cd_stato_fascicolo, :cd_tipo_richiesta, :cd_tipo_risposta, :data_ins, :data_mod, :ds_fascicolo, :gg_tempo_medio, :gg_termini_legge, :id_destinatario, :id_ente, :id_iter, :sw_termini_legge, :user_ins, :user_mod)",
	  nativeQuery = true)
	void insertDossier(@Param("country") String country, @Param("id_fascicolo") Long idFascicolo, @Param("canc_logica") String cancLogica, @Param("cd_fascicolo") String cdFascicolo, @Param("cd_stato_fascicolo") String cdStatoFascicolo, @Param("cd_tipo_richiesta") String cdTipoRichiesta, 
			@Param("cd_tipo_risposta") String cdTipoRisposta, @Param("data_ins") Date dataIns, @Param("data_mod") Date dataMod, @Param("ds_fascicolo") String dsFascicolo, 
			@Param("gg_tempo_medio") Integer ggTempoMedio, @Param("gg_termini_legge") Integer ggTerminiLegge, @Param("id_destinatario") Long idDestinatario, @Param("id_ente") Long idEnte, @Param("id_iter") Long idIter, 
			@Param("sw_termini_legge") String swTerminiLegge, @Param("user_ins") String userIns, @Param("user_mod") String userMod);

    @Transactional
	Long deleteDossierByIdFascicolo(Long id); 
    
    Page<Dossier> findDossierByCdStatoFascicoloLikeAndCdTipoRichiestaLikeAndCdTipoRispostaLikeAndCountry(String cdStatoFascicolo,String cdTipoRichiesta, String cdTipoRisposta,String country, Pageable pageable);

}
