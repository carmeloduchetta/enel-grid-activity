package com.enel.permitting.config.country;

import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.enel.permitting.repository.italy",
entityManagerFactoryRef = "italyDatabaseEntityManager", transactionManagerRef = "italyDatabaseTransactionManager")
public class ItalyDatabaseConfiguration {
	
	@Autowired
	Environment env;

	public ItalyDatabaseConfiguration() {super();}
	
	//@Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean italyDatabaseEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(italyDataSource());
        em.setPackagesToScan("com.enel.permitting.model");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPersistenceUnitName("italy");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.jpa.hibernate.ddl-auto"));
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.jpa.database-platform"));
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.jpa.database"));
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.jpa.show-sql"));
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.jpa.hibernate.ddl-auto"));

        
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.italy.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.italy.jpa.database-platform"));
        properties.put("hibernate.database", env.getProperty("spring.italy.jpa.database"));
        properties.put("hibernate.proc.param_null_passing", env.getProperty("spring.italy.jpa.properties.hibernate.proc.param_null_passing"));
        properties.put("hibernate.show_sql", env.getProperty("spring.italy.jpa.properties.hibernate.show_sql"));
        
        //properties.put("hibernate.jdbc.batch_size", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
        //properties.put("hibernate.order_inserts", env.getProperty("spring.jpa.properties.hibernate.order_inserts"));
        //properties.put("hibernate.order_updates", env.getProperty("spring.jpa.properties.hibernate.order_updates"));
        //properties.put("hibernate.jdbc.batch_versioned_data", env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_versioned_data"));
        //properties.put("hibernate.generate_statistics", env.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
        //properties.put("hibernate.id.new_generator_mappings", env.getProperty("spring.jpa.properties.hibernate.id.new_generator_mappings"));
        //properties.put("hhibernate.cache.use_second_level_cache", env.getProperty("spring.jpa.properties.hibernate.cache.use_second_level_cache"));
        //properties.put("hibernate.globally_quoted_identifiers", env.getProperty("spring.jpa.properties.hibernate.globally_quoted_identifiers"));
        //properties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        //properties.put("hibernate.use_sql_comments", env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
        //properties.put("hibernate.type", env.getProperty("spring.jpa.properties.hibernate.type"));
        //properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    //@Primary
    @Bean
    public DataSource italyDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.datasource.url"));
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.datasource.username"));
        System.out.println("ITALY#####################"+env.getProperty("spring.italy.datasource.password"));
        
        dataSource.setDriverClassName(env.getProperty("spring.italy.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.italy.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.italy.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.italy.datasource.password"));


        return dataSource;
    }

    //@Primary
    @Bean
    public PlatformTransactionManager italyDatabaseTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(italyDatabaseEntityManager().getObject());
        return transactionManager;
    }

	

}
