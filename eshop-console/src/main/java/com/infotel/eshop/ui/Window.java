

package com.infotel.eshop.ui;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Book;

public abstract class Window {
	
	private final static Logger log = LogManager.getLogger(Window.class);
	
	
	protected String title;
		
	public Window (String title) {
		super();
		this.title = title;
	}
	
	protected void renderTitle()
	{
		render("===========================");
		render ("----  " + getMessage(title) + "---------");
		render("============================");
		render();
		
	}
	
	
	protected abstract void renderBody(NavigationRequest request) throws EShopException ;
	
	
	
	public void show(NavigationRequest request)  {
		renderTitle();
		render();
		try {
			renderBody(request);
		} catch (EShopException e) {
			render();
			log.error("Problme Système", e);
			render("Problème système");
		}
	}
	protected void render() {
		System.out.println();	
	}

	protected void render(String text) {
		System.out.println(text);
	}


	protected String readInputText() {
		Scanner scanner = new Scanner(System.in);
		String keyword = scanner.nextLine();
		return keyword;
	}
	protected int readInputInt() {
		return Integer.parseInt(readInputText());
	}

	
	//static Router router = new Router();
	protected void navigate(String target, Object... params)  {
		Router router = Router.getInstance();
		router.navigate(target, params);
	}

	protected String getMessage(String key, Object... params) {
		try {
		ResourceBundle bundle = Session.Instance.getAttribute("bundle");
		String message = bundle.getString(key);
		
		if(params != null && params.length > 0) {
			message = MessageFormat.format(message,  params);
		}
		
		return message;
		
		}catch(MissingResourceException e) {
			log.warn("Clé de traduction manquante : " + key);
			return "???" + key + "???";
		}
	}
}
