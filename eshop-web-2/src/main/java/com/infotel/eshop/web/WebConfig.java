package com.infotel.eshop.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.infotel.eshop.config.ServiceConfig;

@Configuration @Import(ServiceConfig.class)
public class WebConfig {
	
	@Bean
	public DataSource eshopDS() throws NamingException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EShopDS");
		
		return ds;
		
	}

}
