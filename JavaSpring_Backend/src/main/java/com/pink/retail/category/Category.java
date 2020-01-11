package com.pink.retail.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.product.Product;

@Entity
public class Category {

	
	@Column(name = "CATEGORY_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
	private List<Product> products;

	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@JsonIgnore
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Category(int categoryId, String categoryName, List<Product> products) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.products = products;
	}
	public Category() {}
	
}
