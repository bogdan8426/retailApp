package com.pink.retail.orderProducts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pink.retail.jasperReport.BliJasperReportService;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductsController {
	
	@Autowired
	BliOrderProductsService orderProductsService;
	
	@Autowired
	BliJasperReportService jasperReportService;

	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	List<OrderProducts> getByOrderId(@PathVariable int orderId)
	{
		return orderProductsService.getByOrderId(orderId);
	}
	
	@RequestMapping(value="/addOrder",method=RequestMethod.PUT)
	void addOrder(@RequestBody OrderProducts orderProducts)
	{
		orderProductsService.addProductOrder(orderProducts);
	}
	
	@RequestMapping(value="/addOrderProducts", method=RequestMethod.PUT)
	void addAllOrder(@RequestBody List<OrderProducts> orders)
	{
		orderProductsService.addAllOrders(orders);
	}
	
	@RequestMapping(value="/getJasperReport",method=RequestMethod.PUT)
	void getJasperReport(@RequestBody List<OrderProducts> orders)
	{
		jasperReportService.generateJasperReportOrder(orders);
	}
}
