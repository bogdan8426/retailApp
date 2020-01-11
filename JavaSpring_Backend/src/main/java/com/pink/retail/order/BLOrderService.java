package com.pink.retail.order;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pink.retail.util.Utility;

@Service
public class BLOrderService implements BLIOrderService {

	public static final Logger LOGGER=Logger.getLogger(BLOrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> findAll() {
		return (List<Order>) orderRepository.findAll();
	}
	
	public Order findById(int orderId) {
		return orderRepository.getByorderId(orderId);		
	}

	public Order save(Order data) {
		return orderRepository.save(data);
		
	}

	public Order update(Order data){
		if(orderRepository.existsById(data.getOrderId())==true) {
			return orderRepository.save(data);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
			return null;
		}		
	}
	public void removeOne(int orderId) {
		if(orderRepository.existsById(orderId)) {
			orderRepository.deleteById(orderId);
		}else {
			LOGGER.info(Utility.ERROR_NOT_FOUND_ID);
		}		
	}
	
	public void remove() {
		orderRepository.deleteAll();
	}

}
