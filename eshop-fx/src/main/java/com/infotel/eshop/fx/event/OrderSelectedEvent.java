package com.infotel.eshop.fx.event;

import com.infotel.eshop.fx.model.Order;

public class OrderSelectedEvent {
	private Order order;
	
	public static OrderSelectedEvent of(Order order) {
		OrderSelectedEvent event = new OrderSelectedEvent();
		event.order = order;
		return event;
	}
		
	@Override
	public String toString() {
		return "SelectedOrderEvent [order=" + order + "]";
	}

	public Order getOrder() {
		return order;
	}
}
