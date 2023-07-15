package com.infotel.eshop.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotel.eshop.service.CatalogService;

public  class HomeWindow extends Window {

	public HomeWindow () {
		super("home.title");
		

	}
	
//	private String getMessage(String key) {
//		
//		RessourceBundle bundle = Session.Instance.getAttribute("bundle");
//		String message = bundle.getString(key);
//		return message;
//	}
	
	@Override
	protected void renderBody(NavigationRequest request){
		render("1. " + getMessage("home.search"));
		render("2. " + getMessage("home.login"));
		render("3. " + getMessage("home.register"));
		render("4. " + getMessage("home.basket"));
		render("5. " + getMessage("home.orders"));
		render("6. " + getMessage("home.settings"));
		render("9. " + getMessage("home.exit"));
		render("");
		render(getMessage("home.search"));

		String choice = readInputText(); 

		switch(choice) {
		case "1" -> {

			navigate("search");

		}
		case "2" -> {
			navigate("login");

		}
		case "3" -> {
			navigate("register");
		}
		case "4" -> {
			navigate("basket");
		}
		
		case "5" -> {
			if (isAuthentificated()) navigate("orders");
			else navigate("login", "orders");
		}
		
		case "6" -> {
			navigate("settings");
		}
		
		case "9" -> {
			render("home.bye");
			System.exit(0);
		}
		}	

	}
private boolean isAuthentificated() {
	return Session.Instance.getAttribute("customer")!=null;
}
}