package com.applewear.crm.admin.controller.stock_transaction;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.applewear.crm.dto.admin.AdminDTO;
import com.applewear.crm.dto.stock_transaction.StockTransactionDTO;
import com.applewear.crm.dto.stock_transaction.StockTransactionSearchDTO;
import com.applewear.crm.service.stock_transaction.StockTransactionService;
import com.applewear.crm.service.transaction_type.TransactionTypeService;
import com.applewear.crm.util.common.CommonConstants;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.applewear.crm.util.common.ReportConstants;
import com.applewear.crm.util.report.CommonReport;
import com.google.protobuf.Message;

@Controller
public class StockTransactionController {

	private final Logger logger = LogManager.getLogger();

	@Autowired
	private StockTransactionService stockTransactionService;

	@Autowired
	private TransactionTypeService transactionTypeService;

	@GetMapping("/stock-transaction-setup")
	public String manageStockTransaction(@RequestParam(name = "id", required = false) Long stockTransactionId,
			Model model, HttpServletRequest httpRequest) {

		StockTransactionDTO stockTransactionDTO = new StockTransactionDTO();

		if (CommonUtil.validLong(stockTransactionId)) {
			stockTransactionDTO = stockTransactionService.getTransactionById(stockTransactionId);
		}

		model.addAttribute("active", MenuConstants.STOCK_TRANSACTION_SETUP);
		model.addAttribute("stockTransactionDTO", stockTransactionDTO);
		model.addAttribute("transactionTypeList", transactionTypeService.getAllTransactionType());
		return "stock-transaction-setup";
	}

	@PostMapping("/stock-transaction-setup")
	public String manageStockTransaction(@ModelAttribute("stockTransactionDTO") StockTransactionDTO stockTransactionDTO,
			Model model, HttpServletRequest httpRequest, RedirectAttributes redirectAttributes, BindingResult result) {

		try {

			AdminDTO adminDTO = (AdminDTO) httpRequest.getSession().getAttribute(CommonConstants.LOGIN_SESSION_KEY);

			stockTransactionDTO.setSubmittedById(adminDTO.getAdminId());

			validateStockTransaction(stockTransactionDTO, result);

			if (!result.hasErrors()) {

				Long stockTransactionId = stockTransactionService.manageStockTransaction(stockTransactionDTO);

				if (CommonUtil.validLong(stockTransactionId)) {
					redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_SUCCESS + "Stock transaction is saved successfully.");
					return "redirect:/stock-transaction-setup.html";
				} else {
					model.addAttribute(CommonConstants.FORM_MSG_KEY,
							CommonConstants.MSG_PREFIX_FAILED + "Stock transaction saving failed.");
				}

			} else {
				model.addAttribute(CommonConstants.FORM_MSG_KEY,
						CommonConstants.MSG_PREFIX_FAILED + "Stock transaction saving failed.");
			}

		} catch (Exception e) {
			Log.error("Exception occured while saving stock transaction => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		model.addAttribute("active", MenuConstants.STOCK_TRANSACTION_SETUP);
		model.addAttribute("stockTransactionDTO", stockTransactionDTO);
		model.addAttribute("transactionTypeList", transactionTypeService.getAllTransactionType());
		return "stock-transaction-setup";
	}

	@GetMapping("/stock-transaction-search")
	public String stockTransactionSearch(Model model) {
		model.addAttribute("active", MenuConstants.STOCK_TRANSACTION_SEARCH);
		model.addAttribute("transactionTypeList", transactionTypeService.getAllTransactionType());
		return "stock-transaction-search";
	}

	@PostMapping("/stock-transaction-search.json")
	@ResponseBody
	public StockTransactionSearchDTO stockTransactionSearch(@RequestBody StockTransactionSearchDTO searchDTO) {

		try {

			List<StockTransactionDTO> dataList = stockTransactionService.searchStockTransactions(searchDTO);
			Long allCount = stockTransactionService.searchStockTransactionCount(searchDTO);

			searchDTO.setData(dataList);
			searchDTO.setRecordsFiltered(allCount);
			searchDTO.setRecordsTotal(allCount);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occurred while searching stock transaction => " + ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;

	}

	private void validateStockTransaction(StockTransactionDTO stockTransactionDTO, BindingResult result) {

		if (!CommonUtil.validString(stockTransactionDTO.getRefNo())) {
			result.rejectValue("refNo", null, "Ref no is required.");
		}

		if (!CommonUtil.validLong(stockTransactionDTO.getProductId())) {
			result.rejectValue("productId", null, "Product is required.");
		}

		if (!CommonUtil.validInteger(stockTransactionDTO.getQuantity())) {
			result.rejectValue("quantity", "quantity.is.required", "Quantity is required.");
		}

		if (!CommonUtil.validLong(stockTransactionDTO.getTransactionTypeId())) {
			result.rejectValue("transactionTypeId", null, "Transaction type is required.");
		}
	}

	@PostMapping(value = "/stock-transaction-search", params = "exportStockTransactionReport")
	public void exportStockTransactionReport(@ModelAttribute("searchDTO") StockTransactionSearchDTO searchDTO,
			HttpServletRequest httpRequest, HttpServletResponse response) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			searchDTO.setExcelReport(true);
			searchDTO.setStart(0);
			searchDTO.setLength(1000);
			List<StockTransactionDTO> dataList = stockTransactionService.searchStockTransactions(searchDTO);

			HashMap<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("no", "စဉ်");
			parameter.put("productName", "ပစ္စည်း အမည်");
			parameter.put("labelType", "အမျိုးအစား");
			parameter.put("quantity", "ပစ္စည်း အရေအတွက်	");
			parameter.put("refNo", "Ref No");
			parameter.put("attachment", "အထောက်အထား");
			parameter.put("remark", "မှတ်ချက်");
			parameter.put("submittedByName", "လုပ်ဆောင်သည့်သူ");
			parameter.put("createdDate", "နေ့စွဲ");

			String realPath = httpRequest.getServletContext().getRealPath("/");

			if (CommonUtil.validString(searchDTO.getFromDate()) && CommonUtil.validString(searchDTO.getToDate())) {
				parameter.put("dateRange", searchDTO.getFromDate() + " - " + searchDTO.getToDate());
			} else if (CommonUtil.validString(searchDTO.getToDate())) {
				parameter.put("dateRange", " - " + searchDTO.getToDate());
			} else if (CommonUtil.validString(searchDTO.getFromDate())) {
				parameter.put("dateRange", " - " + searchDTO.getFromDate());
			}

			String reportPath = realPath + ReportConstants.STOCK_TRANSACTION_REPORT;
			String fileName = "StockTransactionReport_" + System.currentTimeMillis() + ".xls";

			CommonReport.generateReportExcel(dataList, reportPath, parameter, baos);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			baos.writeTo(response.getOutputStream());
			baos.flush();

		} catch (Exception e) {
			logger.error("Exception occurred while exporting stock transaction report => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (Exception exp) {
				logger.error("Exception occurred while exporting stock transaction report => Exception => "
						+ ExceptionUtils.getStackTrace(exp));
			}
		}

	}

}
