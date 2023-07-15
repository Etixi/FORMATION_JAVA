package com.infotel.eshop.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EShopApp {	

	private final static Logger log = LogManager.getLogger(EShopApp.class);
	
	public static void main(String[] args) {
		
		log.info("L'application démarre");
		
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context-console.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		Session.Instance.setAttribute("context", ctx);
		
		
		Configuration config = new Configuration().configure();
		Session.Instance.setAttribute("config", config);
		
		Translation translation = new Translation();
		translation.loadBundle();
		
		
		Router router = Router.getInstance();
		router.navigate("home");
		
		
		log.info("L'application s'arrête");
	}		 
}



