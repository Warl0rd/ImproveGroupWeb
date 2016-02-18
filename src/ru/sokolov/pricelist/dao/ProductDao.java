package ru.sokolov.pricelist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.sokolov.pricelist.models.Product;

public class ProductDao {
	
	private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ImproveGroup");
	
	private EntityManager em;

	public ProductDao() {
		em = emFactory.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		
		Query query = em.createQuery("SELECT p FROM Product p");
		List<Product> products = (List<Product>) query.getResultList();
		
		return products;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findAppropriate(String sql) {
		
		Query query = em.createQuery(sql);
		List<Product> products = (List<Product>) query.getResultList();
		
		return products;
	}
}
