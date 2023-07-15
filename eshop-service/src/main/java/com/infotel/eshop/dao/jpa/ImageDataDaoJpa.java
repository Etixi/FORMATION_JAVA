package com.infotel.eshop.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.infotel.eshop.dao.jdbc.ImageDataDao;
import com.infotel.eshop.model.ImageData;

@Repository
public class ImageDataDaoJpa implements ImageDataDao{
	//private static EntityManagerFactory emf = JpaContext.Instance.emf;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ImageData findOneById(int id) {
		
		//EntityManager em = emf .createEntityManager();
		
//		String jpql ="select img from ImageData img where img.id = :id";
//		
//		TypedQuery<ImageData> query = em.createQuery(jpql, ImageData.class);
//		query.setParameter("id", id);
//		
//		List<ImageData> images = query.getResultList();
		ImageData image = em.find(ImageData.class, id);
		//ImageData image = images.get(0);
		
		
		//em.close();
		return image;
	}

	@Override
	public Integer findOneByEntity(String target, String targetId) {
		
		//EntityManager em = emf .createEntityManager();
		String jpql ="select img.id  from ImageData img where img.target= :targ and img.targetId = :targetId";
		
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		query.setParameter("targ", target);
		query.setParameter("targetId", targetId);
		
		//List<Integer> imageIds = query.;
		Integer id = query.getSingleResult();
		
		//em.close();
		return id;
	}

}
