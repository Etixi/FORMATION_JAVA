package com.infotel.eshop.dao.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.OrderDao;
import com.infotel.eshop.dto.OrderDto;
import com.infotel.eshop.model.Order;
import com.infotel.eshop.model.OrderStatus;

@Repository
public class OrderDaoJpa implements OrderDao {
	
	@PersistenceContext
	private EntityManager em;
	
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	@Override
	public void create(Order order) {
		//EntityManager em = emf .createEntityManager();
		
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
		
		em.persist(order);
		
		//tx.commit();
		//em.close();
		
	}

	@Override
	public List<Order> findManyByCustomer(String username) {
		
		//EntityManager em = emf .createEntityManager();
		String jpql ="select distinct o from Order o join fetch o.items where o.customer.username=:id";
		
		TypedQuery<Order> query = em.createQuery(jpql, Order.class);
		query.setParameter("id",  username);
		
		
		List<Order> orders = query.getResultList();
		Order order = orders.isEmpty() ? null:orders.get(0);
		
		
		///em.close();
		return orders;
	
			
	}
	@Override
	public List<Order> findManyByStatus(OrderStatus ... statusArray) {
		
		List<OrderStatus> statusList = Arrays.asList(statusArray);
		
		String jpql = "select distinct o from Order o left join fetch o.items where o.status in (:statList)";
		//String jpql = "select distinct o from Order o left join fetch o.items where o.status in (Allocated, Packed)";
		TypedQuery<Order> query = em.createQuery(jpql, Order.class);
		query.setParameter("statList", statusList);
		
		List<Order> orders = query.getResultList();
		
		return orders;
		
	}
	
	

}
