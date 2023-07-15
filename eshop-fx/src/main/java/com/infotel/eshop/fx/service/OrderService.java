package com.infotel.eshop.fx.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.infotel.eshop.fx.model.Address;
import com.infotel.eshop.fx.model.Book;
import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderItem;
import com.infotel.eshop.fx.xml.OrdersToProcessResponseDom4jReader;

public class OrderService<OrdersToProcessResponseHandlerDom4jReader> {

//	public List<Order> getOrdersToProcess() {
//		Order order1 = new Order();
//		order1.setId(1);
//		order1.setNumber("CDE23-00010");
//		
//		Order order2 = new Order();
//		order2.setId(2);
//		order2.setNumber("CDE23-00015");
//		
//		return List.of(order1, order2);
//	}
	
	public List<Order> getOrdersToProcess() {
		Document docReq = DocumentHelper.createDocument();
		docReq.addElement("OrdersToProcessRequest", "http://www.infotel.com/eshop");
		Source source = new DocumentSource(docReq);
		DocumentResult result = new DocumentResult();
		
		WebServiceTemplate client = new WebServiceTemplate();
		client.setDefaultUri("http://localhost:8080/eshop-ws/services");
		
		client.sendSourceAndReceiveToResult(source, result);
		
		Document docResp = result.getDocument();
		Element respElt = docResp.getRootElement();
		

		List<Order> orders = new ArrayList<>();
			
		List<Element> orderEltList = respElt.elements("Order");
		for (Element orderElt : orderEltList) {
			Order order = new Order();
			order.setId(Integer.parseInt(orderElt.attributeValue("id")));
			order.setNumber(orderElt.attributeValue("number"));
			order.setCreated(LocalDateTime.parse(orderElt.attributeValue("created")));
			order.setStatus(orderElt.attributeValue("status"));
			
			orders.add(order);
			
			Customer cust = new Customer();
			Element custElt = orderElt.element("Customer");
			cust.setUsername(custElt.attributeValue("username"));
			cust.setName(custElt.elementText("Name"));
			order.setCustomer(cust);
			
			Element addrElt = custElt.element("Address");
			if (addrElt != null) {
				Address addr = new Address();
				addr.setStreet(addrElt.elementText("Street"));
				addr.setZip(addrElt.elementText("Zip"));
				addr.setCity(addrElt.elementText("City"));
				cust.setAddress(addr);				
			}
			
			List<Element> itemEltList = orderElt.element("Items").elements("Item");
			for (Element itemElt : itemEltList) {
				OrderItem item = new OrderItem();
				item.setId(Integer.parseInt(itemElt.attributeValue("id")));
				item.setPrice(Double.parseDouble(itemElt.elementText("Price")));
				item.setQuantity(Integer.parseInt(itemElt.elementText("Quantity")));
				
				Element bookElt = itemElt.element("Book");
				
				Book book = new Book();
				book.setId(Integer.parseInt(bookElt.attributeValue("id")));
				book.setName(bookElt.getText());
				item.setBook(book);
				order.addItem(item);
			}				
		}
		
		System.out.println(orders);
		
		return orders;

	}
	
//	public List<Order> getOrdersToProcess() {
//		new OrdersToProcessRequestDom4jWriter().write();
//		
//		//OrdersToProcessResponseSaxReader reader = new OrdersToProcessResponseSaxReader();
//		OrdersToProcessResponseDom4jReader reader = new OrdersToProcessResponseDom4jReader();
//		return reader.read();
//	}
//	
	public List<Order> getOrdersToProcess_() {
		// clients
		Customer cust1 = new Customer();
		cust1.setUsername("emile@mailo.fr");
		cust1.setName("Émile Lampion");
		
		Address address1 = new Address();
		address1.setStreet("20 rue de Belleville");
		address1.setZip("75020");
		address1.setCity("Paris");
		
		cust1.setAddress(address1);
		
		Customer cust2 = new Customer();
		cust2.setUsername("amelie@yahoo.fr");
		cust2.setName("Amélie Poulain");
		
		Address address2 = new Address();
		address2.setStreet("18 rue Montorgueil");
		address2.setZip("75018");
		address2.setCity("Paris");
		
		cust2.setAddress(address2);
		
		// livres
		Book book1 = new Book();
		book1.setId(1);
		book1.setName("Astérix en Corse");
		
		Book book2 = new Book();
		book2.setId(2);
		book2.setName("Tintin au Tibet");
		
		Book book3 = new Book();
		book3.setId(3);
		book3.setName("Adèle et la Bête");
		
		// commandes
		Order order1 = new Order();
		order1.setId(1);
		order1.setCustomer(cust1);
		order1.setNumber("CDE22-0010");
		order1.setStatus("Pending");
		order1.setCreated(LocalDateTime.now().minusDays(10));
		
		Order order2 = new Order();
		order2.setId(2);
		order2.setCustomer(cust2);
		order2.setNumber("CDE22-0015");
		order2.setStatus("Packed");
		order2.setCreated(LocalDateTime.now().minusDays(5));
		
		// lignes de commandes
		OrderItem item1 = new OrderItem();
		item1.setId(1);
		item1.setQuantity(2);
		item1.setBook(book1);
		item1.setPrice(10.80);
		
		OrderItem item2 = new OrderItem();
		item2.setId(2);
		item2.setQuantity(1);
		item2.setBook(book2);
		item2.setPrice(11.20);
		
		OrderItem item3 = new OrderItem();
		item3.setId(3);
		item3.setQuantity(1);
		item3.setBook(book3);
		item3.setPrice(11.50);
		
		order1.setItems(List.of(item1, item2));
		order2.setItems(List.of(item3));
		
		return List.of(order1, order2);
	}

}
