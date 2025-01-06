package com.applewear.crm.admin.controller.inventory;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.applewear.crm.dto.inventory.InventoryDTO;
import com.applewear.crm.dto.inventory.InventorySearchDTO;
import com.applewear.crm.service.stock.StockService;
import com.applewear.crm.util.common.MenuConstants;

@Controller
public class InventoryController {

	private final Logger LOG = LogManager.getLogger();

	@Autowired
	private StockService stockService;

	@GetMapping("/inventory")
	public String inventory(Model model) {
		model.addAttribute("active", MenuConstants.INVENTORY);
		return "inventory";
	}

	@PostMapping("/inventory-search.json")
	@ResponseBody
	public InventorySearchDTO inventorySearch(@RequestBody InventorySearchDTO searchDTO) {

		try {

			List<InventoryDTO> inventoryList = stockService.searchInventorys(searchDTO);
			Long allCount = stockService.searchInventoryCount(searchDTO);

			searchDTO.setData(inventoryList);
			searchDTO.setRecordsTotal(allCount);
			searchDTO.setRecordsFiltered(allCount);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(
					"Exception occurred while searching inventory => Exception => " + ExceptionUtils.getStackTrace(e));
		}

		return searchDTO;
	}

}
