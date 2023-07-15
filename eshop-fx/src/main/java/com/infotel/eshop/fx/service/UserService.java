package com.infotel.eshop.fx.service;

import java.io.StringReader;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.User;
import com.infotel.eshop.fx.xml.LoginRequestDom4jWriter;
import com.infotel.eshop.fx.xml.LoginResponseDom4jReader;

public class UserService {

	public User login(String username, String password) {
		
		
			
			Document docReq = DocumentHelper.createDocument();
			Element reqElt = docReq.addElement("LoginRequest", "http://www.infotel.com/eshop");
			reqElt.addElement("Username").setText(username);
			reqElt.addElement("Password").setText(password);
			
			Source source = new DocumentSource(docReq);
			DocumentResult result = new DocumentResult();
			
			WebServiceTemplate client = new WebServiceTemplate();
			client.setDefaultUri("http://localhost:8080/eshop-ws/services");
			
			client.sendSourceAndReceiveToResult(source, result);
			
			Document docResp = result.getDocument();
			Element respElt = docResp.getRootElement();
			
			User user = new User();
			user.setUsername(respElt.attributeValue("username"));
			user.setFirstName(respElt.elementText("FirstName"));
			user.setLastName(respElt.elementText("LastName"));
			
			return user;
		}
		
		public User login$(String username, String password) {
			
			String request = "<LoginRequest xmlns=\"http://www.infotel.com/eshop\">"
					+ "	<Username>" + username + "</Username>"
					+ "	<Password>" + password + "</Password>"
					+ "   </LoginRequest>";
			
			Source source = new StreamSource(new StringReader(request));
			Result result = new StreamResult(System.out);
			
			WebServiceTemplate client = new WebServiceTemplate();
			client.setDefaultUri("http://localhost:8080/eshop-ws/services");
			
			client.sendSourceAndReceiveToResult(source, result);
			
			return new User();
		}
		
		public User login_(String username, String password) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			//LoginRequestDomWriter writer = new LoginRequestDomWriter();
			LoginRequestDom4jWriter writer = new LoginRequestDom4jWriter();
			writer.write(username, password);
			
			if ("francois@yahoo.fr".equals(username) && "secret".equals(password)) {
				//LoginResponseSaxReader reader = new LoginResponseSaxReader();
				//LoginResponseDomReader reader = new LoginResponseDomReader();
				LoginResponseDom4jReader reader = new LoginResponseDom4jReader();
				
//				User user = new User();
//				user.setUsername(username);
//				user.setFirstName("François");
//				user.setLastName("Pignon");
				
				User user = reader.read();
				
				return user;
			}
			
			throw new AppException("Échec de l'authentification");
		}
			
		
}
