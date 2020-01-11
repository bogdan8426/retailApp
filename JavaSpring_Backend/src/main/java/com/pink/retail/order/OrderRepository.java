package com.pink.retail.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
	Order getByorderId(int productID);
}
