package ru.sokolov.pricelist.services;

import java.util.List;

import ru.sokolov.pricelist.dao.ProductDao;
import ru.sokolov.pricelist.models.Product;

public class ProductService {
	
	private static ProductDao productDao;
	
	public ProductService() {
		productDao = new ProductDao();
	}
	
	public List<Product> findAll() {
		return productDao.findAll();
	}
	
	public List<Product> findAppropriate(String sql) {
		return (List<Product>) productDao.findAppropriate(sql);
	}
}
