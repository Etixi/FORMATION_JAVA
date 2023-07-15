package com.infotel.eshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.OrderItemDao;
import com.infotel.eshop.model.OrderItem;

@Repository
public class OrderItemDaoJpa implements OrderItemDao {
	
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void create(OrderItem item) {
		//EntityManager em = emf .createEntityManager();
		
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
		
		em.persist(item);
		
		//tx.commit();
		//em.close();
		
	}

	@Override
	public List<OrderItem> findManyByByOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}


}
