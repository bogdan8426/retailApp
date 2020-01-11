package com.pink.retail.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.util.Utility;

@Service
public class BLProductService implements BLIProductService{

	public static final Logger LOGGER=Logger.getLogger(BLProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}
	public Product findById(int productId) {
		return productRepository.getByproductId(productId);
	}

	public Product save(Product data) {
		if(data.getImage()=="")
		{
			data.setImage("default.jpg");
		}
		return productRepository.save(data);
	}

	public Product update(Product data) {
		if(productRepository.existsById(data.getProductId())==true) {
			return productRepository.save(data);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
			return null;
		}
	}

	public void removeOne(int productId) {
		if(productRepository.existsById(productId)==true) {
			productRepository.deleteById(productId);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}
	}
	
	public void remove() {
		productRepository.deleteAll();
	}
	
	@Override
	public List<Product> findByCategoryId(int categoryId) {
		return productRepository.getByCategoryCategoryId(categoryId);
		
	}
	
	

}
