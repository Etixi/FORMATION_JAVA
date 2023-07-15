package com.infotel.eshop.ui;

import java.util.HashMap;

import java.util.Map;




public enum Session {
	Instance;
	//private static Session session;
	
	private Map<String, Object>attributes = new HashMap<>();
	
	/*
	 * private Session() { super(); }
	 * 
	 * public static Session getSession() { if(session == null) { session = new
	 * Session(); session.attributes = new HashMap<>(); }; return session;
	 * 
	 * }
	 */
	
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String name) {
		return (T)attributes.get(name);
	}
	
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
		
	}
		
		public void removeAttribute(String name) {
			attributes.remove(name);
		
	}
	
}
