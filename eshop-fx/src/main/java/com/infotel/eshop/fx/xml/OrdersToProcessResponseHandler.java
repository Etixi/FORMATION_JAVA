package com.infotel.eshop.fx.xml;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.infotel.eshop.fx.model.Address;
import com.infotel.eshop.fx.model.Book;
import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderItem;

public class OrdersToProcessResponseHandler extends DefaultHandler{


	private String current;
	private List<Order> orders;
	private Order order;
	//private List<OrderItem> items;
	//private Address address;

	@Override
	public void startDocument() throws SAXException {
		System.out.println("START DOC ");
		orders = new ArrayList<>();

	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("START ELT : " + qName + " => " + attributes.getValue("username"));


		switch(qName) {

		case "order" -> {

			Order order = new Order();

			order.setId(Integer.parseInt(attributes.getValue("id")));
			order.setCreated(LocalDateTime.parse(attributes.getValue("created")));
			order.setStatus(attributes.getValue("status"));

			orders.add(order);


			//order.setItems(items);
		}

		case "customer" -> {

			Customer cust = new Customer();
			cust.setUsername(attributes.getValue("username"));
			order.setCustomer(cust);
		}
		
		case "Items" -> {
			
			order.setItems(new ArrayList<>());
		}
		
		case "Name", "Street", "Zip", "City", "Quantity", "Price"  -> {
			current = qName;
		}

		case "Address" -> {	

			Customer cust = order.getCustomer();
			cust.setAddress(new Address());

		}

		


		case "Item" -> {	

			OrderItem item = new OrderItem();
			item.setId(Integer.parseInt(attributes.getValue("id")));
			order.getItems().add(item);
			
		}

		case "Book"	 -> {

			current = qName;
			Book book = new Book();
			book.setId(Integer.parseInt(attributes.getValue("id")));

			OrderItem item = order.getItems().get(order.getItems().size() -1);
			item.setBook(book);	
		}

		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("END DOC ");


	}


	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("END ELT : " + qName);

		switch(qName) {

		case "Name", "Street", "Zip", "City", "Quantity", "Price", "Book" -> {
			current = null;
		}

		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("CHARS : => " + new String(ch, start, length));

		if(current == null) return;

		switch(current) {


		case "Name" -> {
			order.getCustomer().setName(new String(ch, start, length));
		}

		case "Street" -> {
			Address addr = order.getCustomer().getAddress();
			addr.setStreet(new String(ch, start, length));
		}

		case "City" -> {

			Address addr = order.getCustomer().getAddress();
			addr.setCity(new String(ch, start, length));
		}


		case "zip" -> {
			Address addr = order.getCustomer().getAddress();
			addr.setZip(new String(ch, start, length));
		}


		case "Quantity" -> 
		{	
			OrderItem item = order.getItems().get(order.getItems().size() -1);
			int quantity = Integer.parseInt(new String(ch, start, length));
			item.setQuantity(quantity);
		}
		
		case "Price" -> 
		{	
			OrderItem item = order.getItems().get(order.getItems().size() -1);
			double price = Double.parseDouble(new String(ch, start, length));
			item.setPrice(price);
		}
		}

	}


	public List<Order> getOrder() {
		return orders;
	}


}
