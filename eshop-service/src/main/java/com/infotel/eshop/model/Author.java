package com.infotel.eshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {
	@Id
	private int id;
	private String name;

	public Author() {
		super();
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
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


	public void setName(String name) {
		this.name = name;
	}


}
