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
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"com.enel.permitting.repository.primary"},
		entityManagerFactoryRef = "primaryDatabaseEntityManager", 
		transactionManagerRef = "primaryDatabaseTransactionManager")
public class PrimaryDatabaseConfiguration {
	
	@Autowired
	Environment env;

	public PrimaryDatabaseConfiguration() {super();}
	
	@Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean primaryDatabaseEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource());
        em.setPackagesToScan("com.enel.permitting.primarymodel");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPersistenceUnitName("primary");
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        
        System.out.println("#####################"+env.getProperty("spring.primary.hibernate.ddl-auto"));
        System.out.println("#####################"+env.getProperty("spring.primary.database-platform"));
        System.out.println("#####################"+env.getProperty("spring.primary.database"));
        System.out.println("#####################"+env.getProperty("spring.primary.show-sql"));
        System.out.println("#####################"+env.getProperty("spring.primary.hibernate.ddl-auto"));
        
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.primary.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.primary.database-platform"));
        properties.put("hibernate.database", env.getProperty("spring.primary.database"));
        properties.put("hibernate.ddl-auto", env.getProperty("spring.primary.hibernate.ddl-auto"));
        //properties.put("hibernate.proc.param_null_passing", env.getProperty("spring.primary.jpa.properties.hibernate.proc.param_null_passing"));
        //properties.put("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
        properties.put("show-sql", env.getProperty("spring.primary.show-sql"));
        
        //properties.put("hibernate.naming.implicit-strategy", env.getProperty("spring.jpa.hibernate.naming.implicit-strategy"));
        //properties.put("hibernate.naming.physical-strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
        
        
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

    @Primary
    @Bean
    public DataSource primaryDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        System.out.println("#####################"+env.getProperty("spring.primary.datasource.url"));
        System.out.println("#####################"+env.getProperty("spring.primary.datasource.username"));
        System.out.println("#####################"+env.getProperty("spring.primary.datasource.password"));
        
        dataSource.setDriverClassName(env.getProperty("spring.primary.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.primary.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.primary.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.primary.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager primaryDatabaseTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(primaryDatabaseEntityManager().getObject());
        return transactionManager;
    }

	

}
