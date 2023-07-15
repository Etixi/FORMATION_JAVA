
package com.infotel.eshop.ui;

import java.util.Locale;

import java.util.Properties;

import com.infotel.eshop.exception.EShopException;

public class SettingsWindow extends Window{
	
	private Translation translation =  Session.Instance.getAttribute("translation");
	
	public SettingsWindow() {
		super("home.settings");
		
	}

	@Override
	protected void renderBody(NavigationRequest request) throws EShopException {
		renderMenu();
		
	}
	
	private void renderMenu() {
		render();
		render("Choix de la langue");

		render();
		render("1.Français");
		render();
		render("2.Anglais");
		render();
		render("3.Retour");
		String choice = readInputText();

		switch(choice) {
		
		
		case "1" -> {
					Locale locale = new Locale("fr", "FR");
					translation.loadBundle(locale);
					navigate("home");
					}
		
		
		case "2" -> {
				Locale locale = new Locale("en", "US");
				translation.loadBundle(locale);
				navigate("home");
		}
		
		case "3" -> navigate("home");
		}
	}
	
	
	
}
