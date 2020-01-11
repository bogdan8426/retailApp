package com.pink.retail.shoppingCart;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pink.retail.product.Product;
import com.pink.retail.users.Users;

@Entity
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHOPPING_ID", unique = true, nullable = false)
	private int shoppingId;
	
	@Column(name="QUANTITY", nullable=false)
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="userId")
	private Users user;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="productId")
	private Product product;

	public ShoppingCart()
	{
		super();
	}
	public int getShoppingId() {
		return shoppingId;
	}

	public void setShoppingId(int shoppingId) {
		this.shoppingId = shoppingId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
