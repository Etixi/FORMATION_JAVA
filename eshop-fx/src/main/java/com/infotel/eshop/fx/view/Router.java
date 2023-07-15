package com.infotel.eshop.fx.view;

import com.infotel.eshop.fx.Session;
import com.infotel.eshop.fx.model.User;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public enum Router {
	Instance;
	
	private Stage stage;
	private ViewFactory factory;
	
	public void init(Stage stage) {
		this.stage = stage;
		this.factory = new ViewFactory();
	}
	
	public void navigate(String name) {
		View view = factory.getView(name);
		Parent container = view.build();
		Scene scene = new Scene(container);
	
		stage.setScene(scene);
		
		User user = Session.Instance.getAttribute("user");
		String title = "eShop App";
		if (user != null) {
			title += " - " + user.getFirstName() + " " + user.getLastName();
		}
		stage.setTitle(title);
	}
}
