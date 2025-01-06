package com.applewear.crm.admin.controller.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.util.Log;
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
import com.applewear.crm.dto.product.ProductDTO;
import com.applewear.crm.dto.product.ProductSearchDTO;
import com.applewear.crm.service.product.ProductService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.ImageConstant;
import com.applewear.crm.util.common.MenuConstants;
import com.applewear.crm.util.enums.ProductType;
import com.google.gson.Gson;

@Controller
public class ProductController extends CommonController {

	@Autowired
	private ProductService productService;

	public final Logger logger = LogManager.getLogger();

	@GetMapping("/product-setup")
	public String productSetup(Model model, @RequestParam(name = "id", required = false) Long productId,
			HttpServletRequest httpRequest) {

		ProductDTO productDTO = new ProductDTO();
		productDTO.setOneTimeProduct(1);
		if (CommonUtil.validLong(productId)) {
			productDTO = productService.getProductById(productId);
			productDTO.setProductPhoto(
					CommonUtil.prepareImageUrl(httpRequest, ImageConstant.PRODUCT, productDTO.getProductPhoto()));

		}
		commonModelForProductSetup(model);
		model.addAttribute("productDTO", productDTO);
		return "product-setup";

	}

	@PostMapping("/product-setup")
	public String productSetup(@ModelAttribute("productDTO") ProductDTO productDTO, Model model,
			RedirectAttributes redirectAttributes, BindingResult result, HttpServletRequest req) {

		try {
			validateProductSetup(productDTO, result);
			if (!result.hasErrors()) {
				// CustomerDTO sessionUser = (CustomerDTO)
				// req.getSession().getAttribute(CommonConstants.LOGIN_SESSION_KEY);
				productDTO.setUserId(Long.parseLong("1"));
				Long productId = productService.manageProduct(productDTO);

				if (CommonUtil.validLong(productId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Product is saved successfully.");
					return "redirect:/list.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Product saving failed.");
				}

			} else {

				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Product saving failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while saving product => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		commonModelForProductSetup(model);
		model.addAttribute("productDTO", productDTO);
		return "product-setup";

	}

	private void validateProductSetup(ProductDTO productDTO, BindingResult result) {

		if (!CommonUtil.validString(productDTO.getProductCode())) {
			result.rejectValue("productCode", null, "Product Code is required.");
		}

		if (!CommonUtil.validInteger(productDTO.getQuantity())) {
			result.rejectValue("quantity", null, "Quantity Per Pack is required.");
		}

		if (!CommonUtil.validLong(productDTO.getBrandId())) {
			result.rejectValue("brandId", null, "Brand is required.");
		}

		if (!CommonUtil.validLong(productDTO.getCategoryId())) {
			result.rejectValue("categoryId", null, "Category is required.");
		}

		if (!CommonUtil.validLong(productDTO.getSizeId())) {
			result.rejectValue("sizeId", null, "Size is required.");
		}

		if (!CommonUtil.validBigDecimal(productDTO.getBuyingPrice())) {
			result.rejectValue("buyingPrice", null, "Buying Price is required.");
		}

		if (!CommonUtil.validBigDecimal(productDTO.getSellingPrice())) {
			result.rejectValue("sellingPrice", null, "Selling Price is required.");
		}

		if (!CommonUtil.validInteger(productDTO.getOneTimeProduct())) {
			result.rejectValue("Product Type", null, "Product Type is required.");
		}

	}

	@GetMapping("/list")
	public String productList(@ModelAttribute("productSearchDTO") ProductSearchDTO productSearchDTO, Model model,
			HttpServletRequest httpRequest) {
		commonModelForProductSetup(model);
		model.addAttribute("active", MenuConstants.PRODUCT_SEARCH);
		model.addAttribute("productSearchDTO", new ProductSearchDTO());
		return "list";
	}

	@PostMapping("/product-search.json")
	@ResponseBody
	public ProductSearchDTO adminUserSearch(@RequestBody ProductSearchDTO productSearchDTO,
			HttpServletRequest httpRequest) {

		try {
			List<ProductDTO> productList = productService.searchProductList(productSearchDTO, httpRequest);
			Long recordCount = productService.searchProductCount(productSearchDTO);
			productSearchDTO.setData(productList);
			productSearchDTO.setRecordsFiltered(recordCount);
			productSearchDTO.setRecordsTotal(recordCount);

		} catch (Exception e) {
			logger.error(
					"Exception occurred while searching product => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return productSearchDTO;

	}

	@GetMapping("/product-search-autocomplete.json")
	@ResponseBody
	public List<ProductDTO> searchProductAutocomplete(
			@RequestParam(name = "searchKey", required = false) String seachKey) {

		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

		try {

			productDTOList = productService.searchProductAutoComplete(seachKey);

		} catch (Exception e) {
			Log.error("Exception occurred while search product => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return productDTOList;

	}

	@PostMapping("/deleteProductById.json")
	@ResponseBody
	public String deleteProductById(@RequestBody Long id) {

		String result = "Unable to delete product.";

		try {

			boolean deleteResult = productService.deleteProductById(id);

			if (deleteResult) {
				result = "1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "Unable to delete product because it is already in use.";
			logger.error("Exception occurred while deleteing product by id => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return new Gson().toJson(result);

	}

	private void commonModelForProductSetup(Model model) {
		model.addAttribute("list", "Product ID");
		model.addAttribute("list1", "ပစ္စည်း အမည်");
		model.addAttribute("list2", "Brand အမည်");
		model.addAttribute("list3", "အမျိုးအစား");
		model.addAttribute("list4", "Size ကုဒ်");
		model.addAttribute("list5", "ပစ္စည်း တင်သူ");
		model.addAttribute("list6", "ရောင်းစျေး");
		model.addAttribute("sizeList", productService.getAllSizeList());
		model.addAttribute("categoryList", productService.getAllCategoryList());
		model.addAttribute("brandList", productService.getAllBrandList());
		model.addAttribute("productTypeList", ProductType.getAll());
		model.addAttribute("active", MenuConstants.PRODUCT_SETUP);
	}

}
