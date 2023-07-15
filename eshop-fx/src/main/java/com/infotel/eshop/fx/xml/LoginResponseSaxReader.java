package com.infotel.eshop.fx.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.User;

public class LoginResponseSaxReader {

	public User read() {
		File file = new File("xml/LoginResponse_2.xml");
		LoginResponseHandler handler = new LoginResponseHandler();
		
		try (InputStream is = new FileInputStream(file)) {
		
			SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(is, handler);
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new AppException("Echec parsing du fichier login response", e);
		}
		return handler.getUser();
	}
	

}