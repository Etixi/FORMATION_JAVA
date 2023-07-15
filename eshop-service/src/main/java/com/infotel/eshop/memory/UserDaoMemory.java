package com.infotel.eshop.memory;

import com.infotel.eshop.dao.UserDao;
import com.infotel.eshop.model.User;

public class UserDaoMemory implements UserDao{

	@Override
	public User findOneByUsername(String username) {
		//if (username.equals(user.getUsername())
//		for (int i =0; i < Database.USERS_TABLE.size(); i++) { //length
//		User user = Database.USERS_TABLE.get(username);//[i];
//			if (username.equals(user.getUsername())) {
//				return user;
//			}
//		}
		//return null;
		return Database.USERS_TABLE.get(username);
	}

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		Database.USERS_TABLE.put(user.getUsername(), user);
	}
	
	

}
