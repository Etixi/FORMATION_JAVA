package com.infotel.eshop.dto;

public class ImageDataDto {
	private int id;
	private byte[] content;
	
	@Override
	public String toString() {
		return "ImageDataDto [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	
}
