package com.infotel.eshop.dto;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.infotel.eshop.jaxb.LocalDateAdapter;

@XmlRootElement(name="Book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookFullDto {
	
	@XmlAttribute
	private int id;
	@XmlElement(name="Title")
	private String title;
	@XmlElement(name="Isbn")
	private String isbn;
	@XmlElement(name="Overview")
	private String overview;
	
	@XmlElement(name="Release")
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	//@JsonSerialize(using=LocalDateSerializer.class)
	//@JsonDeserialize(using=LocalDateDeserializer.class)
	private LocalDate release;
	
	@XmlElement(name="Price")
	private double price;
	
	@XmlElement(name="ImageId")
	private Integer imageId;
	
	@XmlElement(name="Category")
	private CategoryDto category;
	
	@XmlElement(name="Author")
	@XmlElementWrapper(name="Authors")
	private List<AuthorDto> authors;
	
	
	@XmlElement(name="Tag")
	@XmlElementWrapper(name="Tags")
	private List<String> tags;
	
	@Override
	public String toString() {
		return "BookFullDto [id=" + id + ", title=" + title + ", isbn=" + isbn + ", overview=" + overview + ", release="
				+ release + ", price=" + price + ", imageId=" + imageId + ", category=" + category + ", authors="
				+ authors + ", tags=" + tags + "]";
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public LocalDate getRelease() {
		return release;
	}

	public void setRelease(LocalDate release) {
		this.release = release;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	

}
