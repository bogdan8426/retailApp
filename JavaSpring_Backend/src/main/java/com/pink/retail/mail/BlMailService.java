package com.pink.retail.mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.pink.retail.jasperReport.BliJasperReportService;
import com.pink.retail.order.BLIOrderService;
import com.pink.retail.order.Order;
import com.pink.retail.orderProducts.BliOrderProductsService;
import com.pink.retail.orderProducts.OrderProducts;
import com.pink.retail.product.BLIProductService;
import com.pink.retail.product.Product;
import com.pink.retail.users.BliUsersService;
import com.pink.retail.users.Users;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class BlMailService implements BliMailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	private BliUsersService userService;

	@Autowired
	private BLIProductService productService;
	
	@Autowired
	private BLIOrderService orderService;
	@Autowired
	private BliOrderProductsService orderProductService;
	
	@Autowired
	private BliJasperReportService jasperReportService;
	
	@Autowired
	private Configuration freeMarkerConfig;

	public void sendMessage(Mail mail, Template t) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper messageHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
		String html=null;
		freeMarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
	        
	    try {
			 html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	    
		messageHelper.setFrom(mail.getFrom());
		messageHelper.setTo(mail.getTo());
		messageHelper.setSubject(mail.getSubject());
		messageHelper.setText(html, true);
		FileSystemResource file = new FileSystemResource("src/main/resources/PDF/Order.pdf");
		messageHelper.addAttachment("Order.pdf", file);
		mailSender.send(message);
	}

	private Map<String, Object> setTemplateOrderModel(String userName,List<OrderProducts> orderProducts, double priceTotal) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", userName);
		model.put("orderProducts",orderProducts);
		model.put("priceTotal",priceTotal);
		return model;
	}
	private Map<String, Object> setTemplateSuggestionsModel(String userName,List<Product>productSuggestions) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", userName);
		model.put("productSuggestions",productSuggestions);

		return model;
	}
	
	private Map<String, Object> setTemplateRegistrationModel(String userName) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", userName);

		return model;
	}

	public void sendOrderMail(List<OrderProducts> orderProducts) {
		List<String> productNames = new ArrayList<String>();
		List<Integer> quantities = new ArrayList<Integer>();

		int userId = orderProducts.get(0).getOrderProduct().getUsers().getUsersId();
		String userEmail = userService.getUserById(userId).getEmail();
		String userName = userService.getUserById(userId).getFirstName();
		double priceTotal=0;

		for (OrderProducts op : orderProducts) {
			productNames.add(op.getProductOrder().getName());
			quantities.add(op.getQuantity());
			priceTotal+=op.getQuantity()*op.getProductOrder().getPrice();
		}
		
		Template t=null;
		try {
			t = freeMarkerConfig.getTemplate("orderEmailTemplate.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mail mail = new Mail();
		mail.setFrom("restaurant.mail26@gmail.com");
		mail.setTo(userEmail);
		mail.setSubject("Successful order from Ecommerce App");
	
		
		Map<String, Object> model = setTemplateOrderModel(userName,orderProducts,priceTotal);
		mail.setModel(model);
		jasperReportService.generateJasperReportOrder(orderProducts);
		try {
			sendMessage(mail, t);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendSuggestionsMail() {
		
		List<Order> allOrders= orderService.findAll();
		List<Order> ordersToSendMail=new ArrayList<Order>();
		List<OrderProducts> ordersProductsByOrderId= new ArrayList<OrderProducts>();

		
		for (Order order : allOrders) {
			if(order.getFlag()!=true) {
				ordersToSendMail.add(order);
			}
		}
		
		for (Order orderToSendMail : ordersToSendMail) {
			ordersProductsByOrderId=orderProductService.getByOrderId(orderToSendMail.getOrderId());
			
			List<Integer> categoriesId = new ArrayList<Integer>();
			List<Integer> productsId = new ArrayList<Integer>();
			List<Product> productsToSuggest = new ArrayList<Product>();
			List<Product> productsToSuggestCrop = new ArrayList<Product>();
			boolean productOrderedFound = false;
			
			int userId = ordersProductsByOrderId.get(0).getOrderProduct().getUsers().getUsersId();
			String userEmail = userService.getUserById(userId).getEmail();
			String userName = userService.getUserById(userId).getFirstName();

			for (OrderProducts op : ordersProductsByOrderId) {
				if(!categoriesId.contains(op.getProductOrder().getCategory().getCategoryId())) {
				categoriesId.add(op.getProductOrder().getCategory().getCategoryId());}
				productsId.add(op.getProductOrder().getProductId());
			}

			for (int i = 0; i < categoriesId.size(); i++) {
				List<Product> productSuggestions = productService.findByCategoryId(categoriesId.get(i));
				for (Product product : productSuggestions) {
					productOrderedFound = false;
					for (int j = 0; j < productsId.size(); j++) {
						if (product.getProductId() == productsId.get(j)) {
							productOrderedFound = true;
						}
					}
					if (productOrderedFound == false) {		
							productsToSuggest.add(product);
					
					}
				}
				
			}
			System.out.println(productsToSuggest);
			Map<String, Object> model;
			if(productsToSuggest.size()>5) {
				for (int i=0;i<5;i++) {
					productsToSuggestCrop.add(productsToSuggest.get(i));
				}
				model = setTemplateSuggestionsModel(userName,productsToSuggestCrop);
			}else {
				model = setTemplateSuggestionsModel(userName,productsToSuggest);
			}
			
			
			Template t=null;
			try {
				t = freeMarkerConfig.getTemplate("suggestionsEmailTemplate.ftl");
			} catch (IOException e) {
				e.printStackTrace();
			}

			Mail mail = new Mail();
			mail.setFrom("restaurant.mail26@gmail.com");
			mail.setTo(userEmail);
			mail.setSubject("Suggestions from Ecommerce App");
		
			
			
			mail.setModel(model);
			try {
				sendMessageSuggestion(mail, t);
				orderToSendMail.setFlag(true);
				orderService.update(orderToSendMail);
			} catch (MessagingException e) {
				e.printStackTrace();
			}						
		}		
	}

	@Override
	public void sendRegistrationMail(Users user) {
		System.out.println(user);
		String userEmail = user.getEmail();
		String userName = user.getFirstName();
		
		Template t=null;
		try {
			t = freeMarkerConfig.getTemplate("registerEmailTemplate.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mail mail = new Mail();
		mail.setFrom("restaurant.mail26@gmail.com");
		mail.setTo(userEmail);
		mail.setSubject("Successful registration from Ecommerce App");
	
		
		Map<String, Object> model = setTemplateRegistrationModel(userName);
		mail.setModel(model);
		try {
			sendMessageSuggestion(mail, t);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageSuggestion(Mail mail, Template t) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper messageHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
		String html=null;
		freeMarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
	        
	    try {
			 html = FreeMarkerTemplateUtils.processTemplateIntoString(t, mail.getModel());
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
	    
		messageHelper.setFrom(mail.getFrom());
		messageHelper.setTo(mail.getTo());
		messageHelper.setSubject(mail.getSubject());
		messageHelper.setText(html, true);
		mailSender.send(message);
	}

	@Override
	public void sendChangePasswordEmail(Users userUpdate) {
		String userEmail = userUpdate.getEmail();
		String userName = userUpdate.getFirstName()+ " " + userUpdate.getLastName();
		Base64 base64 = new Base64();
		String encodeUserEmail = new String(base64.encode(userEmail.getBytes()));
		Template t=null;
		try {
			t = freeMarkerConfig.getTemplate("changePasswordTemplate.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mail mail = new Mail();
		mail.setFrom("restaurant.mail26@gmail.com");
		mail.setTo(userEmail);
		mail.setSubject("Change passsword for your account");
	
		
		Map<String, Object> model = setTemplateRegistrationModelRequestPassword(encodeUserEmail, userName);
		mail.setModel(model);
		try {
			sendMessageSuggestion(mail, t);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}	
	
	private Map<String, Object> setTemplateRegistrationModelRequestPassword(String email, String userName) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", userName);
		model.put("userEmail", email);
		return model;
	}
		
}