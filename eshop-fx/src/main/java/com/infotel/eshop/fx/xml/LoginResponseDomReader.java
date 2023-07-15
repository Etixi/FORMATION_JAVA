package com.infotel.eshop.fx.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.User;

public class LoginResponseDomReader {

	public User read_() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			File file = new File("xml/LoginResponse_2.xml");
			Document doc = builder.parse(file);
			
			User user = new User();
			Element rootElt = doc.getDocumentElement();
			Element firstNameElt = (Element) rootElt.getElementsByTagName("FirstName").item(0);
			Element lastNameElt = (Element) rootElt.getElementsByTagName("LastName").item(1);
			
			user.setUsername(rootElt.getAttribute("username"));
			user.setFirstName(firstNameElt.getTextContent());
			user.setLastName(lastNameElt.getTextContent());
			
			return user;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new AppException("Echec lors du parsing de login response", e);
		}
	}
	
	public User read() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			File file = new File("xml/LoginResponse_2.xml");
			Document doc = builder.parse(file);
			
			User user = new User();
			Element rootElt = doc.getDocumentElement();
			user.setUsername(rootElt.getAttribute("username"));

			NodeList nodes = rootElt.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)node;
					switch (element.getNodeName()) {
					case "FirstName" -> user.setFirstName(element.getTextContent());
					case "LastName" -> user.setLastName(element.getTextContent());
					}
				}
			}
			
			return user;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new AppException("Echec lors du parsing de login response", e);
		}
	}

}