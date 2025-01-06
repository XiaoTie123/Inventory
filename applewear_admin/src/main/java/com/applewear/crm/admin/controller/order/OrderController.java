package com.applewear.crm.admin.controller.order;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.mime.MimeUtility;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applewear.crm.dto.admin.AdminDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.service.admin.AdminService;
import com.applewear.crm.service.customer.CustomerService;
import com.applewear.crm.service.order.OrderService;
import com.applewear.crm.service.product.ProductService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.ImageConstant;
import com.applewear.crm.util.common.MenuConstants;
import com.applewear.crm.util.enums.OrderStatus;

@Controller
public class OrderController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/order-search")
	public String orderSearch(Model model) {
		model.addAttribute("active", MenuConstants.PENDING_ORDER_SEARCH);
		model.addAttribute("orderStatusList", OrderStatus.getAll());
		return "order-search";
	}

	@PostMapping("/order-search.json")
	@ResponseBody
	public OrderSearchDTO orderSearch(@RequestBody OrderSearchDTO searchDTO) {

		try {

			searchDTO.setProcessOnly(true);

			List<OrderDTO> orderList = orderService.searchOrders(searchDTO);
			Long orderCount = orderService.searchOrderCount(searchDTO);

			searchDTO.setData(orderList);
			searchDTO.setRecordsTotal(orderCount);
			searchDTO.setRecordsFiltered(orderCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching order => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/order-finish-search")
	public String orderFinishSearch(Model model) {
		model.addAttribute("active", MenuConstants.ORDER_FINISHED_SEARCH);
		return "order-finish-search";
	}

	@PostMapping("/order-finish-search.json")
	@ResponseBody
	public OrderSearchDTO orderFinishSearch(@RequestBody OrderSearchDTO searchDTO) {

		try {

			searchDTO.setCompleteOnly(true);

			List<OrderDTO> orderList = orderService.searchOrders(searchDTO);
			Long orderCount = orderService.searchOrderCount(searchDTO);

			searchDTO.setData(orderList);
			searchDTO.setRecordsTotal(orderCount);
			searchDTO.setRecordsFiltered(orderCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching order => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/customer-order-history")
	public String customerOrderHistory(Model model, @RequestParam(name = "customerId") Long customerId,
			HttpServletRequest httpRequest) {

		AdminDTO loginUser = (AdminDTO) httpRequest.getSession().getAttribute(CommonConstants.LOGIN_SESSION_KEY);

		model.addAttribute("customerId", customerId);
		model.addAttribute("customerBalanceDTO", orderService.getCustomerBalance(customerId));
		model.addAttribute("isEditPayment", adminService.isAdminRole(loginUser.getAdminId()));
		return "customer-order-history";
	}

	@GetMapping("/customer-order-list")
	public String customerOrderList(Model model, HttpServletRequest httpRequest) {

		AdminDTO loginUser = (AdminDTO) httpRequest.getSession().getAttribute(CommonConstants.LOGIN_SESSION_KEY);
		model.addAttribute("customerBalanceDTO", orderService.getCustomerBalanceTotal());
		model.addAttribute("isEditPayment", adminService.isAdminRole(loginUser.getAdminId()));
		return "customer-order-list";
	}

	@PostMapping("/payment-completed-orders.json")
	@ResponseBody
	public OrderSearchDTO paymentCompleteOrders(@RequestBody OrderSearchDTO searchDTO) {

		try {

			searchDTO.setPaymentCompleted(true);

			List<OrderDTO> orderList = orderService.searchOrders(searchDTO);
			Long orderCount = orderService.searchOrderCount(searchDTO);

			searchDTO.setData(orderList);
			searchDTO.setRecordsTotal(orderCount);
			searchDTO.setRecordsFiltered(orderCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching payment completed orders => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@PostMapping("/payment-pending-orders.json")
	@ResponseBody
	public OrderSearchDTO paymentPendingOrders(@RequestBody OrderSearchDTO searchDTO) {

		try {

			searchDTO.setPaymentPending(true);

			List<OrderDTO> orderList = orderService.searchOrders(searchDTO);
			Long orderCount = orderService.searchOrderCount(searchDTO);

			searchDTO.setData(orderList);
			searchDTO.setRecordsTotal(orderCount);
			searchDTO.setRecordsFiltered(orderCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching payment pending orders => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@PostMapping("/payment-pending-noti.json")
	@ResponseBody
	public OrderSearchDTO paymentPendingNoti(@RequestBody OrderSearchDTO searchDTO) {

		try {

			searchDTO.setPaymentPending(true);

			List<OrderDTO> orderList = orderService.searchOrdersNoti(searchDTO);
			Long orderCount = orderService.searchOrderCountNoti(searchDTO);

			searchDTO.setData(orderList);
			searchDTO.setRecordsTotal(orderCount);
			searchDTO.setRecordsFiltered(orderCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching payment pending noti => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/order-setup")
	public String orderSetup(Model model, @RequestParam(name = "id", required = false) Long orderId) {

		OrderDTO orderDTO = new OrderDTO();

		if (CommonUtil.validLong(orderId)) {
			orderDTO = orderService.getOrderById(orderId);
		}

		model.addAttribute("active", MenuConstants.ORDER_CREATE);
		model.addAttribute("orderStatusList", OrderStatus.getAll());
		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("customerDTOList", customerService.getAllCustomers());
		model.addAttribute("productList", productService.getAllProducts());
		return "order-setup";
	}

	@PostMapping("/order-setup")
	public String orderSetuup(@ModelAttribute("orderDTO") OrderDTO orderDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result, HttpServletRequest httpRequest) {

		model.addAttribute("active", MenuConstants.ORDER_CREATE);
		
		try {

			validateOrderSetup(orderDTO, result);

			if (!result.hasErrors()) {

				if (!CommonUtil.validList(orderDTO.getItemList())) {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Product list is required.");
				} else {

					AdminDTO adminDTO = (AdminDTO) httpRequest.getSession()
							.getAttribute(CommonConstants.LOGIN_SESSION_KEY);

					orderDTO.setSubmittedById(adminDTO.getAdminId());

					Long orderId = orderService.manageOrder(orderDTO);

					if (CommonUtil.validLong(orderId)) {
						redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
								CommonConstants.MSG_PREFIX_SUCCESS + "Order is saved successfully.");
						return "redirect:/order-setup.html";
					} else {
						model.addAttribute(CommonConstants.FORM_MSG_KEY,
								CommonConstants.MSG_PREFIX_FAILED + "Order saving failed.");
					}

				}

			} else {
				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Order saving failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while saving order => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("orderStatusList", OrderStatus.getAll());
		model.addAttribute("customerDTOList", customerService.getAllCustomers());
		model.addAttribute("productList", productService.getAllProducts());
		model.addAttribute("orderDTO", orderDTO);
		return "order-setup";
	}

	@GetMapping("/invoice")
	public String invoice(@RequestParam(name = "orderId") Long orderId, Model model) {

		OrderDTO orderDTO = orderService.getOrderById(orderId);

		model.addAttribute("orderDTO", orderDTO);
		return "invoice";

	}

	@PostMapping(value = "/invoice", params = "downloadInvoice")
	public void downloadInvoice(@ModelAttribute("orderDTO") OrderDTO orderDTO, HttpServletRequest httpRequest,
			HttpServletResponse response) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			String fileName = orderService.exportOrderInvoice(orderDTO.getOrderId(), httpRequest);

			String url = ImageConstant.IMAGE_BASE_DIR + ImageConstant.TEMP_DOWNLOAD + fileName;
			File file = new File(url);
			System.out.println("url..." + url);

			if (file.exists()) {
				System.out.println("file..." + file);
				InputStream in;
				try {

					in = new FileInputStream(file);
					String filename2 = "";
					String agent = httpRequest.getHeader("USER-AGENT");
					if (agent != null && agent.indexOf("MSIE") != -1) {

						filename2 = URLEncoder.encode(fileName, "UTF8");
						response.setContentType("application/x-download");
						response.setHeader("Content-Disposition", "attachment;filename=" + filename2);
					} else if (agent != null && agent.indexOf("Mozilla") != -1) {

						response.setCharacterEncoding("UTF-8");
						filename2 = encodeText(fileName, "UTF8");
						response.setContentType("application/force-download");
						response.addHeader("Content-Disposition", "attachment; filename=" + filename2);
					}

					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
					byte by[] = new byte[32768];
					int index = in.read(by, 0, 32768);
					while (index != -1) {
						out.write(by, 0, index);
						index = in.read(by, 0, 32768);
					}
					out.flush();
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("Error occur while downloading  pdf file......");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while downloading invoice => Exception => " + ExceptionUtils.getStackTrace(e));
		}

	}

	@PostMapping("/deleteOrderById.json")
	@ResponseBody
	public String deleteOrderById(@RequestBody Long orderId) {

		String result = "-1";

		try {

			boolean deleteResult = orderService.deleteOrderById(orderId);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while deleteing order by id => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return result;

	}

	public static String encodeText(String text, String charset) throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes(charset);
		String encoded = Base64.getEncoder().encodeToString(bytes);
		return "=?UTF-8?B?" + encoded + "?=";
	}

	private void validateOrderSetup(OrderDTO orderDTO, BindingResult result) {

		if (!CommonUtil.validLong(orderDTO.getCustomerId())) {
			result.rejectValue("customerId", null, "Customer is required.");
		}

	}

}
