package com.infotel.eshop.dto;

import java.util.ArrayList;
import java.util.List;

public class BasketDto {
	
	
	private CustomerDto customer;
	
	private List<BasketItemDto> items;

	public void addItem(BookFullDto book, int quantity) {
		
		BookLightDto dto = new BookLightDto();
		
		dto.setId(book.getId());
		
		dto.setTitle(book.getTitle());
		
		dto.setPrice(book.getPrice());
		
		dto.setImageId(book.getImageId());
		
		addItem(dto, quantity);
	}
	
	public void addItem(BookLightDto book, int quantity) {
		BasketItemDto item = new BasketItemDto();
		item.setBook(book);
		item.setQuantity(quantity);
		
		if (this.getItems() == null) {
			this.setItems(new ArrayList<>());	
		}
		this.getItems().add(item);
	}
	
	public void clear() {
		if (items != null) items.clear();
	}
	
	// TODO règle de gestion dans DTO !
	public double getTotalAmount() {
		double amount = 0;
		
		for (BasketItemDto item : items) {
			amount += item.getQuantity() * item.getBook().getPrice();
		}
		
		return amount;
	}

	
	@Override
	public String toString() {
		return "BasketDto [customer=" + customer + ", items=" + items + "]";
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public List<BasketItemDto> getItems() {
		return items;
	}

	public void setItems(List<BasketItemDto> items) {
		this.items = items;
	}
	
}
