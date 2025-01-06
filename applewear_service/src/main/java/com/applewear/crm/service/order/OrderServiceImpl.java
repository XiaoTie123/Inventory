package com.applewear.crm.service.order;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.order.OrderDao;
import com.applewear.crm.dao.order_item.OrderItemDao;
import com.applewear.crm.dao.payment.PaymentDao;
import com.applewear.crm.dto.customer.CustomerBalanceDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.dto.order_item.OrderItemDTO;
import com.applewear.crm.dto.sale_report.SaleReportDTO;
import com.applewear.crm.dto.sale_report.SaleReportSearchDTO;
import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.entity.customer.Customer;
import com.applewear.crm.entity.order.Order;
import com.applewear.crm.entity.order_item.OrderItem;
import com.applewear.crm.entity.product.Product;
import com.applewear.crm.service.order_item.OrderItemService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.ImageConstant;
import com.applewear.crm.util.enums.OrderStatus;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public List<OrderDTO> searchOrders(OrderSearchDTO searchDTO) {

		List<OrderDTO> orderList = orderDao.searchOrders(searchDTO);

		if (CommonUtil.validList(orderList)) {

			AtomicInteger num = new AtomicInteger(searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1);

			orderList.forEach(orderDTO -> {
				orderDTO.setTotalAmountText(CommonUtil.formatNumber(orderDTO.getTotalAmount()));
				orderDTO.setTotalPaidText(CommonUtil.formatNumber(orderDTO.getTotalPaid()));
				orderDTO.setDiscountText(CommonUtil.formatNumber(orderDTO.getDiscount()));
				orderDTO.setOrderStatusDesc(OrderStatus.getDescriptionByCode(orderDTO.getOrderStatus()));
				orderDTO.setRemainingAmountText(CommonUtil.formatNumber(orderDTO.getRemainingAmount()));
				orderDTO.setNum(num.getAndIncrement());
			});

		}

		return orderList;
	}

	@Override
	public Long searchOrderCount(OrderSearchDTO searchDTO) {

		return orderDao.searchOrderCount(searchDTO);
	}

	@Override
	public Long searchOrderCountNoti(OrderSearchDTO searchDTO) {

		return orderDao.searchOrderCountNoti(searchDTO);
	}

	@Override
	public List<SaleReportDTO> searchMonthlyReport(SaleReportSearchDTO searchDTO) {

		List<SaleReportDTO> saleReportList = orderDao.searchMonthlyReport(searchDTO);

		if (CommonUtil.validList(saleReportList)) {
			for (SaleReportDTO saleReportDTO : saleReportList) {
				saleReportDTO.setTotalAmountDesc(CommonUtil.formatNumber(saleReportDTO.getTotalAmount()));
				saleReportDTO.setTotalPaidDesc(CommonUtil.formatNumber(saleReportDTO.getTotalPaid()));
			}
		}

		return saleReportList;
	}

	@Override
	public Long searchMonthlyReportCount(SaleReportSearchDTO searchDTO) {

		return orderDao.searchMonthlyReportCount(searchDTO);
	}

	@Override
	public List<SaleReportDTO> searchYearlyReport(SaleReportSearchDTO searchDTO) {

		List<SaleReportDTO> saleReportList = orderDao.searchYearlyReport(searchDTO);

		if (CommonUtil.validList(saleReportList)) {
			for (SaleReportDTO saleReportDTO : saleReportList) {
				saleReportDTO.setTotalAmountDesc(CommonUtil.formatNumber(saleReportDTO.getTotalAmount()));
				saleReportDTO.setTotalPaidDesc(CommonUtil.formatNumber(saleReportDTO.getTotalPaid()));
			}
		}

		return saleReportList;

	}

	@Override
	public Long searchYearlyReportCount(SaleReportSearchDTO searchDTO) {

		return orderDao.searchYearlyReportCount(searchDTO);
	}

	@Override
	public List<SaleReportDTO> searchDailyReport(SaleReportSearchDTO searchDTO) {

		List<SaleReportDTO> saleReportList = orderDao.searchDailySaleReport(searchDTO);

		if (CommonUtil.validList(saleReportList)) {
			for (SaleReportDTO saleReportDTO : saleReportList) {
				saleReportDTO.setSaleDate(
						CommonUtil.dateToString(CommonConstants.STD_DATE_FORMAT, saleReportDTO.getSaleRawDate()));
				saleReportDTO.setTotalAmountDesc(CommonUtil.formatNumber(saleReportDTO.getTotalAmount()));
				saleReportDTO.setTotalPaidDesc(CommonUtil.formatNumber(saleReportDTO.getTotalPaid()));
			}
		}

		return saleReportList;
	}

	@Override
	public Long searchDailyReportCount(SaleReportSearchDTO searchDTO) {

		return orderDao.searchDailySaleReportCount(searchDTO);
	}

	@Override
	public CustomerBalanceDTO getCustomerBalance(Long customerId) {

		CustomerBalanceDTO customerBalanceDTO = orderDao.getCustomerBalance(customerId);

		if (customerBalanceDTO != null) {
			customerBalanceDTO.setTotalAmountText(CommonUtil.formatNumber(customerBalanceDTO.getTotalAmount()));
			customerBalanceDTO.setTotalPaidAmountText(CommonUtil.formatNumber(customerBalanceDTO.getTotalPaidAmount()));
			customerBalanceDTO
					.setTotalRemainingAmountText(CommonUtil.formatNumber(customerBalanceDTO.getTotalRemainingAmount()));
		}

		return customerBalanceDTO;
	}

	@Override
	public CustomerBalanceDTO getCustomerBalanceTotal() {

		CustomerBalanceDTO customerBalanceDTO = orderDao.getCustomerBalanceTotal();

		if (customerBalanceDTO != null) {
			customerBalanceDTO.setTotalAmountText(CommonUtil.formatNumber(customerBalanceDTO.getTotalAmount()));
			customerBalanceDTO.setTotalPaidAmountText(CommonUtil.formatNumber(customerBalanceDTO.getTotalPaidAmount()));
			customerBalanceDTO
					.setTotalRemainingAmountText(CommonUtil.formatNumber(customerBalanceDTO.getTotalRemainingAmount()));
		}

		return customerBalanceDTO;
	}

	@Override
	public OrderDTO getOrderById(Long orderId) {

		if (!CommonUtil.validLong(orderId)) {
			return new OrderDTO();
		}

		OrderDTO orderDTO = orderDao.getOrderById(orderId);

		if (orderDTO != null) {

			orderDTO.setTotalAmountText(CommonUtil.formatNumber(orderDTO.getTotalAmount()));

			orderDTO.setTotalPaidText(CommonUtil.formatNumber(orderDTO.getTotalPaid()));

			orderDTO.setRemainingAmountText(CommonUtil.formatNumber(orderDTO.getRemainingAmount()));

			List<OrderItemDTO> orderItemList = orderItemService.getOrderItemListByOrderId(orderId);

			orderDTO.setItemList(orderItemList);

		}

		return orderDTO;
	}

	@Override
	public Long manageOrder(OrderDTO orderDTO) {

		Order order = new Order();

		boolean isNew = false;

		if (CommonUtil.validLong(orderDTO.getOrderId())) {
			order = orderDao.get(orderDTO.getOrderId());
			order.setUpdatedTime(new Date());
		} else {

			isNew = true;
			order.setOrder_ref("");
			order.setCreatedTime(new Date());
			order.setUpdatedTime(new Date());
			order.setPayment(0);
			order.setTotalPaid(BigDecimal.ZERO);
			order.setTotal(BigDecimal.ZERO);
			order.setOrderDate(new Date());

			if (CommonUtil.validLong(orderDTO.getSubmittedById())) {
				Admin admin = new Admin();
				admin.setId(orderDTO.getSubmittedById());
				order.setAdmin(admin);
			}

		}

		if (CommonUtil.validLong(orderDTO.getCustomerId())) {
			Customer customer = new Customer();
			customer.setId(orderDTO.getCustomerId());
			order.setCustomer(customer);
		}

		order.setOrderStatus(orderDTO.getOrderStatus());

		order.setDiscount(BigDecimal.ZERO);

		orderDao.saveOrUpdate(order);

		boolean deleteResult = orderItemDao.deleteOrderItemsByOrderId(order.getId());

		System.out.println(deleteResult);

		if (CommonUtil.validList(orderDTO.getItemList())) {

			BigDecimal orderTotalAmount = BigDecimal.ZERO;

			for (OrderItemDTO orderItemDTO : orderDTO.getItemList()) {

				OrderItem orderItem = new OrderItem();
				orderItem.setCreatedTime(new Date());
				orderItem.setUpdatedTime(new Date());

				orderItem.setOrder(order);

				if (CommonUtil.validLong(orderItemDTO.getProductId())) {
					Product product = new Product();
					product.setId(orderItemDTO.getProductId());
					orderItem.setProduct(product);
				}

				orderItem
						.setDiscount(orderItemDTO.getDiscount() != null ? orderItemDTO.getDiscount() : BigDecimal.ZERO);

				orderItem.setPack(orderItemDTO.getPack());

				orderItem.setQuantityPerPack(orderItemDTO.getQuantityPerPack());

				orderItem.setQuantity(orderItemDTO.getQuantity());

				orderItem.setSellingPrice(orderItemDTO.getSellingPrice());

				BigDecimal totalAmount = orderItemDTO.getSellingPrice()
						.multiply(new BigDecimal(orderItemDTO.getQuantity()));

				orderItem.setTotal(totalAmount);

				orderTotalAmount = orderTotalAmount.add(totalAmount);

				orderItemDao.saveOrUpdate(orderItem);
			}

			order.setTotal(orderTotalAmount);

			if (isNew) {
				order.setOrder_ref("ORD-" + order.getId());
			}

			orderDao.saveOrUpdate(order);

		}

		return order != null && CommonUtil.validLong(order.getId()) ? order.getId() : null;
	}

	@Override
	public OrderDTO getOrderTotalAmountByCustomerId(Long customerId) {

		OrderDTO orderDTO = orderDao.getOrderTotalAmountByCustomerId(customerId);

		if (orderDTO != null) {
			orderDTO.setTotalAmountText(CommonUtil.formatNumber(orderDTO.getTotalAmount()));
			orderDTO.setTotalPaidText(CommonUtil.formatNumber(orderDTO.getTotalPaid()));
			return orderDTO;
		}

		return null;
	}

	@Override
	public boolean deleteOrderById(Long orderId) {

		if (!CommonUtil.validLong(orderId)) {
			return false;
		}

		Order entity = orderDao.get(orderId);

		if (entity != null) {

			orderItemDao.deleteOrderItemsByOrderId(orderId);

			paymentDao.deletePaymentByOrderId(orderId);

			orderDao.delete(entity);

			return true;

		}

		return false;
	}

	@Override
	public List<OrderDTO> searchOrdersNoti(OrderSearchDTO searchDTO) {

		List<OrderDTO> orderList = orderDao.searchOrdersNoti(searchDTO);

		if (CommonUtil.validList(orderList)) {

			AtomicInteger num = new AtomicInteger(searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1);

			orderList.forEach(orderDTO -> {
				orderDTO.setTotalAmountText(CommonUtil.formatNumber(orderDTO.getTotalAmount()));
				orderDTO.setTotalPaidText(CommonUtil.formatNumber(orderDTO.getTotalPaid()));
				orderDTO.setDiscountText(CommonUtil.formatNumber(orderDTO.getDiscount()));
				orderDTO.setOrderStatusDesc(OrderStatus.getDescriptionByCode(orderDTO.getOrderStatus()));
				orderDTO.setRemainingAmountText(CommonUtil.formatNumber(orderDTO.getRemainingAmount()));
				orderDTO.setNum(num.getAndIncrement());
			});

		}

		return orderList;
	}

	@Override
	public String exportOrderInvoice(Long orderId, HttpServletRequest httpRequest) {

		if (!CommonUtil.validLong(orderId)) {
			return "";
		}

		String realPath = httpRequest.getServletContext().getRealPath("/");

		OrderDTO orderDTO = getOrderById(orderId);

		if (orderDTO != null && CommonUtil.validLong(orderDTO.getOrderId())) {

			HashMap<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("companyAddress", "No. (26) , Thida Street , Kyimyindine Tsp, Yangon.");
			parameter.put("companyPhone", "Phone No: 09403337250 , 095065483, 09771228599 , 09780000093");
			parameter.put("orderNo", orderDTO.getOrderRef());
			parameter.put("orderDate", orderDTO.getOrderDate());
			parameter.put("customerName", CommonUtil.UnicodeToZawgyi(orderDTO.getCustomerName()));
			parameter.put("customerAddress", CommonUtil.UnicodeToZawgyi(orderDTO.getCustomerAddress()));
			parameter.put("customerPhone", CommonUtil.UnicodeToZawgyi(orderDTO.getCustomerPhone()));
			parameter.put("orderTotalAmount", orderDTO.getTotalAmountText());

			String logoPath = realPath + "/resources/images/applewear_logo.png";

			parameter.put("logo", logoPath);

			String reportPath = realPath + "/WEB-INF/reports/order-invoice.jrxml";

			try {

				JasperReport report = JasperCompileManager.compileReport(reportPath);

				JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(orderDTO.getItemList());

				JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameter, datasource);

				String fileName = "invoice_" + System.currentTimeMillis() + ".pdf";

				File dir = new File(ImageConstant.IMAGE_BASE_DIR + ImageConstant.TEMP_DOWNLOAD);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				try {

					FileUtils.cleanDirectory(dir);

				} catch (IOException e) {

				}

				JasperExportManager.exportReportToPdfFile(jasperPrint,
						ImageConstant.IMAGE_BASE_DIR + ImageConstant.TEMP_DOWNLOAD + fileName);

				return fileName;

			} catch (Exception e) {

			}

		}

		return null;
	}

}
