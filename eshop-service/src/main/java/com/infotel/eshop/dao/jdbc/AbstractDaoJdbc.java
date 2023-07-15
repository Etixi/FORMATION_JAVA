
package com.infotel.eshop.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AbstractDaoJdbc {
	
	@Autowired  
	private DataSource datasource;
	
	
//	public void setDatasource(DataSource datasource) {
//		this.datasource = datasource;
//	}

//	public static Properties props = new Properties();
//	static {
//		props.setProperty("driver", "org.h2.Driver");
//		props.setProperty("url", "jdbc:h2:tcp://localhost:9092/eshop");
//		props.setProperty("username", "scott");
//		props.setProperty("password", "tiger");
//	}
	
	protected Connection getConnection() throws ClassNotFoundException, SQLException, NamingException {
		return datasource.getConnection();
		//return getConnectionDM();
		//return getConnectionDS();
	}
	

//	private Connection getConnectionDM() throws ClassNotFoundException, SQLException {
//		Connection cn;
//		Class.forName(props.getProperty("driver"));
//		
//		String url = props.getProperty("url");
//		
//		cn = DriverManager.getConnection(url, 
//				props.getProperty("username"), 
//				props.getProperty("password"));
//		
//		return cn;
//	}
//	
	private Connection getConnectionDS() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EShopDS");
		
		return ds.getConnection();
		
	}
	
	
//	protected Connection getConnection() throws ClassNotFoundException, SQLException {
//		Connection cn;
//		Class.forName("org.h2.Driver");
//		
//		String url = "jdbc:h2:tcp://localhost:9092/eshop";
//		
//		cn = DriverManager.getConnection(url, "scott", "tiger");
//		
//		return cn;
//	}

}