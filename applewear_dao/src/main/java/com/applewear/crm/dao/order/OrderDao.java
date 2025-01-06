package com.applewear.crm.dao.order;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.customer.CustomerBalanceDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.dto.sale_report.SaleReportDTO;
import com.applewear.crm.dto.sale_report.SaleReportSearchDTO;
import com.applewear.crm.entity.order.Order;

public interface OrderDao extends GenericDao<Order, Long> {

	List<OrderDTO> searchOrders(OrderSearchDTO searchDTO);

	List<OrderDTO> searchOrdersNoti(OrderSearchDTO searchDTO);
	
	Long searchOrderCount(OrderSearchDTO searchDTO);

	Long searchOrderCountNoti(OrderSearchDTO searchDTO);

	List<SaleReportDTO> searchMonthlyReport(SaleReportSearchDTO searchDTO);

	Long searchMonthlyReportCount(SaleReportSearchDTO searchDTO);

	List<SaleReportDTO> searchYearlyReport(SaleReportSearchDTO searchDTO);

	Long searchYearlyReportCount(SaleReportSearchDTO searchDTO);

	List<SaleReportDTO> searchDailySaleReport(SaleReportSearchDTO searchDTO);

	Long searchDailySaleReportCount(SaleReportSearchDTO searchDTO);

	CustomerBalanceDTO getCustomerBalance(Long customerId);

	CustomerBalanceDTO getCustomerBalanceTotal();

	OrderDTO getOrderById(Long orderId);

	OrderDTO getOrderTotalAmountByCustomerId(Long customerId);

	boolean isOrderExistByCustomerId(Long customerId);

}
