
package com.infotel.eshop.memory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.infotel.eshop.dao.BookDao;
import com.infotel.eshop.model.Book;

public class BookDaoMemory implements BookDao {
	
	
	// on incrémente en fonction de la dimension de la base donnée BOOKS
	// afin de trouver les identifiants qui se correspondent et puis on retourne
	// les livres correspondants
	
	@Override
	public Book findOneById(int id) {
		for (int i = 0; i < Database.BOOKS_TABLE.length; i++) {
			Book book = Database.BOOKS_TABLE[i];
			if(book.getId() == id) {
				return book;
				}
			}
		return null;
		}
		
	
	
	
	@Override
	public List<Book> findManyByCriteria(String keyword, int categoryId) {
//		Book[] bdd = new Book[] {
//				new Book(1, "Tintin au Tibet", 10.5),
//				new Book(2, "Astérix en Corse", 11.2),
//				new Book(3, "Adèle et la Bête", 11.3),
//				new Book (4, "Tintin au Congo", 10.8),
//				new Book(5, "Astérix le Gaulois", 10.2),
//		};
		
	//Book[] result = new Book[Database.BOOKS_TABLE.length];
		List <Book> result = new ArrayList<>();
	
	// Filtrage par mot clé
	//&& book.getCategory().getId()==categoryId
	
	
	
	int count = 0;
	

//	for(Iterator it = Database.BOOKS_TABLE.iterator(); it.hasNext();) {
//		Book book = Database.BOOKS_TABLE[i]; it.next();
	
	// filtrage par titre
	for (int i= 0; i < Database.BOOKS_TABLE.length; i++) {
		Book book = Database.BOOKS_TABLE[i];
		if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ) {
			result.add(book);
		}
	}
	// Filtrage par catégories
	
	if (categoryId != -1) {
//		for (int i = 0; i < result.length; i++) {
//			Book book = result[i];
//			if (book != null && book.getCategory().getId() != categoryId) {
//				result[i] = null;
//			}
//		}
		
		for(Iterator it = result.iterator(); it.hasNext();) {
			Book book = (Book) it.next();
			if (book != null && book.getCategory().getId() != categoryId) {
				it.remove();
			}
		}
	}
	
	return result;                          
	}
		
}
