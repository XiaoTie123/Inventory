package com.applewear.crm.admin.controller.home;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.applewear.crm.dto.customer.CustomerBalanceDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.service.customer.CustomerService;
import com.applewear.crm.service.order.OrderService;
import com.applewear.crm.service.order_item.OrderItemService;
import com.applewear.crm.util.common.MenuConstants;

@Controller
public class HomeController {

	public final Logger LOG = LogManager.getLogger();

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/home")
	public String home(Model model) {

		model.addAttribute("topTenProducts", orderItemService.getTopTenProducts());
		model.addAttribute("topCreditCustomers", customerService.getTopCreditCustomers());
		model.addAttribute("topTenCustomers", customerService.getTopTenCustomers());
		model.addAttribute("active", MenuConstants.HOME);
		
		return "home";
	}

	@PostMapping("/order-incomplete-search.json")
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

}
