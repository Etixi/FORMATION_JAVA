package com.infotel.eshop.fx.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.infotel.eshop.fx.model.OrderItem;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class OrderItemListCell extends ListCell<OrderItem> {

	private NumberFormat priceFmt;
	
	private Label nameLabel;
	private Label quantityLabel;
	private Label priceLabel;
	
	private AnchorPane container;
	
	public OrderItemListCell() {
		super();
		init();
	}
	
	private void init() {
		priceFmt = new DecimalFormat("0.00");
		
		nameLabel = new Label();
		AnchorPane.setTopAnchor(nameLabel, 5.0);
		AnchorPane.setLeftAnchor(nameLabel, 5.0);
		AnchorPane.setBottomAnchor(nameLabel, 5.0);
		
		quantityLabel = new Label();
		AnchorPane.setTopAnchor(quantityLabel, 5.0);
		AnchorPane.setLeftAnchor(quantityLabel, 230.0);

		priceLabel = new Label();
		AnchorPane.setTopAnchor(priceLabel, 5.0);
		AnchorPane.setRightAnchor(priceLabel, 5.0);
		
		container = new AnchorPane(
			nameLabel,
			quantityLabel,
			priceLabel
		);
	}
	
	@Override
	protected void updateItem(OrderItem item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setGraphic(null);
		} else {
			setGraphic(container);
			
			nameLabel.setText(item.getBook().getName());
			quantityLabel.setText(Integer.toString(item.getQuantity()));
			priceLabel.setText(priceFmt.format(item.getPrice()) + " €");
		}
	}
	
	
}
