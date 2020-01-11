package com.pink.retail.shoppingCart;

import java.util.List;

public interface BliShoppingCartService {

	List<ShoppingCart> getByUser(int userId);
	
	String insertShoppingCart(ShoppingCart addProduct);
	
	void deleteByUserAndProduct(int idUser,int idProduct);
}
