package com.enel.permitting.repository.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enel.permitting.primarymodel.Glossary;

@Repository
@Transactional
public interface GlossaryRepository extends JpaRepository <Glossary,Long>{

	List<Glossary> findGlossaryByColumnNameAndCodeAndCountry(String columnname,String code,String country);
	
	Glossary findGlossaryByCodeAndCountry(String columnname,String country);

	List<Glossary> findGlossaryByColumnNameAndCountry(String columnName,String country);
	
	List<Glossary> findDistinctGlossaryByColumnName(String columnName);
}
