package com.infotel.eshop.fx.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.infotel.eshop.fx.model.User;

public class LoginResponseHandler extends DefaultHandler {
	
	private User user;
	private String current;

	@Override
	public void startDocument() throws SAXException {
		user = new User();
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("END DOC");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		
		switch (qName) {
		case "LoginResponse" -> {
			String username = attributes.getValue("username");
			user.setUsername(username);
		}
		case "FirstName", "LastName" -> {
			current = qName;
		}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		
		switch (qName) {
		case "FirstName", "LastName" -> {
			current = null;
		}
		}

	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (current == null) return;
		switch (current) {
		case "FirstName" -> user.setFirstName(new String(ch, start, length));
		case "LastName" -> user.setLastName(new String(ch, start, length));
		}
	}

	public User getUser() {
		return user;
	}
}
