package com.pink.retail.productDetails;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.util.Utility;

@Service
public class BlProductDetailsService implements BliProductDetailsService{
	public static final Logger LOGGER=Logger.getLogger(BlProductDetailsService.class);
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	public List<ProductDetails> findAll() {
		return (List<ProductDetails>) productDetailsRepository.findAll();
	}
	public ProductDetails findById(int productId) {
		return productDetailsRepository.getByproductDetailsId(productId);
	}

	public ProductDetails save(ProductDetails data) {
		return productDetailsRepository.save(data);
	}

	public ProductDetails update(ProductDetails data) {
		if(productDetailsRepository.existsById(data.getProductDetailsId())==true) {
			return productDetailsRepository.save(data);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
			return null;
		}
	}

	public void removeOne(int productId) {
		if(productDetailsRepository.existsById(productId)==true) {
			productDetailsRepository.deleteById(productId);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}
	}
	
	public void remove() {
		productDetailsRepository.deleteAll();
	}
}
