package com.infotel.eshop.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryListDto {

	@XmlElement(name="Category")
	private List<CategoryDto> categories;

	@Override
	public String toString() {
		return "CategoryListDto [categories=" + categories + "]";
	}

	public List<CategoryDto> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

	
}
