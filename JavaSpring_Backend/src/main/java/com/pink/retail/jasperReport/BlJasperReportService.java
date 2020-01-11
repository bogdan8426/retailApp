package com.pink.retail.jasperReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.pink.retail.orderProducts.OrderProducts;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

@Service
public class BlJasperReportService implements BliJasperReportService{

	@Override
	public void generateJasperReportOrder(List<OrderProducts> orderProductsList) {
		
	JasperPrint jasperPrint = null;
	
	   Map<String, Object> parameters = new HashMap<>();
	   parameters.put("orderID", orderProductsList.get(0).getOrderProduct().getOrderId());
	   parameters.put("totalPrice", orderProductsList.get(0).getOrderProduct().getTotalPrice());
	   parameters.put("numberCodebar", (int)((Math.random() * 9000000)+1000000));
	   try {
		   Connection conn=getDataSource().getConnection();
           JasperCompileManager.compileReportToFile("src/main/resources/jasper_report_template.jrxml", "src/main/resources/jasper_report_template.jasper");
           jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper_report_template.jasper", parameters,conn);
           JasperViewer jasperViewer = new JasperViewer(jasperPrint);
           jasperViewer.setVisible(true);
           JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/PDF/Order.pdf");
       } catch (JRException | SQLException ex) {
           ex.printStackTrace();
       }
	}
	
	@Bean
	 public DataSource getDataSource() {
	  MysqlDataSource dataSource = new MysqlDataSource();
	  // Set dataSource Properties
	  dataSource.setServerName("localhost");
	  dataSource.setPortNumber(3306);
	  dataSource.setDatabaseName("ecommerce");
	  dataSource.setUser("root");
	  dataSource.setPassword("admin");
	  return dataSource;
	 }
	
}
