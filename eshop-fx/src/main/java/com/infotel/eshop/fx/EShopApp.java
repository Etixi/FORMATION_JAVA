package com.infotel.eshop.fx;

import com.infotel.eshop.fx.view.Router;

import javafx.application.Application;
import javafx.stage.Stage;

public class EShopApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Router.Instance.init(stage);
		Router.Instance.navigate("login");

		stage.show();
	}

}
