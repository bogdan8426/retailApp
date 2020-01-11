package com.pink.retail.orderProducts;

import java.util.List;

public interface BliOrderProductsService {

	List<OrderProducts> getByOrderId(int orderId);
	void addProductOrder(OrderProducts orderProducts);
	void addAllOrders(List<OrderProducts> orders);
	void updateStockProduct(OrderProducts orderToChange);
	List<OrderProducts> getAllOrderProducts();
}
