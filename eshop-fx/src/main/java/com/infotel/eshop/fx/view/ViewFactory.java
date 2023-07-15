package com.infotel.eshop.fx.view;

public class ViewFactory {

	public View getView(String name) {
		View view = switch (name) {
		case "login" -> new LoginView();
		case "order" -> new OrderView();
		case "order-list" -> new OrderListView();
		case "order-detail" -> new OrderDetailView();
		case "customer" -> new CustomerView();
		case "item-list" -> new OrderItemListView();
		default ->
			throw new IllegalArgumentException("Unexpected value: " + name);
		};
		
		view.init();
		
		return view;
	}
}
