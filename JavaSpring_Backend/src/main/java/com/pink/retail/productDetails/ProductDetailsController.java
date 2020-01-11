package com.pink.retail.productDetails;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/details")
public class ProductDetailsController {

	@Autowired
	private BliProductDetailsService productDetailsService;
	
	@RequestMapping(value = "/addProductDetails", method = RequestMethod.PUT)
	public ProductDetails addData(@RequestBody ProductDetails productDetails) {
		return productDetailsService.save(productDetails);
	}

	@RequestMapping(value = "/allProductDetails", method = RequestMethod.GET)
	public List<ProductDetails> getData() {
		return productDetailsService.findAll();
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ProductDetails findById(@PathVariable @NotNull int productId) {
        return productDetailsService.findById(productId);
	}
	
	@RequestMapping(value = "/remove/allProductDetails", method = RequestMethod.DELETE)
	public void remove() {
		productDetailsService.remove();
	}
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.DELETE)
	public void removeOne(@PathVariable @NotNull int productId) {
		productDetailsService.removeOne(productId);
	}
	
	@RequestMapping(value = "/updateProductDetails", method = RequestMethod.POST)
	public ProductDetails updateData(@RequestBody ProductDetails productDetails) {
		return productDetailsService.update(productDetails);
	}
}
