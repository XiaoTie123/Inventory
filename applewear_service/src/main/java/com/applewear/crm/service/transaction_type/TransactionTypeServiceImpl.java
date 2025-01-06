package com.applewear.crm.service.transaction_type;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applewear.crm.dao.transaction_type.TransactionTypeDao;
import com.applewear.crm.dto.stock_transaction.StockTransactionDTO;
import com.applewear.crm.dto.transaction_type.TransactionTypeDTO;
import com.applewear.crm.entity.transaction_type.StockTransactionType;
import com.applewear.crm.util.common.CommonUtil;

@Service
@Transactional
public class TransactionTypeServiceImpl implements TransactionTypeService {

	@Autowired
	private TransactionTypeDao transactionTypeDao;

	@Override
	public List<TransactionTypeDTO> getAllTransactionType() {

		List<TransactionTypeDTO> transactionTypeList = new ArrayList<TransactionTypeDTO>();

		List<StockTransactionType> entityList = transactionTypeDao.getAllTransactionType();

		if (CommonUtil.validList(entityList)) {

			for (StockTransactionType entity : entityList) {
				TransactionTypeDTO transactionTypeDTO = new TransactionTypeDTO(entity);
				transactionTypeList.add(transactionTypeDTO);
			}

		}

		return transactionTypeList;
	}

}
