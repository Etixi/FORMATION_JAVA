package com.infotel.eshop.fx.view;

import com.google.common.eventbus.Subscribe;
import com.infotel.eshop.fx.event.OrderSelectedEvent;
import com.infotel.eshop.fx.model.Address;
import com.infotel.eshop.fx.model.Customer;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CustomerView implements View {
	
	private Label titleLabel;
	private Label customerLabel;
	private Label addressLabel;
	
	private AnchorPane container;
	
	@Override
	public void init() {
		register(this);
	}
	
	@Override
	public Parent build() {
		titleLabel = new Label("Client");
		titleLabel.setStyle("-fx-font-weight: bold;");
		customerLabel = new Label("Mme Amélie Poulain");
		addressLabel = new Label("18 rue Montorgueil, 75018 Paris");
		
		AnchorPane.setTopAnchor(customerLabel, 30.0);
		AnchorPane.setTopAnchor(addressLabel, 55.0);
		
		container = new AnchorPane(
			titleLabel,
			customerLabel,
			addressLabel
		);
		container.setStyle("-fx-background-color: teal;");
		
		return container;
	}

	@Subscribe
	public void onOrderSelected(OrderSelectedEvent event) {
		Customer cust = event.getOrder().getCustomer();
		Address addr = cust.getAddress();
		
		customerLabel.setText(cust.getName());
		
		if (addr != null) {
			addressLabel.setText(addr.getStreet() + ", " + addr.getZip() + " " + addr.getCity());			
		} else {
			addressLabel.setText("<addresse manquante>");
		}
	}

	
}
