package ru.sokolov.pricelist.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROD")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 4453443914139540116L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Cathegory cat;
	
	private String name;
	
	private double price;

	public Product() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cathegory getCat() {
		return cat;
	}

	public void setCat(Cathegory cat) {
		this.cat = cat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [cathegory=" + cat + ", name=" + name + ", price=" + price + "]";
	}

}
