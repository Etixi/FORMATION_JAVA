
package com.infotel.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "item_counter")
public class ItemCounter {
	
	@Id
	private String item;
	
	@Column(name="item_value")
	private int value;
	
	
	public void increment() {
		value++;
	}
	
	@Override
	public String toString() {
		return "ItemCounter [item=" + item + ", value=" + value + "]";
	}


	public String getItem() {
		return item;
	}
	
	
	public void setItem(String item) {
		this.item = item;
	}
	
	
	public int getValue() {
		return value;
	}
	
	
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
