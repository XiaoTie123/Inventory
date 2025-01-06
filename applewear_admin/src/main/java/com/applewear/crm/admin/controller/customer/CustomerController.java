package com.applewear.crm.admin.controller.customer;

import java.util.List;

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

import com.applewear.crm.dto.customer.CustomerDTO;
import com.applewear.crm.dto.customer.CustomerSearchDTO;
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.service.customer.CustomerService;
import com.applewear.crm.service.exception.OrderAlreadyExistException;
import com.applewear.crm.service.exception.PaymentAlreadyExistException;
import com.applewear.crm.service.order.OrderService;
import com.applewear.crm.service.rank.RankService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.applewear.crm.util.enums.RecordStatus;
import com.google.gson.Gson;

@Controller
public class CustomerController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RankService rankService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/customer-setup")
	public String customerSetup(Model model, @RequestParam(name = "id", required = false) Long customerId) {

		CustomerDTO customerDTO = new CustomerDTO();

		if (CommonUtil.validLong(customerId)) {
			customerDTO = customerService.getCustomerById(customerId);
		}

		commonModelForCustomerSetup(model);
		model.addAttribute("customerDTO", customerDTO);
		return "customer-setup";

	}

	@PostMapping("/customer-setup")
	public String customerSetup(@ModelAttribute("customerDTO") CustomerDTO customerDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			validateCustomerSetup(customerDTO, result);

			if (!result.hasErrors()) {

				Long customerId = customerService.manageCustomer(customerDTO);

				if (CommonUtil.validLong(customerId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Customer is saved successfully.");
					return "redirect:/customer-search.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Customer saving failed.");
				}

			} else {

				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Customer saving failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while saving customer => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		commonModelForCustomerSetup(model);
		model.addAttribute("customerDTO", customerDTO);
		return "customer-setup";

	}

	private void validateCustomerSetup(CustomerDTO customerDTO, BindingResult result) {

		if (!CommonUtil.validString(customerDTO.getName())) {
			result.rejectValue("name", null, "Name is required.");
		}

		if (!CommonUtil.validString(customerDTO.getMobile())) {
			result.rejectValue("mobile", null, "Mobile is required.");
		}

//		if (!CommonUtil.validString(customerDTO.getLoginId())) {
//			result.rejectValue("loginId", null, "Login Id is required.");
//		}


//		if (!CommonUtil.validInteger(customerDTO.getVisible())) {
//			result.rejectValue("visible", null, "Status is required.");
//		}

		if (!CommonUtil.validLong(customerDTO.getRankId())) {
			result.rejectValue("rankId", null, "Rank is required.");
		}

	}

	@GetMapping("/customer-search")
	public String customerSearch(Model model) {
		model.addAttribute("active", MenuConstants.CUSTOMER_SEARCH);
		return "customer-search";
	}

	@PostMapping("/customer-search.json")
	@ResponseBody
	public CustomerSearchDTO customerSearchDTO(@RequestBody CustomerSearchDTO searchDTO) {

		try {

			searchDTO.setData(customerService.searchCustomers(searchDTO));

			Long totalCount = customerService.searchCustomerCount(searchDTO);

			searchDTO.setRecordsFiltered(totalCount);

			searchDTO.setRecordsTotal(totalCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(
					"Exception occurred while searching customers => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/customer-history")
	public String customerHistory(Model model) {

		model.addAttribute("active", MenuConstants.CUSTOMER_HISTORY);
		model.addAttribute("customerList", customerService.getAllCustomers());
		return "customer-history";
	}

	@PostMapping("/customer-history-search.json")
	@ResponseBody
	public CustomerSearchDTO customerHistorySearch(@RequestBody CustomerSearchDTO searchDTO) {

		try {

			searchDTO.setData(customerService.searchCustomers(searchDTO));

			Long totalCount = customerService.searchCustomerCount(searchDTO);

			searchDTO.setRecordsFiltered(totalCount);

			searchDTO.setRecordsTotal(totalCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching customers history=> Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/customer-search-autocomplete.json")
	@ResponseBody
	public List<CustomerDTO> customerSearch(@RequestParam(name = "searchKey", required = false) String seachKey) {

		List<CustomerDTO> customerDTOList = customerService.searchCustomerAutoComplete(seachKey);

		return customerDTOList;
	}

	@GetMapping("/customer_invoice")
	public String customerInvoice(@RequestParam(name = "orderId") Long orderId, Model model) {

		OrderDTO orderDTO = orderService.getOrderById(orderId);

		model.addAttribute("orderDTO", orderDTO);
		return "customer_invoice";

	}

	@PostMapping("/deleteCustomerById.json")
	@ResponseBody
	public String deleteCustomerById(@RequestBody Long id) {

		String result = "Unable to delete customer.";

		try {

			boolean deleteResult = customerService.deleteCustomerById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (PaymentAlreadyExistException e) {
			result = e.getMessage();
		} catch (OrderAlreadyExistException e) {
			result = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while deleteing order by id => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return new Gson().toJson(result);

	}

	private void commonModelForCustomerSetup(Model model) {
		model.addAttribute("rankList", rankService.getAllRanks());
		model.addAttribute("statusList", RecordStatus.getAll());
		model.addAttribute("active", MenuConstants.CUSTOMER_SETUP);
	}

}
