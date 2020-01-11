package com.pink.retail.order;

import java.util.List;

public interface BLIOrderService {
	List<Order> findAll();
	
	Order findById(int orderId);
	
	Order save(Order data);
	
	Order update(Order data);
	
	void remove();
	
	void removeOne(int orderId);
}
