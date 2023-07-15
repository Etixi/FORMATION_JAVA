package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.model.Book;

public interface BookDao {
	
	// rechercche de livre par identifiant
	
	Book findOneById(int id);
	
	//Recherche de livre par clé et identifiant de catégorie
	List<Book> findManyByCriteria(String keyword, int categoryId);

}