package com.infotel.eshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.CategoryDao;
import com.infotel.eshop.model.Category;

@Repository
public class CategoryDaoJpa implements CategoryDao{
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Category> findAll() {
		//EntityManager em = emf .createEntityManager();
	
		String jpql ="select c from Category c";
		
		TypedQuery<Category> query = em.createQuery(jpql, Category.class);
		List<Category> categories = query.getResultList();
		
		
		//em.close();
		return categories;
	}

}
