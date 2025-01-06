package com.applewear.crm.service.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.stock.StockDao;
import com.applewear.crm.dto.inventory.InventoryDTO;
import com.applewear.crm.dto.inventory.InventorySearchDTO;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDao stockDao;

	@Override
	public List<InventoryDTO> searchInventorys(InventorySearchDTO searchDTO) {

		List<InventoryDTO> inventoryDTOList = stockDao.searchInventorys(searchDTO);

		if (CommonUtil.validList(inventoryDTOList)) {

			Integer num = searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1;

			for (InventoryDTO inventoryDTO : inventoryDTOList) {
				inventoryDTO.setNum(num);
				num++;
			}

		}

		return inventoryDTOList;
	}

	@Override
	public Long searchInventoryCount(InventorySearchDTO searchDTO) {

		return stockDao.searchInventoryCount(searchDTO);
	}

}
