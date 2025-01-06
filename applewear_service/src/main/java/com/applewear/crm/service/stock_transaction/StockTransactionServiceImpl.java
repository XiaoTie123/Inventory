package com.applewear.crm.service.stock_transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.stock_transaction.StockTransactionDao;
import com.applewear.crm.dto.stock_transaction.StockTransactionDTO;
import com.applewear.crm.dto.stock_transaction.StockTransactionSearchDTO;
import com.applewear.crm.entity.admin.Admin;
import com.applewear.crm.entity.product.Product;
import com.applewear.crm.entity.stock_transaction.StockTransaction;
import com.applewear.crm.entity.transaction_type.StockTransactionType;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class StockTransactionServiceImpl implements StockTransactionService {

	@Autowired
	private StockTransactionDao stockTransactionDao;

	@Override
	public Long manageStockTransaction(StockTransactionDTO stockTransactionDTO) {

		StockTransaction entity = new StockTransaction();

		if (CommonUtil.validLong(stockTransactionDTO.getStockTransactionId())) {
			entity = stockTransactionDao.get(stockTransactionDTO.getStockTransactionId());
			entity.setUpdatedTime(new Date());
		} else {
			entity.setCreatedTime(new Date());
			entity.setUpdatedTime(new Date());

			if (CommonUtil.validLong(stockTransactionDTO.getSubmittedById())) {
				Admin admin = new Admin();
				admin.setId(stockTransactionDTO.getSubmittedById());
				entity.setUser(admin);
			}

		}

		if (CommonUtil.validLong(stockTransactionDTO.getProductId())) {
			Product product = new Product();
			product.setId(stockTransactionDTO.getProductId());
			entity.setProduct(product);
		}

		if (CommonUtil.validLong(stockTransactionDTO.getTransactionTypeId())) {
			StockTransactionType stockTransactionType = new StockTransactionType();
			stockTransactionType.setId(stockTransactionDTO.getTransactionTypeId());
			entity.setStockTransactionType(stockTransactionType);
		}

		entity.setQuantity(stockTransactionDTO.getQuantity());

		entity.setRefNo(stockTransactionDTO.getRefNo());

		entity.setRemark(stockTransactionDTO.getRemark());

		stockTransactionDao.saveOrUpdate(entity);

		return entity != null && CommonUtil.validLong(entity.getId()) ? entity.getId() : null;
	}

	@Override
	public List<StockTransactionDTO> searchStockTransactions(StockTransactionSearchDTO searchDTO) {

		List<StockTransactionDTO> stockTransactionDTOList = new ArrayList<StockTransactionDTO>();

		List<StockTransaction> entityList = stockTransactionDao.searchStockTransactions(searchDTO);

		if (CommonUtil.validList(entityList)) {
			Integer num = searchDTO.getStart() != null ? searchDTO.getStart() + 1 : 1;
			for (StockTransaction entity : entityList) {
				StockTransactionDTO stockTransactionDTO = new StockTransactionDTO(entity);
				stockTransactionDTO.setNum(num);
				num++;
				stockTransactionDTOList.add(stockTransactionDTO);
			}
		}

		return stockTransactionDTOList;
	}

	@Override
	public Long searchStockTransactionCount(StockTransactionSearchDTO searchDTO) {

		return stockTransactionDao.searchStockTransactionCount(searchDTO);
	}

	@Override
	public StockTransactionDTO getTransactionById(Long id) {

		if (CommonUtil.validLong(id)) {

			StockTransaction entity = stockTransactionDao.get(id);

			if (entity != null) {
				return new StockTransactionDTO(entity);
			}

		}

		return null;
	}

}
