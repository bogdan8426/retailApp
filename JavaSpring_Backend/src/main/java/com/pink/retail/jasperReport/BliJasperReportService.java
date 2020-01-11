package com.pink.retail.jasperReport;

import java.util.List;

import com.pink.retail.orderProducts.OrderProducts;


public interface BliJasperReportService {

	void generateJasperReportOrder(List<OrderProducts> orderProductsList);
}
