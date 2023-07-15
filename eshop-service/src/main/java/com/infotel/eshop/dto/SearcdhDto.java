package com.infotel.eshop.dto;

public class SearcdhDto {
	
	private String keyword;
	private int categoryId;
	
	
	
	@Override
	public String toString() {
		return "SearcdhDto [keyword=" + keyword + ", categoryId=" + categoryId + "]";
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	
	

}
