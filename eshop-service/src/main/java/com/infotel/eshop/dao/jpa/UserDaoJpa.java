 package com.infotel.eshop.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.UserDao;
import com.infotel.eshop.model.User;


@Repository
public class UserDaoJpa implements UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	
	@Override
	public User findOneByUsername(String username) {
		
		//EntityManager em = emf .createEntityManager();
//		String jpql ="select u from User u where u.username=:id";
//		
//		TypedQuery<User> query = em.createQuery(jpql, User.class);
//		query.setParameter("id",  username);
//		
		
//		List<User> users = query.getResultList();
//		User user = users.isEmpty() ? null:users.get(0);
		if(username == null) return null;
		User users = em.find(User.class, username);
	
		
		
		
		
		//em.close();
		return users;
	}

	@Override
	public void create(User user) {
	
		//EntityManager em = emf .createEntityManager();
		
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
		
		em.persist(user);
		
		//tx.commit();
		//em.close();
		//
		
	}

}
