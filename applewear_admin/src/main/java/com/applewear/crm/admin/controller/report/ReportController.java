package com.applewear.crm.admin.controller.report;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.applewear.crm.dto.sale_report.SaleReportDTO;
import com.applewear.crm.dto.sale_report.SaleReportSearchDTO;
import com.applewear.crm.service.order.OrderService;
import com.applewear.crm.util.common.CommonUtil;
import com.applewear.crm.util.common.MenuConstants;
import com.applewear.crm.util.common.ReportConstants;
import com.applewear.crm.util.report.CommonReport;

@Controller
public class ReportController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private OrderService orderService;

	@GetMapping("/sale-monthly-report")
	public String saleMonthlyReport(Model model) {
		model.addAttribute("active", MenuConstants.SALE_MONTHLY_REPORT);
		model.addAttribute("searchDTO", new SaleReportSearchDTO());
		return "sale-monthly-report";
	}

	@PostMapping("/sale-monthly-report.json")
	@ResponseBody
	public SaleReportSearchDTO saleMonthlyReport(@RequestBody SaleReportSearchDTO searchDTO) {

		try {

			List<SaleReportDTO> dataList = orderService.searchMonthlyReport(searchDTO);
			Long totalCount = orderService.searchMonthlyReportCount(searchDTO);

			searchDTO.setData(dataList);
			searchDTO.setRecordsFiltered(totalCount);
			searchDTO.setRecordsTotal(totalCount);

		} catch (Exception e) {
			LOG.error("Exception occurred while searching sale monthly report => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@PostMapping(value = "/sale-monthly-report", params = "exportSaleMonthlyReport")
	public void exportSaleMonthlyReport(@ModelAttribute("searchDTO") SaleReportSearchDTO searchDTO,
			HttpServletRequest httpRequest, HttpServletResponse response) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			searchDTO.setExcelReport(true);

			List<SaleReportDTO> dataList = orderService.searchMonthlyReport(searchDTO);

			HashMap<String, Object> parameter = new HashMap<String, Object>();
			String realPath = httpRequest.getServletContext().getRealPath("/");

			if (CommonUtil.validString(searchDTO.getFromMonthYear())
					&& CommonUtil.validString(searchDTO.getToMonthYear())) {
				parameter.put("dateRange", searchDTO.getFromMonthYear() + " - " + searchDTO.getToMonthYear());
			} else if (CommonUtil.validString(searchDTO.getFromMonthYear())) {
				parameter.put("dateRange", " - " + searchDTO.getFromMonthYear());
			} else if (CommonUtil.validString(searchDTO.getToMonthYear())) {
				parameter.put("dateRange", " - " + searchDTO.getToMonthYear());
			}

			String reportPath = realPath + ReportConstants.SALE_MONTHLY_REPORT;
			String fileName = "SaleMonthlyReport_" + System.currentTimeMillis() + ".xls";

			CommonReport.generateReportExcel(dataList, reportPath, parameter, baos);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			baos.writeTo(response.getOutputStream());
			baos.flush();

		} catch (Exception e) {
			LOG.error("Exception occurred while exporting sale monthly report => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (Exception exp) {
				LOG.error("Exception occurred while exporting sale monthly report => Exception => "
						+ ExceptionUtils.getStackTrace(exp));
			}
		}

	}

	@GetMapping("/sale-yearly-report")
	public String saleYearlyReport(Model model) {
		model.addAttribute("active", MenuConstants.SALE_YEARLY_REPORT);
		return "sale-yearly-report";
	}

	@PostMapping("/sale-yearly-report.json")
	@ResponseBody
	public SaleReportSearchDTO saleYearlyReport(@RequestBody SaleReportSearchDTO searchDTO) {

		try {

			List<SaleReportDTO> dataList = orderService.searchYearlyReport(searchDTO);
			Long totalCount = orderService.searchYearlyReportCount(searchDTO);

			searchDTO.setData(dataList);
			searchDTO.setRecordsFiltered(totalCount);
			searchDTO.setRecordsTotal(totalCount);

		} catch (Exception e) {
			LOG.error("Exception occurred while searching sale yearly report => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@GetMapping("/sale-daily-report")
	public String saleDailyReport(Model model) {
		model.addAttribute("active", MenuConstants.SALE_DAILY_REPORT);
		model.addAttribute("searchDTO", new SaleReportSearchDTO());
		return "sale-daily-report";
	}

	@PostMapping("/sale-daily-report.json")
	@ResponseBody
	public SaleReportSearchDTO saleDailyReport(@RequestBody SaleReportSearchDTO searchDTO) {

		try {

			List<SaleReportDTO> dataList = orderService.searchDailyReport(searchDTO);
			Long totalCount = orderService.searchDailyReportCount(searchDTO);
			searchDTO.setData(dataList);
			searchDTO.setRecordsFiltered(totalCount);
			searchDTO.setRecordsTotal(totalCount);

		} catch (Exception e) {
			LOG.error("Exception occurred while searching sale daily report => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

	@PostMapping(value = "/sale-daily-report", params = "exportDailyExcelReport")
	public void exportSaleDailyReport(@ModelAttribute("searchDTO") SaleReportSearchDTO searchDTO,
			HttpServletRequest httpRequest, HttpServletResponse response) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			searchDTO.setExcelReport(true);

			List<SaleReportDTO> dataList = orderService.searchDailyReport(searchDTO);

			HashMap<String, Object> parameter = new HashMap<String, Object>();
			String realPath = httpRequest.getServletContext().getRealPath("/");

			if (CommonUtil.validString(searchDTO.getFromDate()) && CommonUtil.validString(searchDTO.getToDate())) {
				parameter.put("dateRange", searchDTO.getFromDate() + " - " + searchDTO.getToDate());
			} else if (CommonUtil.validString(searchDTO.getToDate())) {
				parameter.put("dateRange", " - " + searchDTO.getToDate());
			} else if (CommonUtil.validString(searchDTO.getFromDate())) {
				parameter.put("dateRange", " - " + searchDTO.getFromDate());
			}

			String reportPath = realPath + ReportConstants.SALE_DAILY_REPORT;
			String fileName = "SaleDailyReport_" + System.currentTimeMillis() + ".xls";

			CommonReport.generateReportExcel(dataList, reportPath, parameter, baos);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			baos.writeTo(response.getOutputStream());
			baos.flush();

		} catch (Exception e) {
			LOG.error("Exception occurred while exporting sale daily report => Exception => "
					+ ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (Exception exp) {
				LOG.error("Exception occurred while exporting sale daily report => Exception => "
						+ ExceptionUtils.getStackTrace(exp));
			}
		}

	}

}
