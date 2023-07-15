package com.infotel.eshop.dao;

import com.infotel.eshop.exception.EShopException;
import com.infotel.eshop.model.User;

public interface UserDao{
	// Recherche unique par nom de l'utilistateur.
	User findOneByUsername(String username);
	// On crée l'utilisateur
	void create(User user); 
		
		

}
