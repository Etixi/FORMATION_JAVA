package com.infotel.eshop.fx.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private String number;
	private LocalDateTime created;
	private String status;
	
	private List<OrderItem> items;
	private Customer customer;
	
	public void addItem(OrderItem item) {
		if (items == null) items = new ArrayList<>();
		items.add(item);
	}
	
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", created=" + created + ", status=" + status + ", items="
				+ items + ", customer=" + customer + "]";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
}
