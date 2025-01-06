package com.applewear.crm.dao.stock;

import java.util.List;

import com.applewear.crm.dao.generic.GenericDao;
import com.applewear.crm.dto.inventory.InventoryDTO;
import com.applewear.crm.dto.inventory.InventorySearchDTO;
import com.applewear.crm.entity.stock.Stock;

public interface StockDao extends GenericDao<Stock, Long> {

	List<InventoryDTO> searchInventorys(InventorySearchDTO searchDTO);

	Long searchInventoryCount(InventorySearchDTO searchDTO);

}
