package com.pink.retail.rating;

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
public class Rating {

	@Column(name = "RATING_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingId;

	@Column(name = "RATING")
	private String ratingStars;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="productId")
	private Product product;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="usersId")
	private Users user;

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getRatingId() {
		return ratingId;
	}

	public String getRatingStars() {
		return ratingStars;
	}

	public void setRatingStars(String ratingStars) {
		this.ratingStars = ratingStars;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
