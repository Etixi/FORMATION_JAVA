package com.infotel.eshop.dao.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.infotel.eshop.dao.UserDao;
import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.Customer;
import com.infotel.eshop.model.Merchant;
import com.infotel.eshop.model.User;

public class UserDaoFile implements UserDao {
	
	
	
	//public User findOneByUsername(String username) {
	private final static Path FILE_PATH = Paths.get("users.txt");
	
		

		
			//return Database.USERS_TABLE.get(username);
	
		
	
			
	@Override
	
	
	public User findOneByUsername(String username) {
		try {
			List<String> lines = Files.readAllLines(FILE_PATH);
			
			for(String line: lines) {
				
				String[] data = line.split(";");
			if(data.length < 2 || !username.equals(data[0])) continue;
				
				switch(data[1]) {
				case "CU" -> {
					Customer cust = new Customer();
					cust.setUsername(data[0]);
					cust.setPassword(data[2]);
					cust.setFirstName(data[3]);
					cust.setLastName(data[4]);
					cust.setTitle(data[5]);
					cust.setPhone(data[6]);
					
					
					
					String phone = "null".equals(data[6])? null :data[6];
					cust.setPhone(phone);
					
					return cust;
				}
				
				case "ME" -> {
					Merchant merchant = new Merchant();
					merchant.setUsername(data[0]);
					merchant.setPassword(data[2]);
					merchant.setFirstName(data[3]);
					merchant.setLastName(data[4]);
					
					return merchant;
				}
				}
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
//	@Override
//	public User findOneByUsername(String username) {
//		Scanner scanner = null;
//		try {
//		String line = scanner = new Scanner(FILE_PATH.toFile());
//		String[] data = line.split(";");
//		
//				if (!username.equals(data[0])) continue;
//				
//				switch(data[1]) {
//				case "CU" -> {
//					Customer cust = new Customer();
//					cust.setUsername(data[0]);
//					cust.setPassword(data[2]);
//					cust.setFirstName(data[3]);
//					cust.setLastName(data[4]);
//					cust.setTitle(data[5]);
//					cust.setPhone(data[6]);
//					
//					
//					
//					String phone = "null".equals(data[6])? null :data[6];
//					cust.setPhone(phone);
//					
//					return cust;
//				}
//				
//				case "ME" -> {
//					Merchant merchant = new Merchant();
//					merchant.setUsername(data[0]);
//					merchant.setPassword(data[2]);
//					merchant.setFirstName(data[3]);
//					merchant.setLastName(data[4]);
//					
//					return merchant;
//				}
//				}
//			}
//			
//		}catch (IOException e) {
//			e.printStackTrace();
//		}finally {
//			if(scanner != null)scanner.close();
//		}
//		return null;
//	}
	
	@Override
    public void create(User user) throws EShopException {
        if (user instanceof Customer) {
        	Customer cust = (Customer)user;
        	PrintWriter pw = null;
        	
        	try {
        		FileWriter fr = new FileWriter(FILE_PATH.toFile(), true);
        		pw = new PrintWriter(fr);
        		
        		StringBuilder sb = new StringBuilder();
        		
        		sb.append(cust.getUsername())
        		  .append(";CU")
        		  .append(cust.getPassword())
        		  .append(";")
        		  .append(cust.getFirstName())
        		  .append(";")
        		  .append(cust.getLastName())
        		  .append(";")
        		  .append(cust.getTitle())
        		  .append(";")
        		  .append(cust.getPhone())
        		  .append(";");
        		  
        		
        		pw.println(sb);
        		  
        	}catch(IOException ioe) {
        		throw new EShopException("Problème accès au fichier de la base") ;
        	}finally {
        		if(pw != null) pw.close();
        	}
        }
        else if(user instanceof Merchant) {
        	
        }
	}
				
}

	
	

