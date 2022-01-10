package com.val.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.val.model.employee.Employee;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackages = "com.val.repository.employee",
	        entityManagerFactoryRef = "employeeEntityManagerFactory",
	        transactionManagerRef= "employeeTransactionManager")
	public class EmployeeDataSourceConfiguration {

	    @Bean
	    @Primary
	    @ConfigurationProperties("spring.datasource.employee")
	    public DataSourceProperties employeeDatasourceProperties() {
	        return new DataSourceProperties();
	    }

	
	    @Bean
	    @Primary
	    @ConfigurationProperties("spring.datasource.employee.configuration")
	    public DataSource employeeDataSource() {
	        return employeeDatasourceProperties().initializeDataSourceBuilder()
	                .type(HikariDataSource.class).build();
	    }

	
	    @Primary
	    @Bean(name = "employeeEntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(EntityManagerFactoryBuilder builder) {
	        return builder
	                .dataSource(employeeDataSource())
	                // for specifying package .packages("com.techgeeknext.entities.employee.type")
	                .packages(Employee.class)
	                .build();
	    }

	    @Primary
	    @Bean
	    public PlatformTransactionManager employeeTransactionManager(
	            final @Qualifier("employeeEntityManagerFactory") LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory) {
	        return new JpaTransactionManager(employeeEntityManagerFactory.getObject());
	    }
	}
