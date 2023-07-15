package com.infotel.eshop.dto;

public class BookLightDto {
	private int id;
	private String title;
	private double price;
	private int imageId;
	
	public static BookLightDto of(int id, String title, double price, int imageId) {
		BookLightDto book = new BookLightDto();
		book.id = id;
		book.title = title;
		book.price = price;
		book.imageId = imageId;
		
		return book;
	}
	
	@Override
	public String toString() {
		return "BookDto [id=" + id + ", title=" + title + ", price=" + price + ", imageId=" + imageId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
}
