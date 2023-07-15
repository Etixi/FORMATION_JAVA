package com.infotel.eshop.fx.view;

import java.time.format.DateTimeFormatter;

import com.infotel.eshop.fx.model.Order;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class OrderListCell extends ListCell<Order> {

	private static DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	private Label numberLabel;
	private Label dateLabel;
	private Label contentLabel;
	private Label statusLabel;
	
	private AnchorPane container;
		
	public OrderListCell() {
		super();
		init();
	}

	private void init() {
		numberLabel = new Label();
		numberLabel.setStyle("-fx-text-fill: #0077B6; -fx-font-weight: bold;");
		AnchorPane.setTopAnchor(numberLabel, 5.0);
		AnchorPane.setLeftAnchor(numberLabel, 5.0);
		
		dateLabel = new Label();
		dateLabel.setStyle("-fx-text-fill: #0077B6; -fx-font-weight: bold;");
		AnchorPane.setTopAnchor(dateLabel, 5.0);
		AnchorPane.setRightAnchor(dateLabel, 5.0);

		contentLabel = new Label();
		contentLabel.setStyle("-fx-text-fill: #6C757D;");
		AnchorPane.setTopAnchor(contentLabel, 35.0);
		AnchorPane.setLeftAnchor(contentLabel, 10.0);
		AnchorPane.setBottomAnchor(contentLabel, 10.0);
		
		statusLabel = new Label();
		statusLabel.setStyle("-fx-text-fill: #EE9B00;");
		AnchorPane.setTopAnchor(statusLabel, 35.0);
		AnchorPane.setLeftAnchor(statusLabel, 350.0);
		
		container = new AnchorPane(
			numberLabel,
			dateLabel,
			contentLabel,
			statusLabel
		);
	}
	
	@Override
	protected void updateItem(Order item, boolean empty) {
		super.updateItem(item, empty);
		
		if (empty) {
			setGraphic(null);
		} else {
			setGraphic(container);
			
			numberLabel.setText(item.getNumber());
			dateLabel.setText(item.getCreated().format(dtFmt));
			
			if( item.getCustomer() != null && item.getItems() != null) {
			String content = item.getCustomer().getName() + " - " + item.getItems().size() + " article";
			
			if (item.getItems().size() > 1) content += "s";
			contentLabel.setText(content);
			}
			
			statusLabel.setText(statusLabelValue(item));
		}
	}
	
	private String statusLabelValue(Order order) {
		switch (order.getStatus()) {
		case "Allocated":
			return "En cours";

		case "Packed":
			return "Emballé";

		default:
			return "<inconnu>";
		}
	}
}
