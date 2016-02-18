package ru.sokolov.pricelist.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.sokolov.pricelist.models.Product;

public class ProductDao {
	
	private EntityManagerFactory emf;
	
	public ProductDao(String persisntenceUnit) {
		
		emf = Persistence.createEntityManagerFactory(persisntenceUnit);
	}
	
	/**
	 * @return List<{@link}Product> with all database entries
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT p FROM Product p");
		//Add limit for ResultList size
		query.setFirstResult(0);
		query.setMaxResults(50);
		List<Product> products = (List<Product>) query.getResultList();
		
		em.close();
		
		return products;
	}
	
	/**
	 * @param parameters describe the appropriate entries in database
	 * @return List<{@link}Product> with only appropriate database entries
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAppropriate(ReqParameters parameters) {
		
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("SELECT p FROM Product p WHERE "
				+ "p.cat.name LIKE :productCathegory AND "
				+ "p.name LIKE :productName AND "
				+ "p.price >= :productMinPrice AND "
				+ "p.price <= :productMaxPrice");
		
		query.setParameter("productCathegory", parameters.getProductCathegory()+"%");
		query.setParameter("productName", parameters.getProductName()+"%");
		query.setParameter("productMinPrice", "".equals(parameters.getProductMinPrice()) ? 0.0 : Double.parseDouble(parameters.getProductMinPrice()));
		query.setParameter("productMaxPrice", "".equals(parameters.getProductMaxPrice()) ? Double.MAX_VALUE : Double.parseDouble(parameters.getProductMaxPrice()));
		//Add limit for ResultList size
		query.setFirstResult(0);
		query.setMaxResults(50);
		
		List<Product> products = (List<Product>) query.getResultList();
		
		em.close();
		
		return products;
	}
}
