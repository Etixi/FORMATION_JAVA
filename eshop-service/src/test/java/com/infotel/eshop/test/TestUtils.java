package com.infotel.eshop.test;

import java.math.BigInteger;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;

import org.assertj.db.type.Source;


public interface TestUtils {
	
	static String driver = "org.h2.Driver";
	static String url = "jdbc:h2:tcp://localhost/eshop";
	static String username = "scott";
	static String password = "tiger";

	static Connection getConnection() throws Exception {
		Class.forName(driver);
		
		return DriverManager.getConnection(url, username, password);
	}
	
	static void executeScript(String path) throws Exception {
		new Script(Paths.get(path), () -> {
			try {
				return getConnection();
			} catch (Exception e) {
				throw new RuntimeException("Open DB connection failed", e);
			}
		}).execute();
	}
		
	static Source getDbSource() {
	return new Source(url, username, password);
	}
	
	static String textToSha1(String text) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(text.getBytes("utf8"));
		
		return String.format("%040x", new BigInteger(digest.digest()));
	}
	
}
