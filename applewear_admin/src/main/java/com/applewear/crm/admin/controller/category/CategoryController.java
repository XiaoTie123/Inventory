package com.applewear.crm.admin.controller.category;

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

import com.applewear.crm.dto.category.CategoryDTO;
import com.applewear.crm.service.category.CategoryService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.google.gson.Gson;

@Controller
public class CategoryController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/category-setup")
	public String categorySetup(Model model, @RequestParam(name = "id", required = false) Long categoryId) {

		CategoryDTO categoryDTO = new CategoryDTO();

		if (CommonUtil.validLong(categoryId)) {
			categoryDTO = categoryService.getCategoryById(categoryId);
		}

		model.addAttribute("categoryDTO", categoryDTO);
		model.addAttribute("categoryList", categoryService.getAllCategoryList());
		model.addAttribute("active", MenuConstants.CATEGORY_SETUP);
		return "category-setup";

	}

	@PostMapping("/category-setup")
	public String categorySetup(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			validateCategorySetup(categoryDTO, result);

			if (!result.hasErrors()) {

				Long categoryId = categoryService.manageCategory(categoryDTO);

				if (CommonUtil.validLong(categoryId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Category is saved successfully.");
					return "redirect:/category-setup.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Category saving failed.");
				}

			} else {
				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Category saving failed.");
			}

		} catch (Exception e) {
			LOG.error("Exception occurred while saving rank => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("categoryDTO", categoryDTO);
		model.addAttribute("categoryList", categoryService.getAllCategoryList());
		model.addAttribute("active", MenuConstants.CATEGORY_SETUP);
		return "category-setup";
	}

	@PostMapping("/deleteCategoryById.json")
	@ResponseBody
	public String deleteCategoryById(@RequestBody Long id) {

		String result = "Unable to delete category because it is already in use.";

		try {

			boolean deleteResult = categoryService.deleteCategoryById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new Gson().toJson(result);

	}

	private void validateCategorySetup(CategoryDTO categoryDTO, BindingResult result) {

		if (!CommonUtil.validString(categoryDTO.getCategoryName())) {
			result.rejectValue("categoryName", null, "Category Name is required.");
		}

	}

}
