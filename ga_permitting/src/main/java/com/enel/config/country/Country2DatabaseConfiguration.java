package com.enel.config.country;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.enel.repository.country2",
entityManagerFactoryRef = "country2DatabaseEntityManager", transactionManagerRef = "country2DatabaseTransactionManager")
public class Country2DatabaseConfiguration {
	
	@Autowired
	Environment env;
	
    @Bean
    public LocalContainerEntityManagerFactoryBean country2DatabaseEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(country2DataSource());
        em.setPackagesToScan("com.enel.permitting.model");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
        properties.put("hibernate.database", env.getProperty("spring.jpa.database"));
        properties.put("hibernate.proc.param_null_passing", env.getProperty("spring.jpa.properties.hibernate.proc.param_null_passing"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource country2DataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.country2.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.country2.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.country2.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.country2.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager country2DatabaseTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(country2DatabaseEntityManager().getObject());
        return transactionManager;
    }

}
