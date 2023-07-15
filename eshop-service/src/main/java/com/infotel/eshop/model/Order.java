package com.infotel.eshop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="purchase_order")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="order_number")
	private String number;
	
	private LocalDateTime created;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy="order")
	private List<OrderItem> items;
	
	public void addItem(Book book, int quantity) {
		OrderItem item = new OrderItem();
		item.setBook(book);
		item.setQuantity(quantity);
		item.setUnitPrice(book.getPrice().getValue());
		
		addItem(item);
	}
	
	public void addItem(OrderItem item) {
		if(items == null) {
			items =  new ArrayList<>();
		}
		items.add(item);
		item.setOrder(this);
	}
	
	public double getTotalAmount() {
		double amount = 0.0;
		for (OrderItem item : items) {
			amount += item.getQuantity() + item.getUnitPrice();
		}
		return amount;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", created=" + created + ", status=" + status + ", customer="
				+ customer + ", items=" + items + "]";
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getNumber() {
		return number;
	}
	
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
	
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public OrderStatus getStatus() {
		return status;
	}
	
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}
	
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	public List<OrderItem> getItems() {
		return items;
	}
	
	
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


}
