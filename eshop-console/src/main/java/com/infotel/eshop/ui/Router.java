package com.infotel.eshop.ui;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;

public class Router {
	
	private WindowFactory factory = new WindowFactory();
	
	private static Router instance;
	
	private Router() {
		super();
			
	}


	public static Router getInstance() {
		if(instance  == null) {
			instance = new Router();
		}
		System.err.println(instance);
		return instance;
	}


	public static void setInstance(Router instance) {
		Router.instance = instance;
	}


	public void navigate(String target, Object ... params) {
		Window window = factory.getWindow(target);
		
		NavigationRequest request = NavigationRequest.of(target)
													.withParams(params);
		window.show(request);
		
		
	}
}
