package com.infotel.eshop.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration @ComponentScan("com.infotel.eshop")
@EnableTransactionManagement
public class ServiceConfig {

	private JpaVendorAdapter jpaVendor() {
		return new HibernateJpaVendorAdapter();
		
	}
	
	
	
	@Bean @Autowired
	public LocalContainerEntityManagerFactoryBean  emf (DataSource datasource) {
		 
		LocalContainerEntityManagerFactoryBean  emf = new  LocalContainerEntityManagerFactoryBean();
		 emf.setDataSource(datasource);
		 emf.setPackagesToScan("com.infotel.eshop.model");
		 
		 
		 //Properties props = JpaProperties();
		 
		 emf.setJpaProperties(JpaProperties());
		 emf.setJpaVendorAdapter(jpaVendor());
	
		 return emf; 
		 
		 
	}



	private Properties JpaProperties() {
		Properties props = new Properties();
		 props.setProperty("hibernate.show_sql", "true");
		 props.setProperty("hibernate.format_sql", "true");
		return props;
	}
	
	@Bean @Autowired
	public PlatformTransactionManager txManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		//JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		//txManager.s
		
		return txManager;
	}
	

}
