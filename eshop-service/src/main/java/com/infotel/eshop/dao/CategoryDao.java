
package com.infotel.eshop.dao;

import java.util.List;

import com.infotel.eshop.model.Category;

public interface CategoryDao {
	//La table categorie devient une liste et la méthode
	//findAll() permet d'efectuer les recherches à partir de tout les paramtres.
	List<Category> findAll();
}
