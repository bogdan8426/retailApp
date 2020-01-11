package com.pink.retail.order;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private BLOrderService orderService;
	
	@RequestMapping(value = "/addOrder", method = RequestMethod.PUT)
	public Order addData(@RequestBody Order order) {
		return orderService.save(order);
	}

	@RequestMapping(value = "/allOrders", method = RequestMethod.GET)
	public List<Order> getData() {
		return orderService.findAll();
	}
	
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Order findById(@PathVariable @NotNull int orderId) {
        return orderService.findById(orderId);
	}
	
	@RequestMapping(value = "/remove/allOrders", method = RequestMethod.DELETE)
	public void remove() {
		orderService.remove();
	}
	
	@RequestMapping(value = "/remove/{orderId}", method = RequestMethod.DELETE)
	public void removeOne(@PathVariable @NotNull int orderId) {
		orderService.removeOne(orderId);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Order updateData(@RequestBody Order order) {
		return orderService.update(order);
	}
}
