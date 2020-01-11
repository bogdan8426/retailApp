package com.pink.retail.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.category.Category;
import com.pink.retail.orderProducts.OrderProducts;
import com.pink.retail.productDetails.ProductDetails;
import com.pink.retail.question.Question;
import com.pink.retail.rating.Rating;
import com.pink.retail.shoppingCart.ShoppingCart;
import com.pink.retail.users.Users;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity
public class Product {

	@Column(name = "PRODUCT_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "QUANTITY")
	private int quantity;

	@Column(name = "PRICE")
	private double price;
	
	@Column(name="IMAGE")
	private String image;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_DETAILS_ID")
	private ProductDetails productDetails;

	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
	private List<ShoppingCart> shoppingCartList;
	
	@OneToMany(mappedBy = "productOrder", cascade = CascadeType.MERGE)
	private List<OrderProducts> orderProducts;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="CATEGORY_ID")
	private Category category;
	
	@ManyToMany(mappedBy = "products",cascade = CascadeType.MERGE)
	private List<Users> users=new ArrayList<Users>();
	
	@OneToMany(mappedBy="product",cascade =
	CascadeType.PERSIST)
	private List<Rating> ratings;
	
	@OneToMany(mappedBy="product",cascade =
			CascadeType.PERSIST)
			private List<Question> questions;

	public Product(String name, String brand, int quantity, double price) {
		super();
		this.name = name;
		this.brand = brand;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	
	@JsonIgnore
	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	@JsonIgnore
	public List<OrderProducts> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProducts> orderProducts) {
		this.orderProducts = orderProducts;
	}

	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@JsonIgnore
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	@JsonIgnore
	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	@JsonIgnore
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String toString() {
		if (quantity == 0) {
			return name + " " + brand + " - Sold Out ! ";
		} else {
			return productId + "." + name + " " + brand + " Quantity:" + quantity + " Price/Item: " + price + " RON";
		}
	}

}
