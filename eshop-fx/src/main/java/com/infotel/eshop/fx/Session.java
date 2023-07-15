package com.infotel.eshop.fx;

import java.util.HashMap;
import java.util.Map;

public enum Session {
	Instance;

	private Map<String, Object> attributes = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name) {
		return (T)attributes.get(name);
	}
	
	public Session setAttribute(String name, Object value) {
		attributes.put(name, value);
		
		return this;
	}
	
	public void removeAttribute(String name) {
		attributes.remove(name);
	}
	
}
