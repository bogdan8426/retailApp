package com.pink.retail.productDetails;

import org.springframework.data.repository.CrudRepository;

public interface ProductDetailsRepository extends CrudRepository<ProductDetails, Integer> {
	ProductDetails getByproductDetailsId(int productDetailsId);
}
