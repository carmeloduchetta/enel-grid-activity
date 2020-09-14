package com.enel.permitting.service;
import java.util.Optional;

import com.enel.permitting.primarymodel.Dossier;
import com.enel.permitting.primarymodel.IdDossier;


public interface IDossierService {
	Optional<Dossier> findById(IdDossier id);
}
