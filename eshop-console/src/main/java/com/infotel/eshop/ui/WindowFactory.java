package com.infotel.eshop.ui;

import java.util.HashMap;
import java.util.Map;

public class WindowFactory {
	private Map<String, Window> cache = new HashMap<>();


	public Window getWindow(String name) {
		Window window = cache.get(name);
		if(window != null) return window;

		window = switch(name) {
		case "home" -> new HomeWindow();
		case "login" -> new LoginWindow();
		case "search" -> new SearchWindow();
		case "detail" -> new DetailWindow();
		case "basket" -> new BasketWindow();
		case "register" -> new RegisterWindow();
		case "orders" -> new OrderWindow();
		case "settings" -> new SettingsWindow();
		default -> null;

		};
		cache.put(name,  window);
		return window;
	}
}
