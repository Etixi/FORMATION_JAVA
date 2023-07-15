package com.infotel.eshop.fx.xml;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.Address;
import com.infotel.eshop.fx.model.Book;
import com.infotel.eshop.fx.model.Customer;
import com.infotel.eshop.fx.model.Order;
import com.infotel.eshop.fx.model.OrderItem;

public class OrdersToProcessResponseDom4jReader {

	public List<Order> read(){
		try {
			File file = new File("xml/OrdersToProcessResponse_2.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element rootElt = doc.getRootElement();

			List<Order> orders = new ArrayList<>();
			List<Element> rootElts = rootElt.elements();
			
			  for (Element orderElt : rootElts) {
					Order order = new Order();
					order.setId(Integer.parseInt(orderElt.attributeValue("id")));
					order.setNumber(orderElt.attributeValue("number"));
					order.setCreated(LocalDateTime.parse(orderElt.attributeValue("created")));
					order.setStatus(orderElt.attributeValue("status"));
					
					
					Customer cust = new Customer();
					Element custElt = orderElt.element("Customer");
					cust.setUsername(custElt.attributeValue("username"));
					cust.setName(custElt.elementTextTrim("Name"));
					
					
					Address addr = new Address();
					Element addrElt = custElt.element("Address");
					addr.setStreet(addrElt.elementTextTrim("Street"));
					addr.setZip(addrElt.elementTextTrim("Zip"));
					addr.setCity(addrElt.elementTextTrim("City"));
					cust.setAddress(addr);
					order.setCustomer(cust);
						
					List<OrderItem> items = new ArrayList<>();
					OrderItem item = new OrderItem();
					Element itemsElt = orderElt.element("Items");
					List<Element> itemElt = itemsElt.elements("Item");
					for (Element itemAtt : itemElt) {
						item.setId(Integer.parseInt(itemAtt.attributeValue("id")));
						item.setQuantity(Integer.parseInt(itemAtt.elementTextTrim("Quantity")));
						item.setPrice(Double.parseDouble(itemAtt.elementTextTrim("Price")));
						
						Book book = new Book();
						Element bookElt = itemAtt.element("Book");
						book.setId(Integer.parseInt(bookElt.attributeValue("id")));
						book.setName(bookElt.getStringValue());
						item.setBook(book);
						items.add(item);
					}
					order.setItems(items);				
					orders.add(order);		
			  }
			
			return orders;
			
		} catch (DocumentException e) {
			throw new AppException("Echec lors du parsing de orders to process response", e);
		}
	}

}