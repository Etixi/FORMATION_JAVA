package com.infotel.eshop.dto;

public class BasketItemDto {
	private BookLightDto book;
	private int quantity;
	
	@Override
	public String toString() {
		return "BasketItemDto [book=" + book + ", quantity=" + quantity + "]";
	}

	public BookLightDto getBook() {
		return book;
	}

	public void setBook(BookLightDto book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
