package com.applewear.crm.admin.controller.admin;

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

import com.applewear.crm.admin.controller.common.CommonController;
import com.applewear.crm.dto.admin.AdminDTO;
import com.applewear.crm.dto.admin.AdminSearchDTO;
import com.applewear.crm.service.admin.AdminService;
import com.applewear.crm.service.exception.LoginNameAlreadyExistException;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.applewear.crm.util.enums.RoleType;
import com.google.gson.Gson;

@Controller
public class AdminController extends CommonController {

	@Autowired
	private AdminService adminService;

	public final Logger logger = LogManager.getLogger();

	@GetMapping("/admin-setup")
	public String adminsetup(Model model, @RequestParam(name = "id", required = false) Long adminId) {

		AdminDTO adminDTO = new AdminDTO();

		if (CommonUtil.validLong(adminId)) {
			adminDTO = adminService.getAdminById(adminId);
		}
		model.addAttribute("roleList", RoleType.getAll());
		model.addAttribute("adminDTO", adminDTO);
		model.addAttribute("active", MenuConstants.USER_SETUP);
		return "admin-setup";

	}

	@PostMapping("/admin-setup")
	public String adminSetup(@ModelAttribute("adminDTO") AdminDTO adminDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result, HttpServletRequest req) {

		try {
			validateAdminSetup(adminDTO, result);
			if (!result.hasErrors()) {
				Long adminId = adminService.manageAdmin(adminDTO);
				if (CommonUtil.validLong(adminId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Admin is saved successfully.");
					return "redirect:/admin-list.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Admin saving failed.");
				}

			} else {

				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Admin saving failed.");
			}

		} catch (LoginNameAlreadyExistException e) {
			addModelFailMessage(e.getMessage(), model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while saving admin => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("roleList", RoleType.getAll());
		model.addAttribute("adminDTO", adminDTO);
		return "admin-setup";

	}

	private void validateAdminSetup(AdminDTO adminDTO, BindingResult result) {

		if (!CommonUtil.validString(adminDTO.getAdminName())) {
			result.rejectValue("adminName", null, "Admin Name is required.");
		}

		if (!CommonUtil.validString(adminDTO.getLoginName())) {
			result.rejectValue("loginName", null, "Login name is required.");
		}

		if (!CommonUtil.validString(adminDTO.getEmail())) {
			result.rejectValue("email", null, "Email is required.");
		}

		if (!CommonUtil.validInteger(adminDTO.getRole())) {
			result.rejectValue("role", null, "Role is required.");
		}

		if (!CommonUtil.validLong(adminDTO.getAdminId())) {
			if (!CommonUtil.validString(adminDTO.getPassword())) {
				result.rejectValue("password", null, "Password is required.");
			}
		}

	}

	@GetMapping("/admin-list")
	public String adminList(@ModelAttribute("adminSearchDTO") AdminSearchDTO adminSearchDTO, Model model,
			HttpServletRequest httpRequest) {
		commonModelForAdminSetup(model);
		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		model.addAttribute("active", MenuConstants.USER_SEARCH);
		return "admin-list";
	}

	@PostMapping("/admin-search.json")
	@ResponseBody
	public AdminSearchDTO adminUserSearch(@RequestBody AdminSearchDTO adminSearchDTO, HttpServletRequest httpRequest) {

		try {
			List<AdminDTO> adminList = adminService.searchAdminList(adminSearchDTO, httpRequest);
			Long recordCount = adminService.searchAdminCount(adminSearchDTO);
			adminSearchDTO.setData(adminList);
			adminSearchDTO.setRecordsFiltered(recordCount);
			adminSearchDTO.setRecordsTotal(recordCount);

		} catch (Exception e) {
			logger.error(
					"Exception occurred while searching product => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return adminSearchDTO;

	}

	@PostMapping("/deleteAdminById.json")
	@ResponseBody
	public String deleteAdminById(@RequestBody Long id) {

		String result = "Unable to delete admin.";

		try {

			boolean deleteResult = adminService.deleteAdminById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "Unable to delete admin because it is already in use.";
			logger.error("Exception occurred while deleteing order by id => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return new Gson().toJson(result);

	}

	private void commonModelForAdminSetup(Model model) {
		model.addAttribute("list1", "အမည်");
		model.addAttribute("list2", "အီးမေးလ်");
		model.addAttribute("list3", "အဆင့်");
		model.addAttribute("list4", "စကားဝှက်");
		model.addAttribute("roleList", RoleType.getAll());
	}

}
