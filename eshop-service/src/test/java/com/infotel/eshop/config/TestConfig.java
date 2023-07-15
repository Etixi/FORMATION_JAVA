package com.infotel.eshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration @Import(ServiceConfig.class)
public class TestConfig {
	
	@Bean
	public DataSource eshopDS() {
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost:9092/eshop");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		
		return ds;
		
	}

}
