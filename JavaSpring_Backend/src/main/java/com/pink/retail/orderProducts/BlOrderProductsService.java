package com.pink.retail.orderProducts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.product.Product;
import com.pink.retail.product.ProductRepository;
import com.pink.retail.shoppingCart.ShoppingCart;
import com.pink.retail.shoppingCart.ShoppingCartRepository;

@Service
public class BlOrderProductsService implements BliOrderProductsService{

	@Autowired
	private OrderProductsRepository orderProductRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public List<OrderProducts> getByOrderId(int orderId)
	{
		return orderProductRepository.getByOrderProductOrderId(orderId);
	}

	@Override
	public void addProductOrder(OrderProducts orderProducts) {
		orderProductRepository.save(orderProducts);
	}

	@Override
	public void addAllOrders(List<OrderProducts> orders) {
		orderProductRepository.saveAll(orders);
		for(int i=0; i<orders.size(); i++)
		{
			updateStockProduct(orders.get(i));
			deleteFromShoppingCart(orders.get(i).getOrderProduct().getUsers().getUsersId(), orders.get(i).getProductOrder().getProductId());
		}
	}
	
	public void updateStockProduct(OrderProducts orderToChange) {
		Product changeProduct=productRepository.getByproductId(orderToChange.getProductOrder().getProductId());
		changeProduct.setQuantity(changeProduct.getQuantity()-orderToChange.getQuantity());
		productRepository.save(changeProduct);
	}
	
	public void deleteFromShoppingCart(int userId, int productId)
	{
		ShoppingCart shop=shoppingCartRepository.getByUserUsersIdAndProductProductId(userId, productId);
		shoppingCartRepository.delete(shop);
	}

	@Override
	public List<OrderProducts> getAllOrderProducts() {	
		return (List<OrderProducts>) orderProductRepository.findAll();
	}
}
