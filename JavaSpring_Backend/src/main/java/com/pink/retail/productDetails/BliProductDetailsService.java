package com.pink.retail.productDetails;

import java.util.List;

public interface BliProductDetailsService {
	List<ProductDetails> findAll();
	
	ProductDetails findById(int productDetailsId);
	
	ProductDetails save(ProductDetails data);
	
	ProductDetails update(ProductDetails data);
	
	void remove();
	
	void removeOne(int id);
}
