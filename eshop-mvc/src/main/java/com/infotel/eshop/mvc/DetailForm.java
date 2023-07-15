package com.infotel.eshop.mvc;

public class DetailForm {
	
	private int quantity;
	private int bookId;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	@Override
	public String toString() {
		return "DetailForm [quantity=" + quantity + ", bookId=" + bookId + "]";
	}
	

}
