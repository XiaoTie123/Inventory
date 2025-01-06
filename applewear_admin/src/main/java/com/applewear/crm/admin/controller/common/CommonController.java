package com.applewear.crm.admin.controller.common;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applewear.crm.util.common.CommonConstants;

@Controller
public class CommonController {

	private final Logger logger = LogManager.getLogger(getClass());

	public void addRedirectSuccessMessage(String message, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY,
				CommonConstants.MSG_PREFIX_SUCCESS + message);
	}

	public void addRedirectFailMessage(String message, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute(CommonConstants.FORM_MSG_KEY, CommonConstants.MSG_PREFIX_FAILED + message);
	}

	public void addModelSuccessMessage(String message, Model model) {
		model.addAttribute(CommonConstants.FORM_MSG_KEY, CommonConstants.MSG_PREFIX_SUCCESS + message);
	}

	public void addModelFailMessage(String message, Model model) {
		model.addAttribute(CommonConstants.FORM_MSG_KEY, CommonConstants.MSG_PREFIX_FAILED + message);
	}
	
	protected void writeWorkbook(Workbook workbook, String name, HttpServletResponse response) {
		renameHttpResponseName(name, response);
		writeToHttpResponse(workbook, response);
	}
	
	public void renameHttpResponseName(String name, HttpServletResponse response) {
		String str = String.format("attachment; filename=\"%s\"", name);
		response.setHeader("Content-Disposition", str);
	}

	@ResponseBody
	public void writeToHttpResponse(Workbook workbook, HttpServletResponse response) {
		try {
			workbook.write(response.getOutputStream());
			workbook.close();
		} catch (Exception e) {
			logger.error("Can't write Excel to Response ", e);
		}
	}

}
