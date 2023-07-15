package com.infotel.eshop.model;

public class BasketItem {
	private Book book;
	private int quantity;
	
	
	public void incrementQuantity(int value) {
		quantity += value;
	}
	
	
	@Override
	public String toString() {
		return "BasketItem [book=" + book + ", quantity=" + quantity + "]";
	}



	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

