package com.infotel.eshop.fx.xml;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.infotel.eshop.fx.exception.AppException;
import com.infotel.eshop.fx.model.User;

public class LoginResponseDom4jReader {

	public User read() {
		try {
			File file = new File("xml/LoginResponse_2.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element rootElt = doc.getRootElement();

			User user = new User();
			user.setUsername(rootElt.attributeValue("username"));
			
			user.setFirstName(rootElt.elementTextTrim("FirstName"));
			user.setLastName(rootElt.elementTextTrim("LastName"));
			

			return user;
			
			
			
		} catch (DocumentException e) {
			throw new AppException("Echec lors du parsing de login response", e);
		}
	}

}