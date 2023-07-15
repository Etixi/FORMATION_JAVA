package com.infotel.eshop.dao;
import java.util.List;

import com.infotel.eshop.model.Author;

public interface AuthorDao {
	List<Author> findManyByBook (int bookId);
	
	
}
