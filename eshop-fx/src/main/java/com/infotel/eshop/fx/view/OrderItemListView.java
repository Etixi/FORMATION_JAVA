package com.infotel.eshop.fx.view;

import com.google.common.eventbus.Subscribe;
import com.infotel.eshop.fx.event.OrderSelectedEvent;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderItem;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class OrderItemListView implements View {

	private Label titleLabel;
	private ListView<OrderItem> listView;
	private VBox container;
	
	@Override
	public void init() {
		register(this);
	}
	
	@Override
	public Parent build() {
		titleLabel = new Label("Articles");
		titleLabel.setStyle("-fx-font-weight: bold;");
		listView = new ListView<>();
		listView.setCellFactory(v -> new OrderItemListCell());
		
		container = new VBox(titleLabel, listView);
		container.setStyle("-fx-background-color: yellow;");
		
		return container;
	}
	
	@Subscribe
	public void onOrderSelected(OrderSelectedEvent event) {
		Order order = event.getOrder();
		
		listView.getItems().clear();
		listView.getItems().addAll(order.getItems());
	}

}
