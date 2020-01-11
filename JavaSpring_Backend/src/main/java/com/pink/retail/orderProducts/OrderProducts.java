package com.pink.retail.orderProducts;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pink.retail.order.Order;
import com.pink.retail.product.Product;

@Entity
public class OrderProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_PRODUCT_ID", unique = true, nullable = false)
	private int idOrderProduct;
	
	@Column(name="QUANTITY", nullable=false)
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="orderId")
	private Order orderProduct;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="productId")
	private Product productOrder;
	
	public OrderProducts()
	{
		super();
	}

	public int getIdOrderProduct() {
		return idOrderProduct;
	}

	public void setIdOrderProduct(int idOrderProduct) {
		this.idOrderProduct = idOrderProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(Order orderProduct) {
		this.orderProduct = orderProduct;
	}

	public Product getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(Product productOrder) {
		this.productOrder = productOrder;
	}
}
