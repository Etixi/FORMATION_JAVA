package com.infotel.eshop.fx.view;

import com.infotel.eshop.fx.Session;


import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.User;
import com.infotel.eshop.fx.service.UserService;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginView implements View {

private UserService service;
	
	private Label titleLabel;
	private Label usernameLabel;
	private TextField usernameField;
	private Label passwordLabel;
	private PasswordField passwordField;
	private Button signInButton;
	private Label errorLabel;
	private GridPane container;

	@Override
	public void init() {
		service = new UserService();
	}
	
	@Override
	public Parent build() {
		titleLabel = new Label("Authentification");
		titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
		GridPane.setColumnSpan(titleLabel, 2);
		GridPane.setHalignment(titleLabel, HPos.CENTER);
		
		usernameLabel = new Label("Identifiant :");
		usernameField = new TextField();
		
		passwordLabel = new Label("Mot de passe");
		passwordField = new PasswordField();
		
		signInButton = new Button("Se connecter");
		signInButton.setDefaultButton(true);
		
//		signInButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		
		signInButton.setOnAction(e -> login());
		GridPane.setColumnSpan(signInButton, 2);
		GridPane.setHalignment(signInButton, HPos.CENTER);
		
		errorLabel = new Label();
		errorLabel.setStyle("-fx-text-fill: red;");
		GridPane.setColumnSpan(errorLabel, 2);
		
		container = new GridPane();
		container.add(titleLabel, 0, 0);
		container.add(usernameLabel, 0, 1);
		container.add(usernameField, 1, 1);
		container.add(passwordLabel, 0, 2);
		container.add(passwordField, 1, 2);
		container.add(signInButton, 0, 3);
		container.add(errorLabel, 0, 4);
		
		container.setStyle("-fx-padding: 60px;");
		container.setHgap(10);
		container.setVgap(10);
		
		//container.setGridLinesVisible(true);
		return container;
	}
	
	private void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();
	
		signInButton.setDisable(true);
		
		//doLogin(username, password);
		new Thread(() -> doLogin(username, password)).start();
	}

	private void doLogin(String username, String password) {
		try {
			// traitement long exécuté dans un thread dédié
			User user = service.login(username, password);
			Session.Instance.setAttribute("user", user);
			
			// mise à jour FX exéutée dans Java FX Thread
			Platform.runLater(() -> navigate("order"));
		} catch (AppException e) {
			// mise à jour FX exéutée dans Java FX Thread
			Platform.runLater(() -> {
				errorLabel.setText("Échec de l'authentification");
				signInButton.setDisable(false);
			});
		}
	}
	
}
