package com.pink.retail.product;

import java.util.List;

public interface BLIProductService {
	List<Product> findAll();
	
	Product findById(int productId);
	
	List<Product> findByCategoryId(int categoryId);
	
	Product save(Product data);
	
	Product update(Product data);
	
	void remove();
	
	void removeOne(int id);
}
