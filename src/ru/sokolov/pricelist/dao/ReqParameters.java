package ru.sokolov.pricelist.dao;

import java.util.Map;

public class ReqParameters {
	
	private String productCathegory;
	
	private String productName;
	
	private String productMinPrice;
	
	private String productMaxPrice;

	public ReqParameters(Map<String, String[]> parameters) {
		this.productCathegory = parameters.get("productCathegory")[0];
		this.productName = parameters.get("productName")[0];
		this.productMinPrice = parameters.get("productMinPrice")[0];
		this.productMaxPrice = parameters.get("productMaxPrice")[0];
	}

	public String getProductCathegory() {
		return productCathegory;
	}

	public void setProductCathegory(String productCathegory) {
		this.productCathegory = productCathegory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductMinPrice() {
		return productMinPrice;
	}

	public void setProductMinPrice(String productMinPrice) {
		this.productMinPrice = productMinPrice;
	}

	public String getProductMaxPrice() {
		return productMaxPrice;
	}

	public void setProductMaxPrice(String productMaxPrice) {
		this.productMaxPrice = productMaxPrice;
	}
}
