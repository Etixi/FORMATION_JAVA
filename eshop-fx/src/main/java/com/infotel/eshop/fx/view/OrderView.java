package com.infotel.eshop.fx.view;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OrderView implements View {

	private Label titleLabel;
	private View listView;
	private View detailView;
	
	private HBox mainBox;
	private VBox container;
	
	@Override
	public Parent build() {
		titleLabel = new Label("Commandes en cours");
		titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
		
		listView = getView("order-list");
		detailView = getView("order-detail");
		
		mainBox = new HBox(listView.build(), detailView.build());
		mainBox.setStyle("-fx-background-color: green; -fx-padding: 5px;");
		VBox.setVgrow(mainBox, Priority.ALWAYS);
		
		container = new VBox(titleLabel, mainBox);
		container.setPrefSize(1000, 600);
		container.setStyle("-fx-padding: 10px;");
		
		return container;
	}

}
