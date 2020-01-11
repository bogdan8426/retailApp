package com.pink.retail.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.address.Address;
import com.pink.retail.answer.Answer;
import com.pink.retail.order.Order;
import com.pink.retail.product.Product;
import com.pink.retail.question.Question;
import com.pink.retail.rating.Rating;
import com.pink.retail.role.Role;
import com.pink.retail.shoppingCart.ShoppingCart;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private int usersId;

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	private String lastName;

	@Column(name = "EMAIL", unique = true, nullable = false, length = 50)
	private String email;

	@Column(name = "PHONE_NUMBER", nullable = false, length = 50)
	private String phoneNumber;

	@Column(name = "PASSWORD", nullable = false, length = 50)
	private String password;

	 @ManyToMany(cascade = CascadeType.MERGE)
	 @JoinTable(name = "USERS_ADDRESS", 
		joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}, 
		inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")})
	 private List<Address> addresses=new ArrayList<Address>();

	 @ManyToMany(cascade = CascadeType.MERGE)
	 @JoinTable(name = "FAVORITES", 
		joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}, 
		inverseJoinColumns = { @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")})
	 private List<Product> products=new ArrayList<Product>();
	 
	@OneToMany(mappedBy = "users", cascade =
	CascadeType.PERSIST,targetEntity=Order.class)
	private List<Order> listOrders;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ROLE_ID")
	private Role role;
	
	@OneToMany(mappedBy="user",cascade =
	CascadeType.PERSIST)
	private List<ShoppingCart> shoppingCartList;
	
	@OneToMany(mappedBy="user",cascade =
	CascadeType.PERSIST)
	private List<Rating> ratings;
	
	@OneToMany(mappedBy="user",cascade =
			CascadeType.PERSIST)
			private List<Question> questions;
	
	@OneToMany(mappedBy="user",cascade =
			CascadeType.PERSIST)
			private List<Answer> answerss;
	
	public Users() {
		super();
	}

	public Users(String firstName, String lastName, String email, String phoneNumber, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@JsonIgnore
	public List<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<Order> listOrders) {
		this.listOrders = listOrders;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@JsonIgnore
	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
	
	@JsonIgnore
	public List<Answer> getAnswerss() {
		return answerss;
	}

	public void setAnswerss(List<Answer> answerss) {
		this.answerss = answerss;
	}

	
}
