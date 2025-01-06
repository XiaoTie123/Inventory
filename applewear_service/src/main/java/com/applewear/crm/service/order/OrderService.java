package com.applewear.crm.service.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.applewear.crm.dto.customer.CustomerBalanceDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.dto.sale_report.SaleReportDTO;
import com.applewear.crm.dto.sale_report.SaleReportSearchDTO;

public interface OrderService {

	List<OrderDTO> searchOrders(OrderSearchDTO searchDTO);

	List<OrderDTO> searchOrdersNoti(OrderSearchDTO searchDTO);

	Long searchOrderCount(OrderSearchDTO searchDTO);

	Long searchOrderCountNoti(OrderSearchDTO searchDTO);

	List<SaleReportDTO> searchMonthlyReport(SaleReportSearchDTO searchDTO);

	Long searchMonthlyReportCount(SaleReportSearchDTO searchDTO);

	List<SaleReportDTO> searchYearlyReport(SaleReportSearchDTO searchDTO);

	Long searchYearlyReportCount(SaleReportSearchDTO searchDTO);

	List<SaleReportDTO> searchDailyReport(SaleReportSearchDTO searchDTO);

	Long searchDailyReportCount(SaleReportSearchDTO searchDTO);

	CustomerBalanceDTO getCustomerBalance(Long customerId);

	OrderDTO getOrderById(Long orderId);

	Long manageOrder(OrderDTO orderDTO);

	OrderDTO getOrderTotalAmountByCustomerId(Long customerId);

	boolean deleteOrderById(Long orderId);

	CustomerBalanceDTO getCustomerBalanceTotal();

	String exportOrderInvoice(Long orderId, HttpServletRequest httpRequest);

}
