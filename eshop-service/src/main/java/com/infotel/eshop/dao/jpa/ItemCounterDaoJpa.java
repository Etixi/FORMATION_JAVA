package com.infotel.eshop.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.ItemCounterDao;
import com.infotel.eshop.model.ItemCounter;

@Repository
public class ItemCounterDaoJpa implements ItemCounterDao{
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ItemCounter findOneByItem(String item) {
		//EntityManager em = emf .createEntityManager();
		String jpql ="select i from ItemCounter i where i.item=:id";
		
//		TypedQuery<ItemCounter> query = em.createQuery(jpql, ItemCounter.class);
//		query.setParameter("id",  item);
//		
//		
//		List<ItemCounter> counters = query.getResultList();
//		ItemCounter counter = counters.isEmpty() ? null:counters.get(0);
		
		ItemCounter counter = em.find(ItemCounter.class, item);
		
		
		
		
		
		//em.close();
		return counter;
	}
	

	@Override
	public void create(ItemCounter counter) {
		//EntityManager em = emf .createEntityManager();
		
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
		
		em.persist(counter);
		
		//tx.commit();
		//em.close();	
		
	}

	@Override
	public void update(ItemCounter counter) {
		
		//EntityManager em = emf .createEntityManager();
		
		//EntityTransaction tx = em.getTransaction();
		//tx.begin();
		
		em.merge(counter);
		
		//tx.commit();
		//em.close();	
		
	}
	
	

}
