package com.infotel.eshop.fx.view;

import java.util.List;

import com.infotel.eshop.fx.event.OrderSelectedEvent;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.service.OrderService;

import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OrderListView implements View {
	private OrderService service;
	
	private ListView<Order> listView;
	private VBox container;
	
	@Override
	public void init() {
		service = new OrderService();
	}
	
	@Override
	public Parent build() {
		listView = new ListView<>();
		listView.setCellFactory(view -> new OrderListCell());
		VBox.setVgrow(listView, Priority.ALWAYS);
		
		container = new VBox(listView);
		container.setStyle("-fx-background-color: blue;");
		container.setPrefWidth(470);
		
		List<Order> orders = service.getOrdersToProcess(); // TODO à modifier
		listView.getItems().addAll(orders);
		listView.getSelectionModel()
				.selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> onOrderSelected(newValue));
		
		return container;
	}
	
	private void onOrderSelected(Order order) {
		send(OrderSelectedEvent.of(order));
	}
	
}
