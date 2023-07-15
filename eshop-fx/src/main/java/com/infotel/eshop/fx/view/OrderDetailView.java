package com.infotel.eshop.fx.view;

import java.time.format.DateTimeFormatter;

import com.google.common.eventbus.Subscribe;
import com.infotel.eshop.fx.event.OrderSelectedEvent;
import com.infotel.eshop.fx.model.Order;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OrderDetailView implements View {

	private DateTimeFormatter dtFmt;
	
	private Label numberLabel;
	private Label dateLabel;

	private View customerView;
	private View itemsView;
	
	private Button packButton;
	private Button shipButton;
	
	private HBox buttonBox;
	private AnchorPane container;

	@Override
	public void init() {
		dtFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		register(this);		
	}

	@Override
	public Parent build() {
		numberLabel = new Label("CDE22-0015");
		numberLabel.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
		AnchorPane.setTopAnchor(numberLabel, 5.0);
		AnchorPane.setLeftAnchor(numberLabel, 5.0);
		
		dateLabel = new Label("10/03/2022");
		dateLabel.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
		AnchorPane.setTopAnchor(dateLabel, 5.0);
		AnchorPane.setRightAnchor(dateLabel, 5.0);

		packButton = new Button("Emballé");
		shipButton = new Button("Envoyé");
		
		buttonBox =new HBox(packButton, shipButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(7.0);
		AnchorPane.setBottomAnchor(buttonBox, 10.0);
		AnchorPane.setLeftAnchor(buttonBox, 5.0);
		AnchorPane.setRightAnchor(buttonBox, 5.0);
		
		customerView = getView("customer");
		itemsView = getView("item-list");

		VBox contentBox = new VBox(
			customerView.build(),
			itemsView.build()
		);
		contentBox.setSpacing(10.0);
		AnchorPane.setTopAnchor(contentBox, 30.0);
		AnchorPane.setLeftAnchor(contentBox, 5.0);
		AnchorPane.setBottomAnchor(contentBox, 45.0);
		AnchorPane.setRightAnchor(contentBox, 5.0);
		
		container = new AnchorPane(
			numberLabel,
			dateLabel,
			contentBox,
			buttonBox
		);
		container.setStyle("-fx-background-color: red;");
		HBox.setHgrow(container, Priority.ALWAYS);
		
		return container;
	}
	
	@Subscribe
	public void onOrderSelected(OrderSelectedEvent event) {
		Order order = event.getOrder();

		numberLabel.setText(order.getNumber());
		dateLabel.setText(order.getCreated().format(dtFmt));
	}
	
}
