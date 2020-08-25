package com.enel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enel.model.Glossary;

public interface GlossaryRepository extends JpaRepository <Glossary,Long>{

	List<Glossary> findGlossaryByColumnNameAndCodeAndCountry(String columnname,String code,String country);
	
	Glossary findGlossaryByCodeAndCountry(String columnname,String country);

	List<Glossary> findGlossaryByColumnNameAndCountry(String columnName,String country);
	
	List<Glossary> findDistinctGlossaryByColumnName(String columnName);
}
