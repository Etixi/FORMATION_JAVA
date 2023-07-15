
package com.infotel.eshop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {

	private List<BasketItem> items;

	public void addItem(Book book, int quantity) {
		BasketItem item = getItem(book);

		if(item == null) {
			item = new BasketItem();
			item.setBook(book);
			item.setQuantity(quantity);

			if (items == null) {
				items = new ArrayList<>();
			}
			items.add(item);
		}
		else {
			item.incrementQuantity(quantity);
		}
	}
	
	
//	public void clear() {
//		
//	}
	private BasketItem getItem(Book book){
		if (items == null) return null;
		for(BasketItem item :items) {
			if(item.getBook().getId() == book.getId()) {
				return item;
			}
		}
		return null;
	}

	
	public double getTotalAmount() {
		double amount = 0;
		for (BasketItem item : this.getItems()) {
			amount += item.getQuantity() + item.getBook().getPrice().getValue();
		}
		return amount;
	}

	@Override
	public String toString() {
		return "Basket [items=" + items + "]";
	}

	public List<BasketItem> getItems() {
		return items;
	}

	public void setItems(List<BasketItem> items) {
		this.items = items;
	}
}
