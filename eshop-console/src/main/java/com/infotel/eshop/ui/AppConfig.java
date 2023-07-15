package com.infotel.eshop.ui;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.infotel.eshop.config.ServiceConfig;

@Configuration
@Import(ServiceConfig.class)
@PropertySource("file:${user.home}/.eshop/config.properties")
public class AppConfig {

	@Bean
	@Autowired
	public DataSource eshopDS(@Value("${jdbc.driver}") String driver,
			@Value("${jdbc.url}") String url,
			@Value("${jdbc.username}") String username,
			@Value("${jdbc.password}") String password) {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
		
		
		return ds;
	}
}