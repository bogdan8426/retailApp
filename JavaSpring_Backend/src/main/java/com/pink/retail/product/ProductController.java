package com.pink.retail.product;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private BLProductService productService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.PUT)
	public Product addData(@RequestBody Product product) {
		return productService.save(product);
	}

	@RequestMapping(value = "/allProduct", method = RequestMethod.GET)
	public List<Product> getData() {
		return productService.findAll();
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product findById(@PathVariable @NotNull int productId) {
        return productService.findById(productId);
	}
	
	@RequestMapping(value = "/remove/allProduct", method = RequestMethod.DELETE)
	public void remove() {
		productService.remove();
	}
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.DELETE)
	public void removeOne(@PathVariable @NotNull int productId) {
		productService.removeOne(productId);
	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public Product updateData(@RequestBody Product product) {
		return productService.update(product);
	}
	
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public List<Product> findByCategoryId(@PathVariable @NotNull int categoryId) {
        return productService.findByCategoryId(categoryId);
	}
	
	
}
