package com.pink.retail.product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Integer> {
	Product getByproductId(int productId);
	List<Product> getByCategoryCategoryId(int categoryId);
}
