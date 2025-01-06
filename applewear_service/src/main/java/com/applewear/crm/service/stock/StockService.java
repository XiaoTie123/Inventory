package com.applewear.crm.service.stock;

import java.util.List;

import com.applewear.crm.dto.inventory.InventoryDTO;
import com.applewear.crm.dto.inventory.InventorySearchDTO;

public interface StockService {

	List<InventoryDTO> searchInventorys(InventorySearchDTO searchDTO);

	Long searchInventoryCount(InventorySearchDTO searchDTO);

}
