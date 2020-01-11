package com.pink.retail.shoppingCart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
public class ShoppingCartController {

	@Autowired
	BliShoppingCartService shoppingCartService;
	
	@RequestMapping(value = "/productsShop/{idUser}", method = RequestMethod.GET)
	public List<ShoppingCart> getProductsByUser(@PathVariable int idUser)
	{
		return shoppingCartService.getByUser(idUser);
	}
	
	@RequestMapping(value="/addProductToList", method=RequestMethod.PUT)
	public String insertProductToList(@RequestBody ShoppingCart shoppingCartItem)
	{
		return shoppingCartService.insertShoppingCart(shoppingCartItem);
	}
	
	@RequestMapping(value="/deleteShoppCart/{idUser}/{idProduct}",method=RequestMethod.DELETE)
	public void deleteProductByUserIdAndProductId(@PathVariable int idUser,@PathVariable int idProduct)
	{
		shoppingCartService.deleteByUserAndProduct(idUser,idProduct);
	}
}
