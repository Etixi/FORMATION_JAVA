package com.infotel.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="order_item")
public class OrderItem {
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int quantity;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@ManyToOne @JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne @JoinColumn(name="order_id")
	private Order order;




	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ",  book=" + book + "]";
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

	public  void setQuantity(int quantity) {//static
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Book getBook() {
		return book;
	}

	public  void setBook(Book book) {  
		this.book = book;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
