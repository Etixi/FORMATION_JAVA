package com.infotel.eshop.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Book {
	
	@Id
	private int id;
	private String title;
	//private double price;//
	
	@Embedded
	private BookPrice price;
	
	@ManyToOne @JoinColumn(name="category_id")
	private Category category;
	
	@OneToOne @JoinColumn(name="details_id")
	private BookDetails details;
	
	@ManyToMany
	@JoinTable(name="book_author",
	joinColumns = @JoinColumn(name="book_id"),
	inverseJoinColumns = @JoinColumn(name="author_id"))
	private List<Author> authors;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(
	 name = "book_tag",
	 joinColumns = @JoinColumn(name="book_id")
	 )
	@Column(name="tag")
	private List<String> tags;
	
	public Book() {
		// super();
	}
	

	
	public Book(int id, String title, double price) {
		//super();
		this.id = id;
		this.title = title;
		
		BookPrice priceObj  = new BookPrice();
		
		priceObj.setValue(price);
		priceObj.setUpdated(LocalDateTime.now());
		this.price = priceObj;
	}



	public List<String> getTags() {
		return tags;
	}



	public void setTags(List<String> tags) {
		this.tags = tags;
	}



	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category + ", details="
				+ details + ", authors=" + authors + ", tags=" + tags + "]";
	}



	public List<Author> getAuthors() {
		return authors;
	}



	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}



	
	
	public Book(int id, String title, double price, Category category) {
		this(id, title, price); 
		this.category = category;
	}
	
	
	public void setPrice(BookPrice price) {
        
        this.price = price;
        
    }

    public BookPrice getPrice() {
        return this.price;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void  setCategory(Category category) {
		this.category = category;
	}

	public BookDetails getDetails() {
		return details;
	}

	public void setDetails(BookDetails details) {
		this.details = details;
	}
	
	
}