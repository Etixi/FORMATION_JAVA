
package com.infotel.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //@Table(name = "category")
public class Category {
	@Id
	//@Column(name="id")
	private int id;
	
	//@Column(name="name")
	private String name;
	
	public Category() {
		super();
	}
	
	public Category(int id, String name){
		super();
		this.id = id;
		this.name = name;
	}
	

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Category : " + id + "-" + name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public  void setName(String name) {
		this.name = name;
	}
}
