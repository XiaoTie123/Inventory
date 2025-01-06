package com.applewear.crm.admin.controller.size;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applewear.crm.dto.size.SizeDTO;
import com.applewear.crm.service.size.SizeService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;

@Controller
public class SizeController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private SizeService sizeService;

	@GetMapping("/size-setup")
	public String sizeSetup(Model model, @RequestParam(name = "id", required = false) Long sizeId) {

		SizeDTO sizeDTO = new SizeDTO();

		if (CommonUtil.validLong(sizeId)) {
			sizeDTO = sizeService.getSizeById(sizeId);
		}

		model.addAttribute("sizeDTO", sizeDTO);
		model.addAttribute("sizeList", sizeService.getAllSizeList());
		model.addAttribute("active", MenuConstants.SIZE_SETUP);
		return "size-setup";

	}

	@PostMapping("/size-setup")
	public String rankSetup(@ModelAttribute("sizeDTO") SizeDTO sizeDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			validateSizeSetup(sizeDTO, result);

			if (!result.hasErrors()) {

				Long sizeId = sizeService.manageSize(sizeDTO);

				if (CommonUtil.validLong(sizeId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Size is saved successfully.");
					return "redirect:/size-setup.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Size saving failed.");
				}

			} else {
				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Size saving failed.");
			}

		} catch (Exception e) {
			LOG.error("Exception occurred while saving size => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("sizeDTO", sizeDTO);
		model.addAttribute("sizeList", sizeService.getAllSizeList());
		model.addAttribute("active", MenuConstants.SIZE_SETUP);
		return "size-setup";
	}

	private void validateSizeSetup(SizeDTO sizeDTO, BindingResult result) {

		if (!CommonUtil.validString(sizeDTO.getSizeName())) {
			result.rejectValue("sizeName", null, "Name is required.");
		}
		
		if (!CommonUtil.validString(sizeDTO.getSizeCode())) {
			result.rejectValue("sizeCode", null, "Code is required.");
		}

	}

}
