package com.pink.retail.orderProducts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderProductsRepository extends CrudRepository<OrderProducts,Integer>{
	List<OrderProducts> getByOrderProductOrderId(int orderId);
}
