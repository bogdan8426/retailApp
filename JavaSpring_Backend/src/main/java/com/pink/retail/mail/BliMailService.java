package com.pink.retail.mail;

import java.util.List;

import javax.mail.MessagingException;

import com.pink.retail.orderProducts.OrderProducts;
import com.pink.retail.users.Users;

import freemarker.template.Template;

public interface BliMailService {
	public void sendMessage(Mail mail, Template template) throws MessagingException ;
	public void sendOrderMail(List<OrderProducts> orderProducts) ;
	public void sendSuggestionsMail();
	public void sendRegistrationMail(Users user);
}
