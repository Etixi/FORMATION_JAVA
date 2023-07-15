package com.infotel.eshop.fx.event;

import com.infotel.eshop.fx.model.Order;

public class SelectedOrderEvent {
	
	private Order order;
	
	public static SelectedOrderEvent of(Order order) {
		SelectedOrderEvent event = new SelectedOrderEvent();
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
