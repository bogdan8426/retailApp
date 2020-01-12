package com.pink.retail.mail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pink.retail.orderProducts.OrderProducts;
import com.pink.retail.users.Users;

@RestController
@RequestMapping("/email")
public class MailController {
	
	@Autowired
	BliMailService mailService;
	
	@RequestMapping(value="/sendOrderMail", method=RequestMethod.PUT)
	void sendOrderMail(@RequestBody List<OrderProducts> orderProducts)
	{
		mailService.sendOrderMail(orderProducts);
	}
	@RequestMapping(value="/sendRegisterMail", method=RequestMethod.PUT)
	void sendRegisterMail(@RequestBody Users user)
	{
		mailService.sendRegistrationMail(user);
	}
	
	@RequestMapping(value="/sendRequestPassword", method=RequestMethod.PUT)
	void sendRequestPassword(@RequestBody Users userRequest)
	{
		mailService.sendChangePasswordEmail(userRequest);
	}
}
