package com.infotel.eshop.fx.model;

public class OrderItem {
	private int id;
	private int quantity;
	private double price;
	
	private Book book;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", price=" + price + ", book=" + book + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
