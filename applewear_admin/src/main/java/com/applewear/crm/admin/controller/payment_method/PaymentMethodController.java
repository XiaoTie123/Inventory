package com.applewear.crm.admin.controller.payment_method;

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

import com.applewear.crm.dto.payment_method.PaymentMethodDTO;
import com.applewear.crm.service.payment_method.PaymentMethodService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.google.gson.Gson;

@Controller
public class PaymentMethodController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private PaymentMethodService paymentMethodService;

	@GetMapping("/payment_method_setup")
	public String paymentMethodSetup(Model model, @RequestParam(name = "id", required = false) Long paymentMethodId) {

		PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();

		if (CommonUtil.validLong(paymentMethodId)) {
			paymentMethodDTO = paymentMethodService.getPaymentMethodById(paymentMethodId);
		}

		model.addAttribute("paymentMethodDTO", paymentMethodDTO);
		model.addAttribute("paymentMethodList", paymentMethodService.getAllPaymentMethods());
		return "payment_method_setup";
	}

	@PostMapping("/payment_method_setup")
	public String paymentMethodSetup(@ModelAttribute("paymentMethodDTO") PaymentMethodDTO paymentMethodDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			validatePaymentMethod(result, paymentMethodDTO);

			if (!result.hasErrors()) {

				Long paymentMethodId = paymentMethodService.managePaymentMethod(paymentMethodDTO);

				if (CommonUtil.validLong(paymentMethodId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Payment method is saved successfully.");
					return "redirect:/payment_method_setup.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Payment method saving failed.");
				}

			} else {

				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Payment method saving failed.");

			}

		} catch (Exception e) {
			LOG.error("Exception occurred while saving payment method => exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("paymentMethodDTO", paymentMethodDTO);
		model.addAttribute("paymentMethodList", paymentMethodService.getAllPaymentMethods());
		return "payment_method_setup";
	}

	@PostMapping("/deletePaymentMethodById.json")
	@ResponseBody
	public String deletePaymentMethodById(@RequestBody Long id) {

		String result = "Unable to delete payment method.";

		try {

			boolean deleteResult = paymentMethodService.deletePaymentById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "Unable to delete payment method because it is already in use.";
			LOG.error("Exception occurred while deleteing payment method by id => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return new Gson().toJson(result);

	}

	private void validatePaymentMethod(BindingResult result, PaymentMethodDTO paymentMethodDTO) {

		if (!CommonUtil.validString(paymentMethodDTO.getName())) {
			result.rejectValue("name", null, "Name is required.");
		}

	}

}
