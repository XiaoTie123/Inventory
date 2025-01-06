package com.applewear.crm.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.applewear.crm.util.common.Result;

import com.applewear.crm.dto.admin.AdminDTO;
import com.applewear.crm.dto.order.OrderSearchDTO;
import com.applewear.crm.service.admin.AdminService;
import com.applewear.crm.service.order.OrderService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private OrderService orderService;
	
	private final Logger LOG = LogManager.getLogger();

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginDto", new AdminDTO());
		return "login";
	}

	@PostMapping("/login.html")
	public String loginPost(@ModelAttribute("loginDto") AdminDTO adminDTO, Model model, HttpServletRequest httpRequest,
			BindingResult bindResult) {
		validateLogin(adminDTO, bindResult);
		if (bindResult.hasErrors()) {
			model.addAttribute("loginDto", new AdminDTO());
			return "login";
		}
		Result<AdminDTO> result = adminService.login(adminDTO);
		if (!result.isSuccess()) {
			model.addAttribute(CommonConstants.FORM_MSG_KEY, CommonConstants.MSG_PREFIX_FAILED + result.getMessage());
			model.addAttribute("loginDto", adminDTO);
			return "login";
		}
		OrderSearchDTO searchDTO = new OrderSearchDTO();
		searchDTO.setPaymentPending(true);
		Long orderCount = orderService.searchOrderCountNoti(searchDTO);
		httpRequest.getSession().setAttribute(CommonConstants.LOGIN_SESSION_KEY, result.getData());
		httpRequest.getSession().setAttribute("count", orderCount);
		return "redirect:/home.html";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest httpRequest) {
		httpRequest.getSession().setAttribute(CommonConstants.LOGIN_SESSION_KEY, null);
		return "redirect:/login.html";
	}

	private void validateLogin(AdminDTO adminDto, BindingResult result) {
		if (!CommonUtil.validString(adminDto.getLoginName())) {
			result.rejectValue("loginName", null, "Login id is required.");
		}
		if (!CommonUtil.validString(adminDto.getPassword())) {
			result.rejectValue("password", null, "Password is required.");
		}
	}

}
