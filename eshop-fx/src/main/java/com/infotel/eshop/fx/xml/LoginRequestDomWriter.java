package com.infotel.eshop.fx.xml;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.infotel.eshop.fx.exception.AppException;

public class LoginRequestDomWriter {

	public void write(String username, String password) {
		try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		Element rootElt = doc.createElement("LoginRequest");
		rootElt.setAttribute("xmlns", "http://www.infotel.com/eshop");
		Element usernameElt = doc.createElement("Username");
		Element passwordElt = doc.createElement("Password");
		
		Text usernameText = doc.createTextNode(username);
		Text passwordText = doc.createTextNode(password);
		
		usernameElt.appendChild(usernameText);
		passwordElt.appendChild(passwordText);
		rootElt.appendChild(usernameElt);
		rootElt.appendChild(passwordElt);
		
		doc.appendChild(rootElt);
		
		Source source = new DOMSource(doc);
		Result result = new StreamResult(System.out);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		
		
		} catch (ParserConfigurationException | TransformerException e) {
			throw new AppException("Echec Ã©criture login request", e);
		}
	}
	

}