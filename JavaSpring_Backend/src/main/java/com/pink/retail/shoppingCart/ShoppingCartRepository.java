package com.pink.retail.shoppingCart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart,Integer>{

	List<ShoppingCart> getByUserUsersId(int userId);
	ShoppingCart getByUserUsersIdAndProductProductId(int userId, int productId);
}
