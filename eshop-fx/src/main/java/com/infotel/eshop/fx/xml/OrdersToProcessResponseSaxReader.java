package com.infotel.eshop.fx.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.Order;

public class OrdersToProcessResponseSaxReader {

	public List<Order> read() {
		
		
		File file = new File("xml/OrdersToProcessResponse_2.xml");
		
		OrdersToProcessResponseHandler handler = new OrdersToProcessResponseHandler();
		
		try (InputStream is = new FileInputStream(file)) {
			
			SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(is, handler);
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new AppException("Échec parsing du fichier OrdersToprocess response", e);
		}
		
		return handler.getOrder();
	
	}
}
