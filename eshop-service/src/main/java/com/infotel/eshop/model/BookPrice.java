package com.infotel.eshop.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BookPrice {
	
	@Column(name="price_value")
	private double value;
	
	@Column(name="price_updated")
	private LocalDateTime updated;
	
	
	
	@Override
	public String toString() {
		return "BookPrice [value=" + value + ", updated=" + updated + "]";
	}
	
	
	public double getValue() {
		return value;
	}
	
	
	public void setValue(double value) {
		this.value = value;
	}


	public LocalDateTime getUpdated() {
		return updated;
	}


	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}	
}
