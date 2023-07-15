package com.infotel.eshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.BookDao;
import com.infotel.eshop.model.Book;

@Repository
public class BookDaoJpa implements BookDao{
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	private static Logger log = LogManager.getLogger(BookDaoJpa.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Book findOneById(int id) {
		
		
		//EntityManager em = emf.createEntityManager();
//		String jpql ="select b from Book b where b.id= :id";
//		
//		TypedQuery<Book> query = em.createQuery(jpql, Book.class);
//		query.setParameter("id", id);
//		
//		List<Book> books = query.getResultList();
//		
//		if(books.isEmpty()) return null;
		Book book = em.find(Book.class, id);
		
		//Pas terrible
		if(book != null) {
		book.getAuthors().size();
		//book.getTags().size();
		
		for(String tag : book.getTags()) {
			System.out.println(tag);
		}
		
		}
		
//		if(log.isDebugEnabled()) {
//			log.debug("Chargement du livre" + book);
//		}
		
		//em.close();
		return book;
	}

	@Override
	public List<Book> findManyByCriteria(String keyword, int categoryId) {
		//EntityManager em = emf.createEntityManager();
		String jpql ="select b from Book b where 1=1";
		//String jpql ="select b from Book b left join fetch b.authors join fetch b.details where 1=1";
		
		if(keyword != null && !keyword.isBlank()) {
			jpql += " and (lower(title) like:keyword or lower(b.details.series) like :keyword)"; //
		}

		if(categoryId != -1) {
			jpql += " and b.category.id = :catId";
		}
		
		TypedQuery<Book> query = em.createQuery(jpql, Book.class);
		query.setMaxResults(10);
		
		if(keyword != null && !keyword.isBlank()) {
			query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
			
		}
		if(categoryId != -1) {
			query.setParameter("catId", categoryId);
		}
		
		List<Book> books = query.getResultList();
		
		
		//em.close();
		return books;
	}

}
