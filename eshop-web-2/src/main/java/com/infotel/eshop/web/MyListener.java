package com.infotel.eshop.web;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyListener implements ServletContextListener{

		public static Logger log = LogManager.getLogger(MyListener.class);


	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("L'application démarre");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("L'application s'arrête");
	}
}
