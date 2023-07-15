package com.infotel.eshop.fx.xml;

import java.io.IOException;
import java.io.PrintWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.infotel.eshop.fx.exception.AppException;

public class LoginRequestDom4jWriter {

	public void write(String username, String password) {
		Document doc = DocumentHelper.createDocument();
		
		Element rootElt = doc.addElement("LoginRequest");
		rootElt.addElement("Username").setText(username);
		rootElt.addElement("Password").setText(password);
		
//		try(PrintWriter pw = new PrintWriter(System.out)) {
//			doc.write(pw);
//		} catch (IOException e) {
//			throw new AppException("Echec de l'écriture login request", e);
//		}
		XMLWriter writer = null;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			writer = new XMLWriter(System.out, format);
			writer.write(doc);
			
		} catch (IOException e){
			throw new AppException("Echec écriture login request", e);
		} finally {
			try {
				if (writer != null) writer.close();
			} catch (IOException e) {
			}
		}
		
	}

}