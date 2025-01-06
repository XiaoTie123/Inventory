package com.applewear.crm.admin.controller.payment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.applewear.crm.dto.order.OrderDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.dto.payment.PaymentDTO;
import com.applewear.crm.dto.payment.PaymentSearchDTO;
import com.applewear.crm.service.customer.CustomerService;
import com.applewear.crm.service.order.OrderService;
import com.applewear.crm.service.payment.PaymentService;
import com.applewear.crm.service.payment_method.PaymentMethodService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;

@Controller
public class PaymentController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentMethodService paymentMethodService;

	@PostMapping("/customer-payment-history.json")
	@ResponseBody
	public PaymentSearchDTO searchPayment(@RequestBody PaymentSearchDTO searchDTO) {

		try {

			List<PaymentDTO> paymentDTOList = paymentService.searchPayments(searchDTO);

			Long totalCount = paymentService.searchPaymentCount(searchDTO);

			searchDTO.setData(paymentDTOList);

			searchDTO.setRecordsTotal(totalCount);

			searchDTO.setRecordsFiltered(totalCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while searching payments => exception => " + ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/customer-payment-setup")
	public String customerPaymentSetup(Model model, @RequestParam(name = "id", required = false) Long paymentId,
			@RequestParam(name = "orderId", required = false) Long orderId,
			@RequestParam(name = "customerId", required = false) Long customerId) {

		PaymentDTO paymentDTO = new PaymentDTO();

		if (CommonUtil.validLong(paymentId)) {
			paymentDTO = paymentService.getPaymentById(paymentId);
		}

		if (CommonUtil.validLong(customerId)) {
			CustomerDTO customerDTO = customerService.getCustomerById(customerId);
			paymentDTO.setCustomerDTO(customerDTO);
		}

		if (CommonUtil.validLong(orderId)) {
			OrderDTO orderDTO = orderService.getOrderById(orderId);
			paymentDTO.setOrderDTO(orderDTO);
		}

		commonModelForPaymentSetup(model);
		model.addAttribute("paymentDTO", paymentDTO);
		return "customer-payment-setup";

	}

	@PostMapping("/customer-payment-setup")
	public String customerPaymentSetup(@ModelAttribute("paymentDTO") PaymentDTO paymentDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result, HttpServletRequest httpRequest) {

		try {

			validatePaymentSetup(paymentDTO, result);

			if (!result.hasErrors()) {

				Long paymentId = paymentService.managePayment(paymentDTO);

				if (CommonUtil.validLong(paymentId)) {
					OrderSearchDTO searchDTO = new OrderSearchDTO();
					searchDTO.setPaymentPending(true);
					Long orderCount = orderService.searchOrderCountNoti(searchDTO);
					httpRequest.getSession().setAttribute("count", orderCount);
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Payment is saved successfully.");
					redirectAttributes.addAttribute("customerId", paymentDTO.getCustomerDTO().getCustomerId());
					return "redirect:/customer-order-history.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Payment saving failed.");
				}

			} else {

				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Payment saving failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception occurred while saving customer payment setup => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		commonModelForPaymentSetup(model);
		model.addAttribute("paymentDTO", paymentDTO);
		return "customer-payment-setup";
	}

	@GetMapping("/payment_invoice")
	public String paymentInvoice(@RequestParam(name = "customerId") Long customerId, Model model) {
		List<PaymentDTO> paymentList = paymentService.getPaymentByCustomerId(customerId);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("customerId", customerId);
		model.addAttribute("totalPaid", paymentService.getTotalPaidByCustomerId(customerId));
		model.addAttribute("customerDTO", customerService.getCustomerById(customerId));
		model.addAttribute("orderDTO", orderService.getOrderTotalAmountByCustomerId(customerId));
		return "payment_invoice";
	}

	private void commonModelForPaymentSetup(Model model) {
		model.addAttribute("paymentMethodList", paymentMethodService.getAllPaymentMethods());
		model.addAttribute("active", MenuConstants.PAYMENT_METHOD_SETUP);
	}

	private void validatePaymentSetup(PaymentDTO paymentDTO, BindingResult result) {

		if (!CommonUtil.validBigDecimal(paymentDTO.getTotalPaidAmount())) {
			result.rejectValue("totalPaidAmount", null, "Paid amount is required.");
		}
		
		if (!CommonUtil.validLong(paymentDTO.getPaymentMethodId())) {
			result.rejectValue("paymentMethodId", null, "Payment Method is required.");
		}

		if (paymentDTO.getCustomerDTO() == null || !CommonUtil.validLong(paymentDTO.getCustomerDTO().getCustomerId())) {
			result.rejectValue("customerDTO.customerId", null, "Customer is required.");
		}
	}

}
