package com.enel.service;
import java.util.Optional;

import com.enel.model.ModelEntity;
import com.enel.model.id.IdModelEntity;

public interface IModelEntityService {
	Optional<ModelEntity> findById(IdModelEntity id);
}
