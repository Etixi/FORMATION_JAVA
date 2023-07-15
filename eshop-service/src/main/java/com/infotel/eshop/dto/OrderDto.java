package com.infotel.eshop.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.infotel.eshop.jackson.LocalDateTimeDeserializer;
import com.infotel.eshop.jackson.LocalDateTimeSerializer;

public class OrderDto {
	private int id;
	private String number;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime created;
	private String status;
	private double totalAmount;
	
	private CustomerDto customer;
	private List<OrderItemDto> items;
	
	@Override
	public String toString() {
		return "OrderDto [id=" + id + ", number=" + number + ", created=" + created + ", status=" + status
				+ ", totalAmount=" + totalAmount + ", customer=" + customer + ", items=" + items + "]";
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

}
