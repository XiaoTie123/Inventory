package com.applewear.crm.admin.controller.brand;

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

import com.applewear.crm.dto.brand.BrandDTO;
import com.applewear.crm.service.brand.BrandService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.google.gson.Gson;

@Controller
public class BrandController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private BrandService brandService;

	@GetMapping("/brand-setup")
	public String brandSetup(Model model, @RequestParam(name = "id", required = false) Long brandId) {

		BrandDTO brandDTO = new BrandDTO();

		if (CommonUtil.validLong(brandId)) {
			brandDTO = brandService.getBrandById(brandId);
		}

		model.addAttribute("brandDTO", brandDTO);
		model.addAttribute("brandList", brandService.getAllBrandList());
		model.addAttribute("active", MenuConstants.BRAND_SETUP);
		return "brand-setup";

	}

	@PostMapping("/brand-setup")
	public String rankSetup(@ModelAttribute("brandDTO") BrandDTO brandDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			validateBrandSetup(brandDTO, result);

			if (!result.hasErrors()) {

				Long brandId = brandService.manageBrand(brandDTO);

				if (CommonUtil.validLong(brandId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Brand is saved successfully.");
					return "redirect:/brand-setup.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Brand saving failed.");
				}

			} else {
				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Brand saving failed.");
			}

		} catch (Exception e) {
			LOG.error("Exception occurred while saving rank => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("brandDTO", brandDTO);
		model.addAttribute("active", MenuConstants.BRAND_SETUP);
		model.addAttribute("brandList", brandService.getAllBrandList());
		return "brand-setup";
	}

	@PostMapping("/deleteBrandById.json")
	@ResponseBody
	public String deleteBrandById(@RequestBody Long id) {

		String result = "Unable to delete brand because it is already in use.";

		try {

			boolean deleteResult = brandService.deleteBrandById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new Gson().toJson(result);

	}

	private void validateBrandSetup(BrandDTO brandDTO, BindingResult result) {

		if (!CommonUtil.validString(brandDTO.getBrandName())) {
			result.rejectValue("brandName", null, "Name is required.");
		}

	}

}
