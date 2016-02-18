package ru.sokolov.pricelist.services;

import java.util.List;

import ru.sokolov.pricelist.dao.ProductDao;
import ru.sokolov.pricelist.dao.ReqParameters;
import ru.sokolov.pricelist.models.Product;

public class ProductService {
	
	private ProductDao productDao;
	
	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public List<Product> findAll() {
		return productDao.findAll();
	}
	
	public List<Product> findAppropriate(ReqParameters parameters) {
		return (List<Product>) productDao.findAppropriate(parameters);
	}
}
