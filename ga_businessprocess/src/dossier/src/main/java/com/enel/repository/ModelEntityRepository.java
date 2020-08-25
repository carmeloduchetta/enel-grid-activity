package com.enel.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.enel.model.ModelEntity;
import com.enel.model.id.IdModelEntity;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */

public interface ModelEntityRepository extends JpaRepository<ModelEntity, IdModelEntity> {	
	
	List<ModelEntity> findModelEntityByCountry(String country);
	
	List<ModelEntity> findModelEntityByColumn_1AndCountry(Long column_1,String country);
	List<ModelEntity> findModelEntityByColumn_2AndCountry(String column_2,String country);
	List<ModelEntity> findModelEntityByColumn_3AndCountry(Date column_3,String country);
	List<ModelEntity> findModelEntityByColumn_4AndCountry(Integer column_4,String country);

	ModelEntity findModelEntityByIdModelEntity(Long idModelEntity);

	@Transactional
	@Modifying
	@Query(
	  value = 
	  	"INSERT INTO DB_SCHEMA.DB_TABLE"+
	  	"(country, id_model_entity, column_1, column_2, column_3, column_4)"+
	  	"VALUES( :country, :id_model_entity, :column_1, :column_2, :column_3, :column_4)",
	  nativeQuery = true)
	void insertModelEntity(@Param("country") String country, @Param("id_model_entity") Long idModelEntity, @Param("column_1") Long column_1, @Param("column_2") String column_2, @Param("column_3") Date column_3, @Param("column_4") Integer column_4);

    @Transactional
	Long deleteModelEntityByIdModelEntity(Long idModelEntity);

}

