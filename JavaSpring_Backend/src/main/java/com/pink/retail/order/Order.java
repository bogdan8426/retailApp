package com.pink.retail.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pink.retail.orderProducts.OrderProducts;
import com.pink.retail.users.Users;


@Entity
@Table(name="orders")
public class Order{

	@Column(name = "ORDER_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int orderId;

	@Column(name = "ORDER_DATE")
	private String orderDate;
	
	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;
	
	@Column(name="TOTAL_PRICE")
	private String totalPrice;
	
	@Column(name="ORDER_ADDRESS",length = 550)
	private String orderAddress;
	
	@Column(name="FLAG")
	private boolean flag;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "usersId")
	private Users users;
	
	@OneToMany(mappedBy="orderProduct",cascade=CascadeType.MERGE)
	private List<OrderProducts> orderProducts;
	
	public Order() {
		super();
	}
	
	public Order(String orderDate,String paymentMethod) {
		this.orderDate = orderDate;
		this.paymentMethod = paymentMethod;
	}
	
	public Order(int orderID, String orderDate, String orderStatus, String paymentMethod) {
		this.orderId = orderID;
		this.orderDate = orderDate;
		this.paymentMethod = paymentMethod;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderID(int orderID) {
		this.orderId = orderID;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@JsonIgnore
	public List<OrderProducts> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProducts> orderProducts) {
		this.orderProducts = orderProducts;
	}
	

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	
	

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "\nOrderID: " + this.orderId + "\nOrderDate: " + orderDate ;
	}
	
}
