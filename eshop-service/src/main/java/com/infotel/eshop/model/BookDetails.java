package com.infotel.eshop.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  @Table(name="book_details")
public class BookDetails {
	
	@Id
	private int id;
    private String overview;
    private String isbn; 
    private String volume;
    private String series;
    private LocalDate release;
    
    
   
	public BookDetails() {
		super();
		
	}

	public BookDetails(int id, String overview, String isbn, LocalDate date) {
		super();
		this.id = id;
		this.overview = overview;
		this.isbn = isbn;
		this.release = date;
	}
	
	
	


	@Override
	public String toString() {
		return "BookDetails [id=" + id + ", overview=" + overview + ", isbn=" + isbn + ", volume=" + volume
				+ ", series=" + series + ", release=" + release + "]";
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOverview() {
		return overview;
	}


	public void setOverview(String overview) {
		this.overview = overview;
	}


	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getRelease() {
		return release;
	}

	public void setRelease(LocalDate release) {
		this.release = release;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	} 
	
	
}
