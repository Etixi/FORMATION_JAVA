package com.infotel.eshop.dto;

public class OrderItemDto {
	private int id;
	private int quantity;
	private double unitPrice;
	
	private BookLightDto book;

	@Override
	public String toString() {
		return "OrderItemDto [id=" + id + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", book=" + book
				+ "]";
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BookLightDto getBook() {
		return book;
	}

	public void setBook(BookLightDto book) {
		this.book = book;
	}
	
}
