package com.infotel.eshop.ui;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.dao.jdbc.AbstractDaoJdbc;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.service.OrderServiceImpl;

public class Configuration {
	
	private final static Logger log = LogManager.getLogger(Configuration.class);
	private Properties props = new Properties();
	
	
	public Configuration configure() {
		loadProperties();
		configureJdbc();
		
		return this;
		
		/*
		 * props.setProperty("driver", "org.h2.Driver"); props.setProperty("url",
		 * "jdbc:h2:tcp://localhost:9092/eshop"); props.setProperty("username",
		 * "scott"); props.setProperty("password", "tiger");
		 */
		
	}
		
		
		private void loadProperties() {
			
		//System.err.println(System.getProperties());
		//String userHome = System.getProperty("user.home");
		//Path configPath = Paths.get(userHome, ".eshop", "config.properties");
		
		String userHome = System.getProperty("user.home");
		String cfgFileName = System.getProperty("eshopConfig", "config.properties");
		Path configPath = Paths.get(userHome, ".eshop", cfgFileName);
		
		//System.err.println(configPath);
		log.info("chargement du fichier de configuration : " + configPath);
		
		try (InputStream fis = new FileInputStream(configPath.toFile());){
			
			props.load(fis);
			
		} catch (IOException e) {
			throw new EShopException("Echec lecture de la configuration", e);
		}

	}

	public String getProperty(String key) {
		return props.getProperty(key);
	}

	
	public String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
	
	private void configureJdbc() {
//		AbstractDaoJdbc.props.setProperty("driver", props.getProperty("jdbc.driver"));
//		AbstractDaoJdbc.props.setProperty("url", props.getProperty("jdbc.url"));
		//AbstractDaoJdbc.props.setProperty("username", props.getProperty("jdbc.username"));
		//AbstractDaoJdbc.props.setProperty("password", props.getProperty("jdbc.password"));
		
	}
}
