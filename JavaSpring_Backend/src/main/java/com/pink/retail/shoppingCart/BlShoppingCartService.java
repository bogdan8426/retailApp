package com.pink.retail.shoppingCart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlShoppingCartService implements BliShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Override
	public List<ShoppingCart> getByUser(int userId) {
		return shoppingCartRepository.getByUserUsersId(userId);
	}

	@SuppressWarnings("unused")
	@Override
	public String insertShoppingCart(ShoppingCart addProduct) {
		ShoppingCart productExist = shoppingCartRepository.getByUserUsersIdAndProductProductId(addProduct.getUser().getUsersId(), addProduct.getProduct().getProductId());
		if (productExist == null) {
			shoppingCartRepository.save(addProduct);
			return "success";
		} else {
			int quantitySelected=productExist.getQuantity();
			productExist.setQuantity(productExist.getQuantity() + addProduct.getQuantity());
			if (productExist.getProduct().getQuantity() >= productExist.getQuantity()) {
				shoppingCartRepository.save(productExist);
				return "success";
			}
			else
			{
				return Integer.toString(productExist.getProduct().getQuantity()-quantitySelected);
			}
		}

	}

	@Override
	public void deleteByUserAndProduct(int idUser,int idProduct) {
		ShoppingCart deleteProduct=shoppingCartRepository.getByUserUsersIdAndProductProductId(idUser,idProduct);
		shoppingCartRepository.deleteById(deleteProduct.getShoppingId());
	}

}
